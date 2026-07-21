# BGP & Calico BGP Mesh 개념 정리

## 1. BGP (Border Gateway Protocol)란?

인터넷을 구성하는 라우팅 프로토콜. 전 세계 라우터들이 서로 "이 IP 대역은 나를 거쳐 가면 돼"라고 광고(announce)하고 교환하면서, 목적지까지 가는 경로 정보를 만들어가는 방식이다. 흔히 인터넷의 "지도를 그리는 프로토콜"이라고 표현한다.

핵심 동작은 단순하다:

- 각 라우터(또는 네트워크)는 자신이 담당하는 IP 대역을 이웃에게 알린다.
- 이웃은 그걸 또 자기 이웃에게 전달한다.
- 이 과정이 반복되며 "어떤 IP로 가려면 어느 방향으로 가야 하는지"에 대한 라우팅 테이블이 클러스터/네트워크 전체에 퍼진다.
- 광고를 주고받는 두 라우터 사이의 연결을 **BGP 세션(peering)** 이라 부르고, 세션이 정상일 때 "peering established"라고 표현한다.

## 2. Kubernetes에서 왜 BGP가 필요한가

쿠버네티스 파드는 각각 고유 IP를 받는다 (예: `<POD_IP_B>`). 그런데 이 IP는 물리 네트워크 장비 입장에서는 원래 알지 못하는 주소다.

```
노드 A 위의 파드 ───???───▶ 노드 B 위의 파드
       (<POD_IP_A>)         (<POD_IP_B>)
```

노드 A에 있는 파드가 노드 B에 있는 파드에게 패킷을 보내려면, 중간 경로에 있는 장비/노드들이 "<POD_IP_B>는 노드 B(<NODE-D>) 방향"이라는 걸 알아야 한다. 이 정보를 전파하는 역할을 **Calico가 각 노드를 작은 BGP 라우터처럼 동작시켜서** 수행한다.

## 3. Calico BGP Full Mesh 구조

기본 설정에서 Calico는 클러스터의 모든 노드가 서로 1:1로 BGP 세션을 맺는 **풀메시(full mesh)** 방식을 쓴다.

```
                 ┌────────────┐
                 │ <NODE-A> │
                 └─────┬──────┘
              ┌────────┼────────┐
              │        │        │
        ┌─────┴───┐┌───┴────┐┌──┴──────┐
        │<NODE-B>││<NODE-C>││<NODE-D>│
        └─────┬───┘└───┬────┘└──┬──────┘
              │        │        │
              └────────┼────────┘
                        │
                 ┌──────┴─────┐
                 │ <NODE-E> │
                 └────────────┘

  (실제로는 노드 5개 → 각 노드가 나머지 4개와 각각 세션 1개씩 = 총 10개 세션)
```

각 노드는 자신을 소개할 때 `projectcalico.org/IPv4Address` **annotation**에 적힌 IP를 자신의 BGP 라우터 주소로 사용한다. 이 값이 실제 노드 IP와 같아야 다른 노드들이 정확한 주소로 연결을 시도할 수 있다.

## 4. 정상 동작 시 로그로 보이는 모습

```
bird: Mesh_<NODE-A_IP>: Connected to table master
bird: Mesh_<NODE-A_IP>: State changed to feed
bird: Mesh_<NODE-A_IP>: State changed to up   ← 피어링 성공
```

`Mesh_<IP>`가 상대 노드와의 BGP 세션 하나를 의미하며, `state changed to up`이 되어야 그 노드로 가는 라우팅 정보가 정상적으로 오간다.

## 5. 깨졌을 때 로그 & 증상

```
Number of node(s) with BGP peering established = 0
BGP not established with <NODE-A_IP>,<NODE-C_IP>,<NODE-B_IP>,<NODE-E_IP>
```

- 이 노드가 나머지 전부와 세션을 못 맺은 상태
- calico-node readiness probe 실패 → Pod `0/1 Not Ready`
- 다른 노드들은 "이 노드 파드 대역으로 가는 길"을 모르게 됨
- 그 결과 → 다른 노드에서 이 노드 위 파드로 보내는 패킷이 목적지를 못 찾고 **connect timeout**
- ingress 입장에서는 upstream 연결 자체가 안 되니 **504 Gateway Timeout**

## 6. 한 줄 요약

> BGP는 "이 IP로 가려면 나를 거쳐가"라고 서로 광고해서 경로를 알려주는 프로토콜이고, Calico는 이를 이용해 쿠버네티스 파드 IP들이 노드 간에 정확히 라우팅되도록 만든다. 이 광고의 기준이 되는 주소(annotation)가 실제 노드 IP와 다르면, 세션 자체가 안 맺어지고 그 노드의 모든 파드가 클러스터 나머지 부분에서 접근 불가능해진다.
