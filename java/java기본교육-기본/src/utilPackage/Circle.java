package utilPackage;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Cloneable {
    int x;
    int y;
    PointClone p;
    public Circle(int x, int y) {
       this.x =x;
       this.y =y;
       p = new PointClone(x, y);
    
    }

    public Circle clone() {
        Object obj = null;

        try {

            obj = super.clone();
            // PointClone p = (PointClone) obj;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }   


        return (Circle) obj;
    }
}
