##*vue.js 시작하기 -1*

>props, events 속성

* **뷰 생성** 

  * 뷰 라이브러리
    ```
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    ```
    
  * 전역변수 선언방법

    ```
        Vue.component('app-content', {
            //Vue.component('컴포넌트 이름', 컴포넌트 내용')
            template: '<div>content</div>'
        });
    ```

  * 지역변수 선언방법

    ```
            new Vue({
                el: "#app",
                components: {
                    // '컴포넌트 이름': 컴포넌트 내용
                    'app-footer': {
                        template: '<footer>footer</footer>'
                    }
                }
            });
    ```

    <br>

* **props 및 events 란?**
  
    <img src="/vue/props&event.png" width="300">

    -props: 부모 컴포넌트에서 자식 컴포넌트로 데이터 전달
    -events: 자식에서 부모로 데이터 참조
    <br>

    * props
    ```
    <body>

        <div id="app">
            <app-header v-bind:propsdata= "message"></app-header>
            <!-- v-bind:프롭스 속성 이름= "상위 컴포넌트의 데이터 이름"-->
        </div>

        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

        <script>

            var appHeader = {
                template: "<h1>{{propsdata}}</h1>",
                props: ['propsdata']
            }

            new Vue({
                el: "#app",
                components :{
                    'app-header': appHeader
                },
                data: {
                    message: 'hi'   
                }
            })
        </script>
        <!--상위: root-->
        <!--하위: app-header -->
        
    </body>
    ```
    **v-bind:프롭스 속성 이름= "상위 컴포넌트의 데이터 이름"**
    생성된 뷰는 root 인 최상위 컴포넌트인 부모 컴포넌트,
    appHeader 는 자식 컴포넌트

    실행결과 root에서 `message` 값을 변경하면 `appHeader`의 값도 변경된다.
    즉, 부모 컴포넌트의 데이터를 변경하니 자식 컴포넌트 데이터도 변경됨.
    <br>

    <span style="color:blue">
    <details>
        <summary>결과출력확인</summary>
        <div markdown="1">
            <img src="/vue/props1.png" width="500">
            <img src="/vue/props2.png" width="500">
        </div>
    </details>
    </span>
    <br>

    * events

    ```
    <body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <div id="app">
        <app-header v-on:pass="logText"></app-header>
        <!--v-on:하위 컴포넌트에서 발생한 이벤트이름="상위 컴포넌트의 메서드 이름"-->
    </div>
    <script>
        var appHeader = {
            template: '<button v-on:click="passEvent">click me</button>',
            methods:{
                passEvent: function(){
                    this.$emit('pass');
                }
            }
        }

        new Vue({
            el:"#app",
            components: {
                'app-header': appHeader

                },
            methods:{
                logText: function(){
                    console.log('Hi');
                }
            }
        });
    </script>

    </body>
    ```
    **v-on:하위 컴포넌트에서 발생한 이벤트이름="상위 컴포넌트의 메서드 이름"**

    `pass` 이벤트가 하위(`<app-header></app-header>`)에서 상위(`root`)로 올라왔을 때, 컴포넌트 태그에서 받아서 `logText`메소드 실행

    <br>

    <span style="color:blue">
    <details>
        <summary>결과출력확인</summary>
        <div markdown="1">
            <img src="/vue/events.png" width="500">
        </div>
    </details>
    </span>
    <br>

* **같은 레벨에서 컴포넌트 간 통신**
    <img src="/vue/same-level1.png" width="300">

    `ApoContent` 에서 `AppHeader`로 전달.
    같은 레벨끼리는 직접적으로 전달할 수 없음.
    그래서 `Root`를 거쳐서 전달해야함.

    `event` 속성으로 `AppContent`에서 `Root`로, `props` 속성으로 `Root`에서 `AppHeader`로 데이터 전달.

    ```
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>같은 레벨의 컴포넌트 간 통신</title>
    </head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

        <div id="app">
            <app-header v-bind:propsdata ="num"></app-header> 
            <app-content v-on:pass="deliverNum"></app-content>       
        </div>

        <script>

            var appHeader={
                template: "<div>header</div>",
                props: ['propsdata']
            }

            var appContent ={
                template: "<div>content <button v-on:click='passNum'>pass</button></div>",
                methods:{
                    passNum:function(){
                        this.$emit("pass", 10);
                    }
                }
            }

            //이 뷰가 Root임
            new Vue({
                el: "#app",
                components:{
                    "app-header":appHeader,
                    "app-content":appContent
                },
                data:{
                    num:0
                },
                methods:{
                    deliverNum:function(value){
                        this.num=value;
                    }
                }
                
            })
        </script>
        
    </body>
    ```

    위 코드는 `AppContent`에서 `Root`를 거쳐 `AppHeader`로 데이터를 전송하는 코드이다.

    `AppContent`에 저장된 "10"이라는 데이터를 pass에 담아 `Root`로 보내 `num`에 저장. 다시 `Root`에서 `AppHeader`로 보내 `propsdata`에 저장. 

    결국 같은 레벨 간의 통신방법은 상위 컴포넌트를 통해 전달한다.
    <br>

