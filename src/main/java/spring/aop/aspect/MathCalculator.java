package spring.aop.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MathCalculator {

    @Autowired
    private MathCalculator tor;

    public int add(int a, int b) {
        System.out.println("MathCalculator# add ...");

        return tor.div(a, b);
    }

    public int div(int a, int b) {
        System.out.println("MathCalculator# div ...");

        return a / b;
    }
}
