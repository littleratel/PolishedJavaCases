package cn.intv.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 时间复杂度为 O(n)
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        LongestConsecutive tool = new LongestConsecutive();
        System.out.println(tool.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(tool.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * 时间复杂度：O(n), 2n+2 ~ 4n
     * 空间复杂度：O(n)
     * */
    private int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;

        Set<Integer> set = new HashSet<>();
        for (int item : nums) {
            set.add(item);
        }

        int maxLen = 1, curLen, left, right;
        for (int num : nums) {
            if (!set.remove(num)) {
                continue;
            }

            curLen = 1;
            left = num - 1;
            right = num + 1;
            while (set.remove(left)) {
                left--;
                curLen++;
            }
            while (set.remove(right)) {
                right++;
                curLen++;
            }

            maxLen = Math.max(curLen, maxLen);
        }

        return maxLen;
    }
}
