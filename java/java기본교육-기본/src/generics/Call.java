package generics;

import java.util.Collection;
import java.util.Collections;

import generics.base.Apple;
import generics.base.Box;
import generics.base.FruitBox;
import generics.extend.Apple2;
import generics.extend.Bus;
import generics.extend.Car;
import generics.extend.Eatable;
import generics.extend.EatableChild;
import generics.extend.Fruit2;
import generics.extend.FruitBox2;
import generics.extend.FruitBox_ex;
import generics.extend.Grape2;
import generics.extend.GreenApple;
import generics.extend.IVehicle;
import generics.extend.Race;
import generics.extend.Red;
import generics.extend.Vehicle;
import generics.whildCard.Fruit3;
import generics.whildCard.FruitBox3;
import generics.whildCard.Juice;
import generics.whildCard.Juicer;
import generics.whildCard.Orange;
import generics.whildCard.PrimTest;

public class Call {
    
    public void test1(){
        Box<String> box1 = new Box<>(); // 타입 매개변수에(<T>) 타입을 지정하는 것: 지네릭 타입 호출
        // 지정 된 타입 String : 매개변수화된 타입(= 대입된 타입)
        
        Box<Object> box2 = new Box<>();
        box2.setItem(1);
        box2.setItem("2");
        System.out.println(box2.toString());

        box2.myToArray(new String[]{"a","b","c"});
        box2.myToArray(new Integer[]{1,2,3,4,5});
        
    }

    public void test2(){
        // Box<Fruit> box3 = new Box<Apple>();  // 참조변수와 대입된 타입이 일치해야함
        Box<Apple> fruit = new FruitBox<Apple>();   // 두 지네릭 클래스가 상속관계이면서 대입된 타입이 같은것은 가능
        
    }

    public void test3(){
        FruitBox2<Fruit2> box3 = new FruitBox2<>();
        box3.setItem(new Apple2("d"));
        box3.setItem(new Fruit2());
        // box3.setItem(new Grape2()); 이건 안됨. Grape2 는 Fruit2의 자손이 아니니.

    }
    
    public void test4(){

        GreenApple<Apple2> apple = new GreenApple<>();
        GreenApple<Apple2> apple2 = new GreenApple<>();

        FruitBox_ex<Apple2> apple3 = new FruitBox_ex<>();   // apple2 에서 fruit2 를 상속받고, fruit2에서 etable을 상속받고잇음
    }

    public void test5(){
        FruitBox3<Fruit3> box4 = new FruitBox3<>();
        FruitBox3<Orange> box5 = new FruitBox3<>();

        Juicer.makeJuice(box4);
        Juicer.makeJuice(box5);
        
        Juicer.test2(new PrimTest<Object>("dsf"));

        
    }
    public void test6(){
        FruitBox_ex<Fruit2> ex = new FruitBox_ex<>();

    }

    public void test7(){
        // Race<Vehicle> race = new Race<>();
        Race<Bus> race2 = new Race<>();
        // Race<Car> race2 = new Race<>();  
        // 버스는 Vehicle의 자손이면서 IVehicle 도 구현했지만, Car는 Vehicle 자손이지만 Ivehicle은 구현하지 않아서 에러남.
    }

    
}
