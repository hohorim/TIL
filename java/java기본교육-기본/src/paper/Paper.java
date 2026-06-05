package paper;
public class Paper {
    public void draw(Shape[] shapes) {
        for(Shape s : shapes) {
            s.draw();
        }
    }
}
