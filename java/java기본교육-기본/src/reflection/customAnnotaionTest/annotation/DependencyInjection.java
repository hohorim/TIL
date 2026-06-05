package reflection.customAnnotaionTest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
        ElementType.TYPE, 
        ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) // 런타임 시까지 사용
public @interface DependencyInjection {
    String name() default "person";
}
