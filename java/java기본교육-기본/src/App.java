import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import collection.ArraysTest;
import collection.ComparatorTest;
import collection.IteratorTest;
import collection.MapTest;
import collection.SetTest;
import enums.EnumsTest;
import etc.Child;
import exception.Exception1;
import generics.Call;
import generics.base.Apple;
import generics.base.Box;
import generics.base.FruitBox;
import generics.extend.Apple2;
import generics.extend.Fruit2;
import generics.extend.FruitBox_ex;
import generics.fruitBoxEx4.FruitBoxEx4;
import generics.method.Box2;
import generics.method.Color;
import generics.method.ColorBox;
import generics.method.Red;
import generics.vehicle.Bus;
import generics.vehicle.Car;
import generics.vehicle.LandVehicle;
import generics.vehicle.Race;
import generics.vehicle.Vehicle;
import paper.Circle;
import reflection.ReflectionTest;
import reflection.customAnnotaionTest.Test;
import utilPackage.ObjectsUtilsTest;

public class App {

    public static void main(String[] args) throws Exception {
        Exception1 exp1 = new Exception1();

        exp1.test();
        System.out.println("----------------");
        exp1.test2();
    }
    
    
    public void test3(){
        
        // float a = 1.1f;
        
        // System.out.println(true ^ true);
        // System.out.println(false ^ true ^ true);
        // System.out.println(true ^ false ^ false);
        // System.out.println(false ^ false);
        // char d = 'd';
        // System.out.println(true && (byte)10000>0);


        // byte i = (byte)10000000;
        // System.out.printf("%d\n", i);
        // System.out.print((byte)0b10000000 +"\n");
        


        // System.out.printf("%d\n", 0b10000000);
        // System.out.println();
        // System.out.println(new String("test")==new String("test"));
        // System.out.println(0010==0b0010);
     

        // ToFloat f = new ToFloat();
        // System.out.println("--------------------------------------------");
        // System.out.println(f.toFloat(new char[] {'0','.','6','2','5'})); 
        // System.out.println(f.toFloat(new char[] { '-', '1', '2', '.', '4'})); 
        // System.out.println(f.toFloat(new char[] { '-', '1', '1', '8', '.', '6', '2', '5'})); 
        // System.out.println(f.toFloat(new char[] {'1','0','0','0','0','.','1','0','0','4','0','0','1','0','1','0','2','0','0','3'})); 
        // System.out.println(f.toFloat(new char[] { '1', '2', '.', '0'}));
        // System.out.println(f.toFloat(new char[] { '2', '5', '.', '6', '2', '5'}));
        // System.out.println(f.toFloat(new char[] {'0','.','0'}));
        // System.out.println(f.toFloat(new char[] { '0','.', '0', '0', '0', '0', '0', '0','0', '0', '0', '0', '0', '0','0',
        //         '0', '0', '0', '0', '0', '1',}));


        // Parent p = new Parent();
        // Child c1 = new Child();
        // Child c2=null;
        // p = c1;
        // c2 = (Child)p;

        // Parent p = new Child();
        // p.print();  //1
        // p.test(5);
        // p.print();  //5

        // Shape s = new Square();
        // s.draw("square".toCharArray());
        // Shape t = new TriAngle();
        // t.draw("triangle".toCharArray());

        // Paper paper = new Paper();
        // paper.draw("square".toCharArray());
        // paper.draw("triangle".toCharArray());

        // System.out.println();

        // InterImpl inter = new InterImpl();
        // inter.test2();

        // Child cs = new Child();
        // cs.test(2);



        // Parent p =new Parent();
        // p.i =2;
        // Parent.Child c = p.new Child();
        // c.methodA();

        Exception1 exp1 = new Exception1();
        
        // try{
        //     exp1.test6();

        // }catch(Exception ex){
        //     System.out.println("heo");
        // }
        // try{
        //     exp1.test7();

        // }catch(Exception ex){
        //     System.out.println("test7");
        // }

        // try{

        //     exp1.test10(false);
        // }catch(RuntimeException e){
        //     System.out.println("myExcep");
        // }
        // try{
        //     exp1.test5();
        // }catch(ArithmeticException ex){
        //     System.out.println("main error");
        // }
        // System.out.println("=======");
        // try {
        //     exp1.test5();
        // } catch (ClassNotFoundException ex) {
        //     System.out.println("main error");
        // }

        // MyWebServer webserver = new MyWebServer();
        // // String uri = "/index.html?name=horim&age=26";
        // String uri = "/index.do?name=horim&age=26"; // TODO html 을 do 로 바꿔서 인터셉터 만들기 과제
        // // String rs = webserver.request(uri);
        // System.out.println("=======");
        // System.out.println(uri);
        // System.out.println("=======");
        // // System.out.println(rs);
        // System.out.println("\n\n=====================================");
        // System.out.println(webserver.request("/index.html?name=horim&age=1"));
        // System.out.println(webserver.request("/index.html"));
        // System.out.println(webserver.request("/index.do?name=horim&age=1"));
        // System.out.println(webserver.request("/index.do"));
        // System.out.println(webserver.request("/indedox.do"));


        
        ObjectsUtilsTest util = new ObjectsUtilsTest();
        
        util.test();
    // String sfef = null;
    // System.out.println("horim");
    // System.out.println("null".getClass());
//         StringTest t= new StringTest();
//         t.stringBufferTest();

        // NumberClassTest nt = new NumberClassTest();
        // nt.test1();

        Child c = new Child();
        c.new Chile2(); // 인스턴스 내부클래스
        new Child.Chile3(); // 스태틱 내부클래스
     
        IteratorTest it = new IteratorTest();
        it.test2();

        ArraysTest at = new ArraysTest();
        at.test();


        String[] sArr = { "cat", "Dog", "lion", "tiger" };
        // String[] sArr = {"Cat", "dog", "lion","Tiger"};

        Arrays.sort(sArr);
        System.out.println("sArr" + Arrays.toString(sArr));

        System.out.println("======After String.CASE_INSENSITIVE_ORDER========");
        Arrays.sort(sArr, String.CASE_INSENSITIVE_ORDER);
        System.out.println("sArr" + Arrays.toString(sArr));

        Arrays.sort(sArr, new ComparatorTest());
        System.out.println("=====new Descending() ====");
        System.out.println("sArr" + Arrays.toString(sArr));

        SetTest sfdf = new SetTest();
        sfdf.test3();

        MapTest sdf = new MapTest();
        sdf.test1();

        System.out.println("=====================generics==============");
        Call call1 = new Call();
        call1.test1();


        FruitBoxEx4 ex = new FruitBoxEx4();
        ex.main();
        Object obj = new Object();
        FruitBox<Apple> apple = new FruitBox<>();
        Box.getInstance(apple);
        Box.<Apple>getInstance(apple);

        ColorBox<Color> col = new ColorBox<>();
        ColorBox<Red> red = new ColorBox<>();
        Box2.<Red>getInstance(red);
        Box2.<Color>getInstance(col);
        Box2.getInstance(red);
        Box2.getInstance(col);  // 컴파일러가 추정할 수 잇기 때문에 생략가능

        Apple2 apple3 = new Apple2("appe");
        // FruitBox_ex.test2(apple3);
        FruitBox_ex<Fruit2> ex3 = new FruitBox_ex<>();
        ex3.test4(apple3);
        ex3.test2(apple3);
        // Apple2 apple3 = new Apple2("appe");
        // FruitBox_ex2.test2(apple3);
        // FruitBox_ex2<Fruit2> ex3 = new FruitBox_ex2<>();
        // ex3.test4(apple3);
        FruitBox_ex efe = new FruitBox_ex<>();


        Vehicle<Car> car = new Vehicle<>();
        car.add(new Car());

        Vehicle<LandVehicle> land = new Vehicle<>();
        land.add(new Car());
        land.add(new Bus());

        Race<Car> race = new Race<>();

        race.baseReady(land);
        Race.ready(car);
        Race.ready(land);
        Race.staticReady(land);
        Race.ready2(land);
        Race.ready2(new Vehicle<Car>());
        
        Race<String> raceString = new Race<>();

        Race<? extends LandVehicle> race2 = new Race<LandVehicle>(); // new Race<>(); 반례
        //race2.add(new LandVehicle());  // error: 위 선언에서 제너릭은 자료형을 유추할 수 없음.
        // 컴파일 시점과 런타임 시점의 타입 유추시점이 좀 다름.. 음.
        // extends 를 위와같이 쓰는 경우는 잘 없고, 제너릭 클래스나 제너릭 메서드 생성할 용도로 많이 씀.

        Race<? super Car> race3 = new Race<LandVehicle>(); // super 는 자기자신 넣을 수 있는데 extends 는 자기자신을 넣을 수가 없음..
        race3.add(new Car());  

        Race<? super LandVehicle> race4 = new Race<>(); // why ? 컴파일 시점에서 되게 애매한 자료형이라서..?
        race4.add(new Car());
        race4.add(new LandVehicle());
        //race4.add(new Vehicle());   // error
        
        List<LandVehicle> item = new ArrayList<>();
        item.add(new LandVehicle());

        // 반례:
        // AClass -> BClass -> CClass 상속순서
        // Box 클래스에 제너릭에 T extends BClass 로
        // Box<? extedns AClass> = new Box<>();
        // Box<AClass> = null;  error 날거임.
        // 그래서 너무 애매해서 에러나는 거 아닐 까.


        
        System.out.println("=====================enum==============");
        EnumsTest enumsTest = new EnumsTest();
        enumsTest.test1();
        enumsTest.test2();
        
        System.out.println("=====================Reflection==============");
        ReflectionTest reflectionTest = new ReflectionTest();
        try{
            reflectionTest.getClassInfo();
            reflectionTest.test1();
            reflectionTest.reflectMethods();
            reflectionTest.runMethod();
            reflectionTest.createInstance();
            reflectionTest.changeField();
            reflectionTest.arrayTest();
        }catch(Exception e){
            System.out.println("reflection error");
        }
        
        System.out.println("=====================Custom Annotation==============");
        
        Test te1 = new Test();
        te1.tesT();
        // Person person = ContextContainer.get(Person.class);
        
        
        System.out.println();
        Circle cc1 = new Circle(1,1,1);
        Circle cc2 = new Circle(1,1,1);
        cc1.equals(cc2);
        System.out.println();
    
    }
    
    

}

