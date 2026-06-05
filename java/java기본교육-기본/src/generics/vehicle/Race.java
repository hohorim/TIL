package generics.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Race <T>{
 

    List<T> item = new ArrayList<>();

    public void add(T t){this.item.add(t);}
    
    public void baseReady(Vehicle<LandVehicle> t){

    }
    

    public static void ready(Vehicle<?> v){
        
    }

    public static <T extends LandVehicle> void staticReady(Vehicle<LandVehicle> t){
        Optional<?> optional = null;
        Optional<T> optional2 = (Optional<T>) optional;

        Vehicle<?> vc1 = t;
        Vehicle<T> vc2 = (Vehicle<T>) t;
        Vehicle<?> vc3 = t;
        Vehicle<T> vc4 = new Vehicle<>();
        Vehicle<?> vc5 = new Vehicle<>();
        System.out.println("");

        List<? extends Vehicle> list = new ArrayList<>();
        //list.add(new Vehicle());    // error   

        List<T> list2 = new ArrayList<>();
        // list2.add((T)new Vehicle());   // runtime error
        //list2.add(new Vehicle());       // error

    }

    public static <T extends LandVehicle> void ready2(Vehicle<T> t) {
        List<T> list2 = new ArrayList<>();
    }

    public void test(Vehicle<Car> c){
        T s = (T) c;
        LandVehicle df = (LandVehicle) c;
        Race<T> race2 = new Race<>();
        race2.add(s); 
    }

}
