package reflection.customAnnotaionTest;

import java.lang.reflect.InvocationTargetException;

import reflection.customAnnotaionTest.annotation.ContextContainer;
import reflection.customAnnotaionTest.annotation.DependencyInjection;

public class Test {
    @DependencyInjection(name = "horim")
    Person person2;
    {
        try {
            person2 = ContextContainer.get(Person.class);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void tesT(){
        System.out.println(person2.getValue());
        System.out.println();
    }
}
