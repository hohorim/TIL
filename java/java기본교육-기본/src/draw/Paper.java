package draw;
public class Paper{

    Shape shape = new Shape();
    
    // paper 에 shape 배열을 넘기면 해당 배열을 모두 출력하는 draw 메소드 생성
    void draw(char[] c){
        shape.draw(c);;
    }
   
    
}
