package enums;

public enum Transportation {
    BUS(100) {
        @Override
        int fare(int distance) {
            return distance * BASIC_FARE;
        }
    }
    , TRAIN(150) {
        @Override
        int fare(int distance) {
            return distance * BASIC_FARE;
        }
    }
    , SHIP(100) {
        @Override
        int fare(int distance) {
            return distance * BASIC_FARE;
        }
    }
    , AIRPLANE(300) {
        @Override
        int fare(int distance) {
            return distance * BASIC_FARE;
        }
    };

    protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근가능

    Transportation(int basicFare){BASIC_FARE = basicFare;}

    public int getValue() {
        return BASIC_FARE;
    }

    abstract int fare(int distance);    // 열거형에 추상메서들르 선언하면 각 상수에서 구현해야한다.
}
