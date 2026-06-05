package etc;
public class InnerClass {
    int a = 0;
    public static class AClass{
        InnerClass c = new InnerClass();
        int b = c.a;
    }

    private class BClass {

    }

    {
        class CClass{

        }
    }

    public void methodA(){
        class DClass{

        }
    }
    
}
   