* **뷰 라우터**
    * 뷰 라우터: 뷰 라이브러리를 이용하여 싱글 페이지 애플리케이션을 구현(페이지 이동)할 때 사용하는 라이브러리.
    <br>

    * 뷰라우터 라이브러리(CDN)
    ```
    <script src="https://unpkg.com/vue-router@3.5.3/dist/vue-router.js"></script>
    ```
    `router-vuew` tag: 페이지 URL이 변경됐을 때, 변경된 페이지를 뿌려주는 영역을 정의함. 뷰 인스턴스에 라우터 인스턴스를 연결해야 사용가능.

    <br>

    ```
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vue Router</title>

    </head>
    <body>
    <div id="app">
        <router-view></router-view> <!--페이지 영역에 뿌려주는 것-->
        <div>
            <router-link to="/Login">Login</router-link>    <!--<a></a>태그와 같음-->
            <router-link to="/Main">Main</router-link>
        </div>
    </div>

    <!--라이브러리 호출 순서 중요-->
    <!--22년 2월부터는 vue 3와 vue 2의 라이브러리 주소가 다르므로 나는 vue 2이므로 @3.5.3을 붙여아한다.-->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/vue-router@3.5.3/dist/vue-router.js"></script>
    
    <script>

        var LoginComponent = {
            template: "<div>login</div>"
        }

        var MainCompontent = {
            template: "<div>main</div>"
        }

        // 라우터 인스턴스
        var router = new VueRouter({
            // 페이지의 라우팅정보
            // 페이지 갯수만큼 객체도 같은 수가 필요함.
            // 라우팅 정보는 배열로 들어오고, 페에지는 각 객체로
            mode:"history", //깔끔한 URL
            routes: [
                {   
                    path: "/login", //페이지의 URL
                    name: "login",
                    component: LoginComponent //해당 URL에서 표시될 컴포넌트
                    //components 아님 주의!
                },
                {
                    path: "/main",
                    component: MainCompontent
                }
            ]
        });

        //뷰 인스턴스
        new Vue({
            el: "#app",
            router: router  //뷰, 라우터 인스턴스 연결
        });

    </script>
    </body>
    ```

    기본적인 뷰라우터 사용방법
    <br>

* **액시오스(Axios)**
    * 액시오스: 뷰에서 권고하는 HTTP 통신 라이브러리. Promise 기반의 HTTP 통신 라이브러리이며 상대적으로 다른 HTTP 통신 라이브러리들에 비해 문서화가 잘되어 있고 API가 다양함.
    <br>
    ```
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    ```
    액시오스 라이브러리(CDN)
    <br>

    * 자바스크립트 비동기 처리패턴
        : 특정 코드의 연산이 끝날 때까지 코드의 실행을 멈추지 않고 다음 코드를 먼저 실행하는 특성.
      1. callback
      2. promise
      3. promise + generator
      4. async & await
    <br>

개발자도구 &rightarrow; 네트워크 &rightarrow; XHR : 비동기 통신 네트워크 보기

* **뷰의 템플릿 문법**
  
    데이터 바인딩 `{{ mesage }}`: 뷰 인스턴스에서 정의한 속성들을 화면에 표시하는 방법. 콧수염 괄
    디렉티브 `v-if` : 뷰로 화면의 요소를 더 쉽게 조작하기 위한 문법. `v-`로 붙는 속성은 뷰 디렉티브임.
    <br>
    * 데이터 바인딩 
    ```
    <body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <div id="app">
        <p>{{num}}</p>
        <p>{{doubleNum}}</p>
    </div>

    <script>

        new Vue({
            el: "#app",
            data:{
                num: 10
            },
            computed:{
                doubleNum :function(){
                    return this.num*2
                }
            }
        })
    </script>

    </body>
    ```
    `computed` 속성은 특정 데이터를 이용할 수 있는 속성.
    <br>
    * 디렉티브
    ```
    <body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <div id="app">
        <p v-bind:id="uuid" v-bind:class="name">{{num}}</p>
        <p>{{doubleNum}}</p>
    </div>

    <script>

        new Vue({
            el: "#app",
            data:{
                num: 10,
                uuid: "abcd1234",    //특정 태그에 연결하기 위한 id
                name: "text-blue"
            },
            computed:{
                doubleNum :function(){
                    return this.num*2
                }
            }
        })
    </script>

    </body>
    ```
    뷰 생성할 때 `uuid`를 생성하고 특정 태그에서 호출해서 지정할 수 있음.
    <br>
    ```
    <body>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <div id="app">
        <p v-bind:id="uuid" v-bind:class="name">{{num}}</p>
        <p>{{doubleNum}}</p>
        <div v-if="loading">Loading..</div>
        <div v-else>test user has been logged in</div>
        <div v-show="loading">Loading..</div>
    </div>

    <script>

        new Vue({
            el: "#app",
            data:{
                num: 10,
                uuid: "abcd1234",    //특정 태그에 연결하기 위한 id
                name: "text-blue",
                loading: true
            },
            computed:{
                doubleNum :function(){
                    return this.num*2
                }
            }
        })
    </script>

    </body>
    ```
    vue로 if문 예제인데
    `v-if`는 개발자도구 vue에서 체크박스를 풀면 소스에 보이지 않는 아예 사라진 상태이지만,
    `v-show`는 `display:none`으로 바뀌며 소스에는 남아있는 차이점이 있다.
    <br>
    <img src="/vue/bind.png" width="300">
