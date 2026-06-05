package etc;

import java.util.AbstractSet;
import java.util.Map;

public class Child extends Parent{
    int c = 1;
    // String qs = "qs!";

    String test2 = qs;

    void print(){
        System.out.println(this.c);
    }
    void test(int i){
        this.c = i;
        String test1 = super.qs;
        
        System.out.println(test1 + " " + test2);
    }

    final public class Chile2 {
        void test4(){

        }
    }

    public static class Chile3 {
        void test4() {

        }
    }
}
