package cn.puzzlers;

/***
 * 奇偶数判断问题
 */
public class OddProperty {
    public static void main(String[] args) {
        System.out.println(1 % 2);   //  1
        System.out.println(-1 % 2);  // -1
        System.out.println(-12 % 2); //  0

        System.out.println(isOdd(-1));
    }

    /**
     * 任何负整数，不管奇偶，该方法永远返回 false;
     * 因为：
     * 取余操作 % 返回一个非零的结果时，它与左操作数具有相同的正负符号。
     *
     * 所以可以改为:
     * return i % 2 != 0;
     */
    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    /**
     * 用位操作符 AND（&）来替代取余操作符 %
     * */
    public static boolean isOddHighPerformance(int i){
        return (i & 1) != 0;
    }
}
