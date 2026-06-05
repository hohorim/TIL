package utilPackage;

public class PointClone implements Cloneable  {
    int x2, y2;

    PointClone(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public String toString() {
        return "x2= " + this.x2 + " , y2= " + this.y2;
    }

    // // Cloneable 의 clone 반환은 Object 이지만 공변반환타입이라해서 자손 클래스 타입으로 반환타입 변경이 가능하다.
    // // clone 함수는 얕은 복사.
    // // 깊은 복사를 위해서는 참조의 참조의 참조까지 전부 새로운 복사를 말하는 것임.
    public Object clone() {
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

}
