package etc;

public class Parent {
    String qs = "qs~";

    public int i =1;

    class Child extends Parent {

        int a = i;
        int w = super.i;
        int s = this.i;
        

        void methodA() {
            System.out.println(i+ " " + s);
            
        }
    }
}
