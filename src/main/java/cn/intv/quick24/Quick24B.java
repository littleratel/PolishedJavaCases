package cn.intv.quick24;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给定4个整数，利用加减乘除，括号，尝试利用算出24
 * 速算24
 */
public class Quick24B {

    @Test
    public void quickly() {
        List<Object> opes = new ArrayList<>();
        simpleEvaluation(24, new int[]{-1, 3, 4, 2}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{1, 3, 4, 2}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{1, 1, 1, 24}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{1, 1, 1, 23}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{1, 2, 3, 9}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{8, 8, 8, 10}, opes);
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{5, 5, 1, 5}, opes); // false
        System.out.println(opes);
        opes.clear();

        simpleEvaluation(24, new int[]{13, 2, 3, 9}, opes);
        System.out.println(opes);
        opes.clear();
    }

    private boolean simpleEvaluation(int target, int[] array, List<Object> opes) {
        //条件不满足 直接退出
        if (array.length < 2)
            return false;

        //
        if (array.length == 2) {
            int a = array[0];
            int b = array[1];
            if (target == a * b) {
                opes.add(a);
                opes.add("*");
                opes.add(b);
                return true;
            }

            if (target == a + b) {
                opes.add(a);
                opes.add("+");
                opes.add(b);
                return true;
            }

            if (target == a - b) {
                opes.add(a);
                opes.add("-");
                opes.add(b);
                return true;
            }

            if (target == b - a) {
                opes.add(b);
                opes.add("-");
                opes.add(a);
                return true;
            }

            if (0 == a % b || 0 == b % a) {
                if (target == a / b) {
                    opes.add(a);
                    opes.add("/");
                    opes.add(b);

                    System.out.println("a/b: " + a + " / " + b + " = " + target);
                    return true;
                }

                if (target == b / a) {
                    opes.add(b);
                    opes.add("/");
                    opes.add(a);

                    System.out.println("b/a: " + b + " / " + a + " = " + target);
                    return true;
                }
            }
        }

        for (int p = array.length - 1; p >= 0; p--) {
            if (simpleEvaluation(target + array[p], newArray(p, array), opes)) {
                opes.add("-");
                opes.add(array[p]);
                return true;
            }

            if (simpleEvaluation(target - array[p], newArray(p, array), opes)) {
                opes.add("+");
                opes.add(array[p]);
                return true;
            }

            if (simpleEvaluation(array[p] - target, newArray(p, array), opes)) {
                opes.add("-");
                opes.add(array[p]);
                return true;
            }

            if (simpleEvaluation(target * array[p], newArray(p, array), opes)) {
                opes.add("/");
                opes.add(array[p]);
                return true;
            }

            if (target % array[p] == 0) {
                if (simpleEvaluation(target / array[p], newArray(p, array), opes)) {
                    opes.add("*");
                    opes.add(array[p]);
                    return true;
                }
            }

            if (array[p] % target == 0) {
                if (simpleEvaluation(array[p] / target, newArray(p, array), opes)) {
                    opes.add("/");
                    opes.add(array[p]);
                    return true;
                }
            }
        }

        return false;
    }

    private int[] newArray(int index, int[] oldArray) {
        int[] newArray = new int[oldArray.length - 1];
        int m = 0;
        for (int n = 0; n < newArray.length; n++) {
            if (m == index) {
                m++;
            }
            newArray[n] = oldArray[m];
            m++;
        }

        return newArray;
    }
}
