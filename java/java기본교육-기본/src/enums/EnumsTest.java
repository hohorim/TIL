package enums;

import java.lang.annotation.Annotation;

public class EnumsTest {
    public void test1(){
        Direction d1 = Direction.EAST;
        Direction d2 = Direction.valueOf("WEST");
        Direction d3 = Enum.valueOf(Direction.class, "EAST");

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        
        System.out.println(Direction.values()[0]);

    }

    public void test2(){
        System.out.println(Transportation.BUS.fare(100));
        System.out.println(Transportation.TRAIN.fare(100));
        System.out.println(Transportation.SHIP.fare(100));
        System.out.println(Transportation.AIRPLANE.fare(100));
        System.out.println(Transportation.AIRPLANE.ordinal());
    }

    public void annotationtest(){
        Annotation a = null;
    }
}
