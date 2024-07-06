package cn.intv.window;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 给定一个数组arr，返回arr的最长无重复元素子数组的长度;
 * 无重复指的是所有数字都不相同.
 * <p>
 * 输入: [2,2,3,4,3]
 * 返回值: 3
 * <p>
 * 输入: [2,2,3,4,8,99,3]
 * 返回值: 5
 * <p>
 * 输入: [1,2,3,1,2,3,2,2]
 * 返回值: 3
 */
public class LongestNonRepeatingSubarray {
    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{2, 2, 3, 4, 3})); // 3
        System.out.println(maxLength(new int[]{2, 2, 3, 4, 8, 99, 3})); // 5
        System.out.println(maxLength(new int[]{1, 2, 3, 1, 2, 3, 2, 2})); // 3
    }

    public static int maxLength(int[] arr) {
        Set<Integer> set = new HashSet();

        int left = 0, right = 0, res = 0;
        while (right < arr.length) {
            while (set.contains(arr[right])) {
                set.remove(arr[left++]);
            }
            set.add(arr[right++]);
            res = Math.max(res, right - left);
        }

        return res;
    }
}