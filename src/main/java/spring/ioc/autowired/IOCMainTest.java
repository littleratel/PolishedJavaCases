package spring.ioc.autowired;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 模拟Spring解决循环依赖的问题
 *
 * 如果注入的目标对象，是一个需要被代理的对象（比如该方法被AOP代理），这种写法就无能为力了
 * */
public class IOCMainTest {
    private final Map<String, Object> singletonObject = new HashMap<>(8);
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(8);

    @Test
    public void mainTest() throws IllegalAccessException, InstantiationException {
        AService bean = getBean(AService.class);
        System.out.println(bean);
    }

    public <T> T getBean(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        String beanName = tClass.getSimpleName();

        //先查询一级缓存是否有数据
        Object object = singletonObject.get(beanName);

        //一级缓存没有在查询二级缓存是否有数据
        if (object == null) {
            object = earlySingletonObjects.get(beanName);
            if (object == null) {
                object = createBean(tClass, beanName);
            }
        }
        return (T) object;
    }

    public Object createBean(Class<?> tClass, String beanName) throws IllegalAccessException, InstantiationException {
        //反射创建对象
        Object newInstance = tClass.newInstance();
        earlySingletonObjects.put(beanName, newInstance);

        populateBean(newInstance);

        earlySingletonObjects.remove(beanName);
        singletonObject.put(beanName, newInstance);
        return newInstance;
    }

    // 填充属性
    public void populateBean(Object object) throws InstantiationException, IllegalAccessException {
        //获取所有添加了 @MyAutowired 注解的属性
        List<Field> autowiredFields = getAutowiredField(object.getClass());
        for (Field field : autowiredFields) {
            //开始注入
            doPopulateBean(object, field);
        }
    }

    // 开始注入对象
    public void doPopulateBean(Object object, Field field) throws IllegalAccessException, InstantiationException {
        //重新调用获取逻辑
        Object target = getBean(field.getType());
        field.setAccessible(true);
        field.set(object, target);
    }

    // 获取被标识自动注入的属性
    private List<Field> getAutowiredField(Class<?> tClass) {
        Field[] declaredFields = tClass.getDeclaredFields();
        return Arrays.stream(declaredFields).filter(field ->
                field.isAnnotationPresent(MyAutowired.class)).collect(Collectors.toList());
    }
}
