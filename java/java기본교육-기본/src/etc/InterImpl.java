package etc;

public class InterImpl implements Inter, Inter.Inter2{
    

    @Override
    public void test() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void test1() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void test2(){
        System.out.println("test2!");
    }


    public void inner(){
        
    }

    @Override
    public void test234() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test234'");
    }

    
}
