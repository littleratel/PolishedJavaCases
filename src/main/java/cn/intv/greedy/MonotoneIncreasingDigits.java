package cn.intv.greedy;

public class MonotoneIncreasingDigits {

    /**
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * 单调递增:
     * 当每个相邻位数上的数字 x 和 y 满足 x <= y 时，称这个整数是单调递增.
     *
     * Examples:
     * 输入: N = 10
     * 输出: 9
     *
     * 输入: N = 1234
     * 输出: 1234
     *
     * 输入: N = 332
     * 输出: 299
     * */
    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(322));
    }

    public static int monotoneIncreasingDigits(int n) {
        if (n < 10) {
            return n;
        }

        char[] arr = ("" + n).toCharArray();
        int tag = arr.length;
        for (int i = arr.length - 1; i >= 1; i--) {
            if (arr[i] < arr[i - 1]) {
                tag = i;
                arr[i - 1]--;
            }
        }

        for (int i = tag; i < arr.length; i++) {
            arr[i] = '9';
        }

        return Integer.parseInt(String.valueOf(arr));
    }
}
