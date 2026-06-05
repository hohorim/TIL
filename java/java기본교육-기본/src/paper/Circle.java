package paper;

public class Circle extends Shape {
    int x;
    int y;
    int radius;
    
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("circle: " + x + "/" + y + "/" + radius);
    }
}
