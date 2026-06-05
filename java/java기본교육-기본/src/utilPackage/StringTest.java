package utilPackage;

import java.nio.charset.Charset;

public class StringTest {
    public void baseTest1() {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        String str4 = new String("abc");
        String str5 = new String("abc").intern(); // 상수 풀에 존재하는지 확인하고 존재하면 그 문자열의 주소값을 반환해줌.

        System.out.println(str1 == str2); // T
        System.out.println(str1 == str3); // F
        System.out.println(str3 == str4); // F
        System.out.println(str1.equals(str1)); // T
        System.out.println(str1.equals(str3)); // T
        System.out.println(str3.equals(str4)); // T

        System.out.println(str1 == str5); // T
        System.out.println(str3 == str5); // F
        System.out.println(str3.intern() == str4.intern()); // T

        // new String 도 스트링 리터럴을 상수 풀에 올림
        // 컴파일 시 모든 리터럴을 확인하고 올림.
        String str6 = new String("str");
        String str7 = "str";
        System.out.println(str6==str7); // f
        System.out.println();

        String str8 = new String("aaa").intern();
        String str9 = "aaa";
        System.out.println(str8 == str9); // t
    }

    // stringbuffer 클래스는 새로운 문자열을 같은 변수에 할당할 때 기존 참조된 주소에 변경된 문자열을 할당할 수 있다.
    // 즉 상수 풀에 새롭게 등록되거나 새로운 string 객체를 만드는 것이 아닌 기존에 만들어진 객체에서 수정이 가능하다.
    public void stringBufferTest() {
        StringBuffer s = new StringBuffer(20);

        // 길이를 지정하지 않으면 16개의 문자를 저장할 수있는 크기의 버퍼를 생성함.
        // 아래처럼 바로 문자열을 생성자로 생성해주면, 문자열길이+ 16 길이의 버퍼가 생성됨.
        StringBuffer str1 = new StringBuffer("abc");
        StringBuffer str2 = new StringBuffer("abc");

        // stringBuffer 는 equals 메서드를 오버라이딩하지 않아 (==) 연산자와 같은 값을 얻게 됨. 즉 주소값을 비교함.
        // 그래서 보통 toString 으로 equals 메서드 비교를 해줌.
        System.out.println(str1 == str2); // F
        System.out.println(str1.equals(str2)); // F
        System.out.println(str1.toString() == str2.toString()); // F
        System.out.println(str1.toString().equals(str2.toString())); // T

        System.out.println(str1.capacity()); // 버퍼 크기
        System.out.println(str1.length()); // 담긴 문자열 크기

        s.append("abc");
        s.insert(2, "f");
        System.out.println(s.capacity());

        StringBuffer s2 = new StringBuffer();
        s2.append("abc");
        System.out.println(s2.capacity());

        StringBuilder b = new StringBuilder();

        String abc = "";
        String abc2 = new String("");
        System.out.println(abc==abc2);

        // String abc3 = "";
        // String abc4 = new String("").intern();
        // System.out.println(abc3 == abc4);

        abc.toString();
        abc =null;
        String nu = String.valueOf(abc);
        String nu2 = null;
        System.out.println(nu == nu2);

    }

    // 멀티 쓰레드에 안전하도록 동기화되어 있음.
    // stringbuffer 는 동기화가 성능을 떨어뜨리기 때문에 stringbuffer 에서 쓰레드의 동기화만 뺀 것이 바로
    // stringbuilder 임.

}
