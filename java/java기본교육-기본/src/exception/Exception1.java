package exception;

import java.io.IOError;
import java.io.IOException;

import mywas.Config;

public class Exception1{
    int number = 100;
    int result = 0;

    public void test(){
        for(int i =0; i<10; i++){
            try{
                result=number/(int)(Math.random()*10);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("0");
            }
        }
    }

    public void test2(){
        
        try{
            for(int i =0; i<10; i++){
                result=number/(int)(Math.random()*10);
                System.out.println(result);
            }
        }catch(ArithmeticException e){    
            System.out.println("0");
        }
        System.out.println(123);
    }

    public void test3() {

        try {
            for (int i = 0; i < 10; i++) {
                result = number / (int) (Math.random() * 10);
                System.out.println(result);
            }
            throw new IOException();
        } catch (IOException | RuntimeException e) { 
            System.out.println("0");
        } catch(Exception e){

        }finally{
            System.out.println("끝");
        }
    }

    public void test4(){
        try(FileInputStream stream = new FileInputStream()){
            stream.input();
        }catch(Exception e){
            
        }
    }

    public void test5() throws ArithmeticException{
        try{
            throw new NullPointerException("NullPointerException 에러");
            // throw new ClassCastException("ClassCastException 에러");
             
        }catch(NullPointerException ex){
            ex.printStackTrace();
            
            throw new ArithmeticException("ArithmeticException 에러");
        }catch(ArithmeticException ex){
            ex.printStackTrace();
        }
    }

    public void test6()  {
        throw new NullPointerException("에러");
        // unchecked  예외라서 exception 선언 필요 없음
    }
    public void test9()  {
        throw new RuntimeException("에러");
        // unchecked  예외라서 exception 선언 필요 없음
    }

    public void test7() throws Exception {
        throw new ClassNotFoundException("에러");
        // checked 예외라서 반드시 필요
    }
    public void test8() {
        throw new MyException("에러");
        // myexception 에서 unchecked인 runtimeeception을 상속받고있어서 선언 x
    }
    public void test10(boolean b) throws MyException3{
        if(b){
            throw new MyException3("3 error");
        }

        if(!b){
            throw new RuntimeException(new MyException2("2 에러"));
            // 위로 하여금 check 예외를 uncheck 예외로 던질 수 있다.

        }
    }

    public String test11() throws Exception{
        String str = "";
        try {
            throw new Exception();  //1
        } catch (Exception ex) {
            str = "1";  //2
            return str; //4
        } finally {
            str = "2";  //3
        }

        // 참조객체가 아닌 것들은 finally str이 다른 주소로 만들어져서 str return 이 1이나오게됨.
        // list 같은 참조객체는 같은 주소니 뭐 finally값 적용됨.
        
        

    }
	// 깨알상식:
	// @Transactional 은 unchecked exception 만 rollback 된다.
	// 왜냐하면 @Transactional(rollbackOn = RunTimeException.class) rollbackOn 을 설정하지 않으면 runtimeException 이 디폴트다.
	// 이는 runtimeException 만 rollback 하겠다는 것이고 만약 Exception.class 로 바꾸면 RuntimeException 이 아니더라도 적용이 된다.
	// method1() db 저장 후, method2() 를 호출하여 method2() 에서 db 저장 후 RuneTimeException 을 내면 method1()까지 rollback이 적용됨.
}
