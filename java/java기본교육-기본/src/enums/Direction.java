package enums;

import java.lang.annotation.Annotation;

public enum Direction {
    EAST(1), SOUTH(5), WEST(-1), NORTH(10);
    // 각 상수는 객체. 상수가 가지고 있는 값은 객체의 주소. == 비교가능
    private final int value;
    Direction(int value){this.value = value;}
    public int getValue(){ return value;}

    
}

