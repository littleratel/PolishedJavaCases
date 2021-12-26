package cn.intv.quick24;

import org.junit.Test;

/**
 * 速算24
 * 给定4个整数，利用加减乘除，括号，尝试利用算出24
 *
 * 下面给出当四个数位置不变的解法.
 * 当要求数字位置可以改变时，利用穷举法，将4个数的位置摆放的情况都列出来.
 */
public class Quick24 {

    @Test
    public void main() {
        if (doCalculate(24, new int[]{13, 2, 3, 9})) {
            System.out.println("可以计算出来");
        } else {
            System.out.println("不可以计算出来");
        }
    }

    private boolean doCalculate(int target, int[] array) {
        if (array.length == 2) {
            int a = array[0];
            int b = array[1];
            if (target == a * b || target == a + b) {
                return true;
            }
            if (target == a - b || target == b - a) {
                return true;
            }

            if (a % b != 0 && b % a != 0) {
                return false;
            } else if (target == a / b || target == b / a) {
                return true;
            }
        }

        for (int p = array.length - 1; p >= 0; p--) {
            if (doCalculate(target + array[p], removeElementWithIndex(p, array))) return true;
            if (doCalculate(target - array[p], removeElementWithIndex(p, array))) return true;
            if (doCalculate(array[p] - target, removeElementWithIndex(p, array))) return true;
            if (doCalculate(target * array[p], removeElementWithIndex(p, array))) return true;
            if (target % array[p] == 0) {
                if (doCalculate(target / array[p], removeElementWithIndex(p, array))) return true;
            }
            if (array[p] % target == 0) {
                if (doCalculate(array[p] / target, removeElementWithIndex(p, array))) return true;
            }
        }

        return false;
    }

    /***
     * Remove Element with index
     * input: [0,1,2,3], index = 2;
     * output: [0,1,3]
     */
    private int[] removeElementWithIndex(int index, int[] oldArray) {
        int[] res = new int[oldArray.length - 1];
        for (int n = 0, m = 0; n < res.length; n++) {
            if (m == index) {
                m++;
            }
            res[n] = oldArray[m++];
        }

        return res;
    }

}