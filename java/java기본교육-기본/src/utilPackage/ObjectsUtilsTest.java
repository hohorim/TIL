package utilPackage;

import java.util.Objects;
import java.util.Random;

public class ObjectsUtilsTest {
    public void test(){
        Circle c1 = new Circle(1, 2);
        Circle c2 = new Circle(1, 2);

        System.out.println(c1.equals(c2));  // c1 이 null 이면 안됨. 그래서 null 체크를 해줘야함.
        System.out.println(c1 == c2);
        System.out.println(Objects.equals(c1, c2)); // Objects class 의 equals 메소드는 매개변수의 null을 체크해주기 때문에 null 따로 체크 안해줘도됨,
        // System.out.println(Objects.equals(null, c2));  // 이렇게 매개변수가 null 이어도 됨.
        
        String[][] str1 = new String[][]{{"aaa","bbb"},{"aaa","bbb"}};
        String[][] str2 = new String[][]{{"aaa","bbb"},{"aaa","bbb"}};
        String[][] str3 = new String[][]{{"aaa","bbb"},{"aaa","bbc"}};

        System.out.println();
        // 재귀적으로 비교하기 때문에 다차원 배열 비교 가능
        System.out.println(Objects.deepEquals(str1, str2));
        System.out.println("hoho");
        System.out.println(Objects.deepEquals(str1, str3)); // f
        System.out.println();


        Object obj = new Object();

        // hashCode(), toString() 메소드도 Objects class 는 널검사를 한다.
        System.out.println(obj.hashCode());
        System.out.println(Objects.hashCode(obj)); // null 이면 0 반환


        Random random = new Random(3);
        System.out.println(random);



        // Circle c3 = new Circle(4, 5);
        // Circle c4 = (Circle) c3.clone();

        // System.out.println(c3.p.x2);
        // c3.p.x2 = 6;
        // System.out.println(c4.p.x2);
        // System.out.println(c3.p.hashCode());
        // System.out.println(c4.p.hashCode());

        // System.out.println(c3.equals(c4));
        // System.out.println("hoho");
        // System.out.println(Objects.deepEquals(c3,c4));



        String[][] str10 = new String[][] { { "aaa", "bbb" }, { "aaa", "bbb" } };
        String[][] str11 = new String[][] { { "aaa", "bbb" }, { "aaa", "bbc" } };

        System.out.println(Objects.deepEquals(str10,str11)); // f


        Circle c5 =new Circle(4, 5);
        Circle c6 = c5.clone();

        System.out.println(c5.p.x2);
        c6.p = new PointClone(0, 0);
        System.out.println(c5.p.x2);

        System.out.println(c5 == c6);   
        System.out.println(c5.equals(c6));  
        System.out.println();
        System.out.println(Objects.deepEquals(c5,c6));  

        //----------------------------------------

        Circle c7 = new Circle(4, 5);
        Circle c8 = c7;

        System.out.println(c7.p.x2);
        c8.p = new PointClone(0, 0);
        System.out.println(c7.p.x2);

        System.out.println(c7 == c8);
        System.out.println(c7.equals(c8));
        System.out.println();
        System.out.println(Objects.deepEquals(c7,c8));


   

    }
}
