package generics.extend;

public class Race <T extends Vehicle & IVehicle>{
    // Vehicle 이  인터페이스를 구현해야한다면 위처럼 써주면 vehicle 클래스에 강제로 구현하는 제약이 추가됨.
    // 즉 Race 는 Vehicle의 자손이면서 IVehicle을 구현한 클래스만 타입 매개변수 T에 넣을 수있다.
}
