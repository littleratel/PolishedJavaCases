package util.beanutils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {

    /**
     * 对象之间的属性值的拷贝
     *
     * */
    public static void main(String[] args) {
        ObjectSource source = new ObjectSource();
        source.setName("Elis");
        source.setAge(36);
        source.setMan(true);
        source.setTestFiled("NO-TEST");

        Object target = new ObjectTarget();

        BeanUtils.copyProperties(source, target);
        System.out.println(target);
    }

    @Setter
    @Getter
    static
    private class ObjectSource {
        private String name;
        private int age;
        private boolean isMan;

        private String testFiled;
    }

    @Setter
    @Getter
    @ToString
    private static class ObjectTarget {
        private String name;
        private int age;
        private boolean isMan;


    }
}

