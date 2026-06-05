package utilPackage;

public class CloneTest {

    public void test1() {
        PointClone p = new PointClone(2, 4);

        // Object 의 clone 함수를 호출하기 위해서는 Cloneable 인터페이스를 구현한 객체여야만 한다.
        p.clone();
    }

}
