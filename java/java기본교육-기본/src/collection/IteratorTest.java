package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class IteratorTest implements ListIterator {
    public void test(){
        List<String> al = new ArrayList<>();
        ListIterator<String> listiterator = al.listIterator(); // 양방향 이동. List인터페이스를 구현한 클래스만 가능.
        Iterator<String> iterator = al.iterator(); // 단방향 이동

        Set<String> set = new HashSet<>();
        set.iterator();
    }

    public void test2(){
        ArrayList arr1= new ArrayList<>(10);
        ArrayList arr2= new ArrayList<>();
        ArrayList arr3= new ArrayList<>();

        for(int i=0; i<10; i++) arr1.add(i+"");

        Iterator it = arr1.iterator();
        
        while(it.hasNext()){
            arr2.add(it.next());
        }
        
        it = arr1.iterator(); // 디시 넣어줘야함. hashNext() 에서 이미 끝까지 돌고 마지막 인덱스를 들고 있기때문.
        

        while(it.hasNext()){
            arr3.add(it.next());
        }

        System.out.println(arr1);
        System.out.println(arr2);
        System.out.println(arr3);
    }




    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Object next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPrevious'");
    }

    @Override
    public Object previous() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'previous'");
    }

    @Override
    public int nextIndex() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextIndex'");
    }

    @Override
    public int previousIndex() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'previousIndex'");
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(Object e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public void add(Object e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}
