package toFloat;
// 과제 ieee754 (float 를 이진수로)
public class ToFloat {


    public char[] toFloat(char[] input) {

        char[] rs = new char[32];

        boolean isNegative = false;
        if (input[0] == 45) {
            isNegative = true;
            input[0] = '0';
            // TODO ascii 쓸거면 일관성있게 계속 쓰자.
        }
        String[] inputNum = String.copyValueOf(input).split("\\.");

        // 정수부분
        String integer = toBinaryString(Integer.parseInt(inputNum[0]));

        // 소수부분
        String decimal = "";
        // TODO 함정. float 를 쓰면 안됨. 문제와 모순적임.
        // int 로 계산할 때 자리수 확인을 log 함수 확인.
        // ex: 0.625 -> 625.  log100 < log625 < log1000. 10^2 승이니 즉 2+1 이 자리수
        float f = Float.parseFloat("0." + inputNum[1]);
        while (f > 0) {
            f *= 2;
            String s = String.valueOf(f);
            decimal += s.substring(0, 1);
            f = Float.parseFloat("0" + s.substring(1));
        
            if(decimal.length()==24) break; 
        }

        String binary = "";     // 정수이진수+소수이진수
        String sign = "";       // 부호비트
        String exponent = "";   // 지수
        String fraction ="";    // 가수

        if(integer.equals("0")){
            int e = 1;
            for(char c: decimal.toCharArray()){
                if(c == 49) break;    // 1
                e ++;
            }

            if(decimal.equals("")){
                decimal = "0"; 
                exponent = "0";
            }else{
                binary = decimal.substring(e);
                exponent = toBinaryString(127-e);  // 127 + 2^(-1)
            }

        }else{
            binary = integer.substring(1) + decimal;
            exponent = toBinaryString(integer.length() - 1 + 127); // 지수 127 = 2^(8)/2 -1
        }
        
        if (binary.length() > 23) {
            binary = binary.substring(0, 23);
        }

        sign = isNegative ? "1" : "0";
        exponent = String.format("%s"+exponent, "0".repeat(8-exponent.length()));
        fraction = String.format(binary + "%s", "0".repeat(23 - binary.length())); 

        rs = (sign + exponent + fraction).toCharArray();

        return rs;

    }

    public String toBinaryString(int i) {

        // 나머지 i & 0x01
        // 나누기 i >> 1 
        // 반복
        // 보통 2진수는 비트연산을 이런식으로 많이 사용함.
        // TODO 바꿔보자

        // 문자열 붙이기는 웬만해선 비추.
        // 계속 메모리 재할당 되니
        // 할거면 char 배열이나 stringbuilder 사용

        String rs = "";
        while (i != 0) {
            rs = String.valueOf(i % 2) + rs;
            i /= 2;
        }
        if(rs.equals("")) rs = "0";
        return rs;
    }
}
