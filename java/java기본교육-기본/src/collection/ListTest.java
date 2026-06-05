package collection;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ListTest {
    // vector, ArrayList, LinkedList
    public void test(){
        List<String> vl = new Vector<>();
        List<String> sl = new Stack<>();
        List<String> al = new ArrayList<>();
        List<String> ll = new LinkedList<>();   // 더블 링크드 리스트로 구현되어있음. 다음 요소 참조뿐만 아니라 이전 요소 참조도 가지고있음.
        // deque: fifo, 양쪽 전부 데이터를 삽입, 삭제 가능
        // queue: fifo, 양쪽에서 각각 데이터 삽입, 삭제
        // stack: lifo, 한쪽에서 데이터 삽입, 삭제
        
        // stack 은 class 로 구현되어있는반면, queue 는 인터페이스만 정의되어있음. queue 인터페이스를 구현한 클래스로 사용해야함. ex) LinkedList, ArrayDeque
        Queue<String> q = new LinkedList<>();
        Queue<String> d = new ArrayDeque<>();
        
        
        List<String> array1 = new ArrayList<>(20);
        List<String> linked1 = new LinkedList<>();

        
        Queue q2 = new PriorityQueue<>(); // 저장한 순서와 관계없이 우선순위가 높은 것부터 꺼냄(숫자가 작을 수록 높음). null 저장할 수 없음. 저장하면 NPE 발생
        // 저장공간은 배열을 사용하며, 각 요소를 힙이라는 자료구조 형태로 저장.

        Stack<Integer> stc = new Stack<>();
        List<Integer> que = new LinkedList<>();
        


    }
    
}
