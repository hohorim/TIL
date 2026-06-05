package reflection;

import reflection.customAnnotaionTest.annotation.DependencyInjection;

public class Child extends Parent{
    static String staticStr;
    private final String priFinStr;
    public String str;
    int age;

    private int pri;
    
    public Child(){
        super("parent");
        this.age = 27;
        this.priFinStr = "priFinStr";
    }
    
    public Child(int age){
        super("parent");
        this.age = age;
        this.priFinStr = "priFinStr";
    }
    
    
    public int getAge(){
        return this.age;
    }
    
    public void method1(String str1, int i){
        System.out.println("method1");
    }
    public String method2() throws RuntimeException{
        System.out.println("method2");
        return "method2 return";
    }

    public int wrapperTest(Integer i1, int i2){
        
        return i2 +=i1;
    }

}