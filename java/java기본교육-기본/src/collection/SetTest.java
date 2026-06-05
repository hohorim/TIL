package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest {
    // HashSet, TreeSet
    
    public void test(){
        Set<String> hs = new HashSet<>();
        Set<String> ss = new TreeSet<>();
        Set<String> lsm = new LinkedHashSet<>(); // 저장순서를 유지보장하지 않으나 원한다면 linkedhashset 사용
    }
    
    public void test2(){
        
        Set<Integer> set = new HashSet<>();
        for(int i =0; set.size()<6; i++){
            int num =(int) (Math.random()*45)+1;
            set.add(num);
            
            set.add((Integer) num);
            
        }

        // Tree 구조 에서 깊이 우선 탐색은 스택으로 구현
        // 넓이 우선 탐색은 큐로 구현

        System.out.println(set);
        List<Integer> list = new LinkedList<>(set);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(set);

    }
    
    public void test3(){
        Set<Integer> tree = new TreeSet<>(); // 이진 검색 트리
        List<Integer> list = new ArrayList<>();
        for (int i = 0; tree.size() < 6; i++) {
            int num = (int) (Math.random() * 45) + 1;
            list.add(num);
            tree.add(num);
        }
        System.out.println(list.hashCode());
        System.out.println(list.remove(3));
        System.out.println(list.hashCode());
        System.out.println(tree);
        System.out.println(tree.hashCode());
        tree.remove(list.get(1));
        System.out.println(tree.hashCode());
        char df ='a'+'b';

        // hashcode ArraysList.class 의 hashCode() 메소드
        // 소수로 해야 hashmap key 를 인덱스로 바꿀때 해쉬충돌이 안남.
        // 홀수이면서 소수면 다되는데 그냥 31 한거임. 재미있는 수임 11111(2)
        // 근데 3 , 7 이런거도 되는데 이런 작은 수들은 다른데에서 쓰일 수도 있고,
        // 너무 큰 수는 오버플로우가 날 수도 있다.

    }
}
