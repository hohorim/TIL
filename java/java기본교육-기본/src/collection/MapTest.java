package collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {
    // HashTable, HashMap, SortedMap,LinkedHashMap, TreeMap
    public void test(){
        // set 은 중복을 허용하지 않음. int 1과 Integer 1 은 다른 객체이므로 가능
        Map<String, String> table = new Hashtable<>();  // 이건 쓰지말자. 너무 구형. 쓰레드에서 이슈가 있음.
        Map<String, String> hash = new HashMap<>();
        Map<String, String> link = new LinkedHashMap<>();
        Map<String, String> sort = new TreeMap<>();
        
        // 인터페이스 Map 안에 Entry 내부인터페이스가 선언되어있다. key-value 값을 다루기위해. 
        // hashmap 처럼 구현클래스 보면 내부클래스가 Map.Entry 를 implement 로 받고있다.
        hash.entrySet().size();
        
        
    }   

    public void test1(){
        Map<String, String> b = new HashMap<>();
        b.put("a", "1");
        b.put("sd", "532");
        b.put("e", "12");
        b.put("2d", "522");
        b.put("A23", "232");
        b.put("A", "232");

        Set<Map.Entry<String,String>> etry = b.entrySet();

    }

    public void test2(){

    }


}
