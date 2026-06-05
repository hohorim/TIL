package generics.method;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Optional;

public class Box2<T> {


    public static <T extends Color> void getInstance(ColorBox<T> item){ 
        
    }

    // public static void printAll(ArrayList<? extends Color> list, ArrayList<? extends Color> list2){

    // }
    // 위와 같이 매개변수안이 복잡한 선언부를 아래와 같이 간략히 할수 있다.
    public static <T extends Color> void printAll(ArrayList<T> list, ArrayList<T> list2){

    }

    public void test(){
        // 다형성

        ColorBox colorBox = null;
        ColorBox<Red> objColorBox = null;
        colorBox = (ColorBox) objColorBox;
        objColorBox = (ColorBox<Red>) colorBox;

        Box2<Object> objCol = null;
        Box2<String> stringCol = null;

        // 불가능
        // objCol = (Box2<Object>)stringCol;
        // stringCol = (Box2<String>) objCol;

        // 불가능
        // Box2<Object> wBox = new Box2<String>();

        Box2<? extends Object> wbox = new Box2<String>();
        ColorBox<? extends Color> red = new ColorBox<Red>();
        ColorBox<? extends Color> blue = new ColorBox<Blue>();


        ColorBox<Color> blue2= new ColorBox<Color>();
        // ColorBox<Color> blue3= new ColorBox<Blue>();
        ColorBox<Blue> blue3= new ColorBox<Blue>();

        FruitBox5<Apple5> fr= new FruitBox5<>();

    }
}