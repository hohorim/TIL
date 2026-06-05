package utilPackage;

public class NumberClassTest {
    public void test1() {
        int i = Integer.parseInt("100"); // int 기본형 반환
        int i2 = Integer.valueOf("100"); // Integer 래퍼클래스 반환
        // 성능은 valueOf 가 더느림

        // int it = null;
        Integer iw = null;
        Integer ia = 3;
        System.out.println(iw.intValue());
    }
}
