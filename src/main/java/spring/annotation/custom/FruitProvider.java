package spring.annotation.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    
	//��Ӧ�̱��
    public int id() default -1;
    
    //��Ӧ������
    public String name() default "";
    
    //��Ӧ�̵�ַ
    public String address() default "";
}
