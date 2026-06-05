package reflection.customAnnotaionTest;

import reflection.customAnnotaionTest.annotation.DependencyInjection;

public class Person {
    
    public String value;

    public String getValue(){
        return this.value;
    }

    public Person(String value){
        this.value = value;
    }


}
