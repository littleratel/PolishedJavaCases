
package cn.intv.greedy;

/**
 * leetcode 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 */
public class HoldRainWater {
    public static void main(String[] args) {
        int[] h = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap_DoublePointer(h));

        h = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap_DoublePointer(h));

        h = new int[]{3, 1, 2, 5, 2, 4};
        System.out.println(trap_DoublePointer(h));
    }

    // 双指针
    // 时间复杂度：O(n), 空间复杂度：O(1)
    private static int trap_DoublePointer(int[] height) {
        int left = 0, right = height.length - 1;
        int left_h_max = 0, right_h_max = 0;
        int ans = 0;
        while (left <= right) {
            if (left_h_max < right_h_max) {
                left_h_max = Math.max(height[left], left_h_max);
                ans += left_h_max - height[left++];
            } else {
                right_h_max = Math.max(height[right], right_h_max);
                ans += right_h_max - height[right--];
            }
        }

        return ans;
    }

    //
    private static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int level = 0, res = 0;
        while (left < right) {
            int lower = height[(height[left] < height[right]) ? left++ : right--];
            level = Math.max(level, lower);
            res += level - lower;
        }

        return res;
    }

    // 动态规划
    private static int trap_dp(int[] height) {
        int res = 0, mx = 0, n = height.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        mx = 0;
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Math.min(dp[i], mx);
            mx = Math.max(mx, height[i]);
            if (dp[i] > height[i]) res += dp[i] - height[i];
        }
        return res;
    }
}
