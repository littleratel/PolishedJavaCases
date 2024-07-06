package cn.intv.quick24;

import org.junit.Test;

/**
 * 速算24
 * 给定4个整数，利用加减乘除，括号，尝试利用算出24
 * <p>
 * 下面给出当四个数位置不变的解法.
 * 当要求数字位置可以改变时，利用穷举法，将4个数的位置摆放的情况都列出来.
 */
public class Quick24 {

    @Test
    public void main() {
        boolean res = doCalculate(24, new int[]{1, 2, 3, 1});
        System.out.println(res);
    }

    private boolean doCalculate(int target, int[] array) {
        if (array.length == 2) {
            if (array[0] + array[1] == target || array[0] * array[1] == target) {
                return true;
            }

            if (array[0] - array[1] == target || array[1] - array[0] == target) {
                return true;
            }

            if (array[0] % array[1] != 0 && array[1] % array[0] != 0) {
                return false;
            } else if (array[0] / array[1] == target || array[1] / array[0] == target) {
                return true;
            }

            return false;
        }

        for (int i = array.length - 1; i >= 2; i--) {
            int[] newArr = removeElementWithIndex(i, array);
            if (doCalculate(target + array[i], newArr)) return true;
            if (doCalculate(target - array[i], newArr)) return true;
            if (doCalculate(array[i] - target, newArr)) return true;
            if (doCalculate(target * array[i], newArr)) return true;

            if (target % array[i] == 0) {
                if (doCalculate(target / array[i], newArr)) return true;
            }

            if (array[i] % target == 0) {
                if (doCalculate(array[i] / target, newArr)) return true;
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

        for (int i = 0, j = 0; i < oldArray.length; i++) {
            if (i == index) {
                continue;
            }
            res[j++] = oldArray[i];
        }

        return res;
    }
}