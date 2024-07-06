package cn.intv.quick24;

import org.junit.Test;

import java.util.Arrays;


/**
 * 给定4个整数，利用加减乘除，括号，尝试利用算出24
 * 速算24
 */
public class Quick24B {

    @Test
    public void quickly() {
        //-1,3,4,2
        //1,3,4,2
        //1,1,1,24
        //1,1,1,23
        //1,2,3,9
        //8,8,8,10
        //5,5,1,5
//        if (simpleEvaluation(24, new int[]{13, 2, 3, 9}, "")) {
//            System.out.println("可以计算出来");
//        } else {
//            System.out.println("不可以计算出来");
//        }

        System.out.println(simpleEvaluation(24, new int[]{-1, 3, 4, 2}, ""));
        System.out.println(simpleEvaluation(24, new int[]{1, 3, 4, 2}, ""));
        System.out.println(simpleEvaluation(24, new int[]{1, 1, 1, 24}, ""));
        System.out.println(simpleEvaluation(24, new int[]{1, 1, 1, 23}, ""));
        System.out.println(simpleEvaluation(24, new int[]{1, 2, 3, 9}, ""));
        System.out.println(simpleEvaluation(24, new int[]{8, 8, 8, 10}, ""));
        System.out.println(simpleEvaluation(24, new int[]{5, 5, 1, 5}, "")); // false
        System.out.println(simpleEvaluation(24, new int[]{13, 2, 3, 9}, ""));
    }

    private boolean simpleEvaluation(int target, int[] array, String operator) {
        //条件不满足 直接退出
        if (array.length < 2)
            return false;
        //
        if (array.length == 2) {
            int a = array[0];
            int b = array[1];
            if (target == a * b || target == a + b) {
//                System.out.println("a:" + a);
//                System.out.println("b:" + b);
//                System.out.println("+ or *");
//                System.out.println("target:" + target);
                return true;
            }
            if (target == a - b || target == b - a) {
//                System.out.println("a:" + a);
//                System.out.println("b:" + b);
//                System.out.println("-");
//                System.out.println("target:" + target);
                return true;
            }
            if (0 == a % b || 0 == b % a) {
                if (target == a / b || target == b / a) {
//                    System.out.println("a:" + a);
//                    System.out.println("b:" + b);
//                    System.out.println("//");
//                    System.out.println("target:" + target);
                    return true;
                }
            }
        }

//        System.out.println("op: " + operator);
//        System.out.println("target:" + target);
//        System.out.print("**********");
//        Arrays.stream(array).forEach(System.out::print);
//        System.out.print("**********");
//        System.out.println();

        for (int p = array.length - 1; p >= 0; p--) {
            if (simpleEvaluation(target + array[p], newArray(p, array), "-")) return true;
            if (simpleEvaluation(target - array[p], newArray(p, array), "+")) return true;
            if (simpleEvaluation(array[p] - target, newArray(p, array), "--")) return true;
            if (simpleEvaluation(target * array[p], newArray(p, array), "/")) return true;
            if (target % array[p] == 0) {
                if (simpleEvaluation(target / array[p], newArray(p, array), "*")) return true;
            }
            if (array[p] % target == 0) {
                if (simpleEvaluation(array[p] / target, newArray(p, array), "//")) return true;
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