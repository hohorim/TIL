package paper;
public class Rectangle extends Shape {
    int x;
    int y;
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("rectangle: " + x + "/" + y + "/" + width + "/" + height);
    }  
}
