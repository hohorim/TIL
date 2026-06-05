package generics.extend;

import java.util.ArrayList;

import generics.base.Fruit;

// 타입을 클레스가 아닌 인터페이스를 넣고 싶을 때 implement 가 아닌 extends 로 받는다. <T extends Eatable>
public class FruitBox_ex<T extends Fruit2 & Eatable>  {  // & 기호는 Fruit의 자손이면서 인터페이스도 구현해야할 때.
    // 대신 Fruit2 에는 Eatable 을 implement 하고 있어야한다.

    ArrayList<T> arrayList = new ArrayList<>();

    public void setItem(T item){
        arrayList.add(item);
    }

    public static <T extends Fruit2 & Eatable> void test(T item){
        System.out.println(item);
    }

    public static <T extends Fruit2> void test2(T item){
        System.out.println();
    }

    public static <T> void test3(T item){
        System.out.println(item.toString());
        
    }
    
    public void test4(T items){
        
        System.out.println(items.toString());
        
    }
    public static <T extends Color> void test5(T item){
        System.out.println(item.toString());
    }
}
