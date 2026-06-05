package generics.vehicle;

import java.util.ArrayList;
import java.util.List;

public class Vehicle<E extends LandVehicle>  {
    List<E> item = new ArrayList<>();
    public void add(E item){this.item.add(item);};
}
