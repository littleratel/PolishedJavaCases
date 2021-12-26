package spring.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;

import javax.annotation.Resource;

/**
 * 当调用getBean("Benz") 时，Spring通过反射机制发现 Benz 实现了FactoryBean的接口，
 * 这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
 * 如果希望获取CarFactoryBean的实例，
 * 则需要在使用getBean(beanName) 方法时在beanName前显示的加上 "&" 前缀.
 * 例如getBean("&car")
 */
@Resource
public class Benz implements FactoryBean<Car> {

    private Car instance;

    @Override
    public Car getObject() throws Exception {
        if (instance == null) {
            instance = new Car();
            instance.setBrand("Benz");
            instance.setPrice(69.0);
        }

        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public String toString(){
        return instance.toString();
    }
}
