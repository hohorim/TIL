package reflection.customAnnotaionTest.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ContextContainer {
    public static <T extends Object> T get(Class<T> t) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Object s = null;
        Constructor constructor = t.getConstructor(String.class);
        s = constructor.newInstance("f");
        Annotation annotation= t.getAnnotation(DependencyInjection.class);


        // Field[] field = t.getDeclaredFields();
        // for(int i =0; i<field.length; i++){
        //     if(annotation != null){
        //     }
        // }
        
        // Method[] methods = t.getDeclaredMethods();
        // for(int i =0; i<methods.length; i++){

        //     Method m = methods[i];
        //     Class[] type =m.getParameterTypes();
        //     m = t.getDeclaredMethod(methods[i].getName(), type);
            
        //     System.out.println();
        // }

        return (T)s;

    }
}
