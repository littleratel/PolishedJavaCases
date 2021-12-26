package cn.intv.search;

/**
 * 数组中出现次数超过一半的数字;
 * 多数投票问题，可以利用 Boyer-Moore Majority Vote Algorithm 来解决这个问题，使得时间复杂度为 O(N);
 * 它只能从存在多数元素的数组中找到它，并不能确定是否存在这个多数元素.
 */
public class FindNumMoreThanHalfArray {

    public static void main(String[] args) {
        int[] arr = {11, 15, 16, 22, 14, 13, 13, 13, 21, 13, 13, 13, 22, 13, 13};
        System.out.println(find(arr));
    }

    private static int find(int[] arr) {
        int result = 0, count = 0;

        for(int a : arr){
            if (count == 0) {
                result = a;
                count = 1;
            } else if (result == a) {
                count++;
            } else {
                count--;
            }
        }

        return result;
    }

}
