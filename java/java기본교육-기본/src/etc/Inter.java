package etc;
public interface Inter {
    public interface Inter2{
        public void test234();
    }
    public void test();
    public abstract void test1();
    public default void test2(){System.out.println("test2");}
    public static void test3(){}

}

