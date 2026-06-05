package generics.extend;

import java.util.ArrayList;

import generics.base.Fruit;

public class FruitBox2<T extends Fruit2> {  // 특정 자손타입만 지정가능
    ArrayList<T> arrayList = new ArrayList<>();

    public void setItem(T item){
        arrayList.add(item);
    }

    public void toSTring(T item){ System.out.println(item);}
}
