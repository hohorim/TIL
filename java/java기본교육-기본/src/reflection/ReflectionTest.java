package reflection;
// 구체적인 클래스 타입을 알지 못해도 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.rmi.server.ObjID;

import javax.swing.text.AbstractDocument.Content;

import reflection.customAnnotaionTest.annotation.DependencyInjection;

public class ReflectionTest {

    public void getClassInfo() throws Exception{
        Class<?> child = Class.forName("reflection.Child");
        Class<?> child2 = Child.class;
        

        Integer i = 37;
        // 인스턴스 비교
        System.out.println(child.isInstance(i));                // false
        System.out.println(child.isInstance(new Child()));    // true

    }

    public void test1() throws Exception{
        Class clazz1 = Class.forName("java.lang.String");
        Class clazz2 = int.class;
        Class clazz3 = Integer.class;
        Class clazz4 = Integer.TYPE;    // wrapper 클래스에 경우 기본형으로 가져옴.
        
        System.out.println(clazz2 == clazz4);   // true
        System.out.println(clazz3 == clazz4);   // false
    }

    public void reflectMethods() throws Exception{
        Class clazz = Child.class;
        // getDeclared~ 는 private 포함해서 가져옴.
        // get~ 는 public만.

        Method[] methods = clazz.getDeclaredMethods();      // class 자신의 메소드 정보
        Method[] methods2 = clazz.getMethods();             // 상속된 메소드 정보
        Field[] fields = clazz.getDeclaredFields();         // 자신의 필드
        Field[] fields2 = clazz.getFields();                
        Constructor[] constructors = clazz.getConstructors();   
        Constructor[] constructors2 = clazz.getDeclaredConstructors(); 
        Class[] interfaces = clazz.getInterfaces();
        Class superClazzs = clazz.getSuperclass();

        // 메서드
        for(int i =0; i<methods.length; i++){
            Method method = methods[i];
            System.out.println(method.getName());
            System.out.println(method.getDeclaringClass());
            System.out.println(method.getParameterTypes());
            System.out.println(method.getExceptionTypes());
            System.out.println(method.getReturnType());
            
            System.out.println();
        }

        // 생성자
        for(int i =0; i<constructors.length; i++){
            Constructor constructor = constructors[i];
            System.out.println(constructor.getName());
            System.out.println(constructor.getDeclaringClass());
            System.out.println(constructor.getParameterTypes());
            System.out.println(constructor.getExceptionTypes());
            
            System.out.println();
        }

        // 필드
        for(int i =0; i<fields.length; i++){
            Field field = fields[i];
            System.out.println(field.getName());
            System.out.println(field.getDeclaringClass());
            System.out.println(field.getType());
            int mod = field.getModifiers();     // 제어자(public, final, static 등..)
            System.out.println(mod);
            System.out.println(Modifier.toString(mod));
            
            System.out.println();

        }
    }

    public void runMethod() throws Exception{
        Class clazz = Child.class;
        
        Class[] types = new Class[2];
        types[0] = String.class;
        types[1] = int.class;   // Integer.class 안됨. 메서드의 매개변수 타입이 래퍼클래스랑 기본형 타입을 구분함.
        Method method = clazz.getMethod("method1", types);  // 메서드 생성

        Child child= new Child();
        Object[] args = new Object[2];
        args[0] = "abc";
        args[1] = 3;
        Object rsObj = method.invoke(child, args);  // 메서드 호출
        
        System.out.println(rsObj);
        
        //======================================================================
        types[0] = Integer.class;   
        types[1] = int.class;   
        method = clazz.getMethod("wrapperTest", types);  // 메서드 생성

        args[0] = 4;
        args[1] = 3;
        Object rsObj2 = method.invoke(child, args);  // 메서드 호출
        System.out.println(rsObj2);

        //-------------------------------
        method = clazz.getDeclaredMethod("wrapperTest", types);
        Object rsObj3 = method.invoke(child, args);
        System.out.println(rsObj3);

        System.out.println();
    }

    public void createInstance() throws Exception{
        Class clazz = Child.class;

        Constructor constructor = clazz.getConstructor(int.class);  // 생성자 생성
        // 생성자만들 때도 매개변수 타입이 래퍼인지 기본형인지 구분함.

        Object rs =constructor.newInstance(30); // 객체 생성
        System.out.println(rs);
        
        System.out.println();
    }

    public void changeField()throws Exception{
        Class clazz = Child.class;

        Field field = clazz.getField("str");    // 필드 가져오기

        Child child = new Child();
        System.out.println("before: " +Child.staticStr);

        field.set(child, "hello~");         // 필드 값 변경.
        //  final 변경안됨(에러 발생)

        System.out.println("after: " + Child.staticStr);
        System.out.println();

        //==========================================

        field = clazz.getDeclaredField("pri"); // 필드 가져오기

        field.setAccessible(true);  // priavte 인스턴스 멤버변수를 접근가능 설정
        field.setInt(child, 10); // 필드 값 변경.

        System.out.println();

    }

    public void arrayTest() throws Exception{
        Class stringClazz = Class.forName("java.lang.String");
        
        Object arr = Array.newInstance(stringClazz, 10);  // String class 라는 10개의 배열 생성
        Array.set(arr, 5, "this is a test");    // 5 index 값 set
        System.out.println();

        // =================================
        int[] intArr= new int[]{5,2,3}; // 차원 배열 만들기 가능
        arr = Array.newInstance(Integer.TYPE, intArr);  // 마찬가지로 래퍼클래스 기본형 구분함.

        Object arrObj = Array.get(arr,1);
        Object arrObj2 = Array.get(arrObj, 1);
        Array.setInt(arrObj2,1, 10);

        System.out.println();
        
    }


}

