package generics.fruitBoxEx4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class FruitBoxEx4 {
    public void main() {
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();
        System.out.println(appleBox.hashCode());
        grapeBox.add(new Grape("green", 200));
        grapeBox.add(new Grape("green", 100));
        appleBox.add(new Apple("greenApple", 120));
        appleBox.add(new Apple("redApple", 110));
        appleBox.add(new Apple("greenApple", 150));

        System.out.println("fruitBox - " + grapeBox);
        System.out.println("appleBox - " + appleBox);

        Collections.sort(appleBox.getList(), new AppleComp());
        Collections.sort(grapeBox.getList(), new GrapeComp());

        Collections.sort(grapeBox.getList(), new Comp<Grape>());
        Collections.sort(appleBox.getList(), new Comp<Apple>());


        System.out.println("============After Collections.sort() by new GrapeComp/AppleComp()============    ");
        System.out.println("appleBox : " + appleBox);
        System.out.println("grapeBox : " + grapeBox);
        System.out.println();

        Collections.sort(appleBox.getList(), new FruitComp());
        Collections.sort(grapeBox.getList(), new FruitComp());

        System.out.println("============After Collections.sort() by new FruitComp()============    ");
        System.out.println("appleBox : " + appleBox);
        System.out.println("grapeBox : " + grapeBox);
        System.out.println();

    }

}

class Fruit {
    String name;
    int weight;

    Fruit(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return name + "(" + weight + ")";
    }
}

class Apple extends Fruit {
    Apple(String name, int weight) {
        // this.name = name;
        // this.weight = weight;
        super(name, weight);
    }
}

class Grape extends Fruit {
    Grape(String name, int weight) {
        super(name, weight);
    }
}


// 아래 AppleComp 와 GrapeComp 중복 선언을 이처럼 타입변수 T로 받아서 처리할수있음.
class Comp<T extends Fruit> implements Comparator<T>{
    public int compare(T t1, T t2){
        return t2.weight - t1.weight;
    }

}

class AppleComp implements Comparator<Apple> {

    public int compare(Apple t1, Apple t2) {
        return t2.weight - t1.weight;
    }
}

class GrapeComp implements Comparator<Grape> {
    public int compare(Grape t1, Grape t2) {
        return t2.weight - t1.weight;
    }
}

class FruitComp implements Comparator<Fruit> {
    public int compare(Fruit t1, Fruit t2) {
        return t2.weight - t1.weight;
    }
}// 위 코드는 책에 나와있는 것으로 중복선언을 위처럼 처리.

class FruitBox<T extends Fruit> extends Boxs<T> {
}

class Boxs<T> {
    ArrayList<T> list = new ArrayList<T>();

    public void add(T item) {
        list.add(item);
    }

    T get(int i) {
        return list.get(i);
    }

    ArrayList<T> getList() {
        return list;
    }

    int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}