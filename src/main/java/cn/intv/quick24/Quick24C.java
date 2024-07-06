package cn.intv.quick24;

/**
 * 给定4个整数，利用加减乘除，括号，尝试利用算出24
 * 速算24
 */
public class Quick24C {
    public static void main(String[] args) {
        System.out.println(simpleEvaluation(new int[]{-1, 3, 4, 2}));
        System.out.println(simpleEvaluation(new int[]{1, 3, 4, 2}));
        System.out.println(simpleEvaluation(new int[]{1, 1, 1, 24}));
        System.out.println(simpleEvaluation(new int[]{1, 1, 1, 23}));
        System.out.println(simpleEvaluation(new int[]{1, 2, 3, 9}));
        System.out.println(simpleEvaluation(new int[]{8, 8, 8, 10}));
        System.out.println(simpleEvaluation(new int[]{5, 5, 1, 5})); // false
        System.out.println(simpleEvaluation(new int[]{13, 2, 3, 9}));
    }

    static boolean simpleEvaluation(int[] nums) {
        boolean[] visit = new boolean[nums.length];
        return dfs(24, nums, visit);
    }

    static boolean dfs(int target, int[] nums, boolean[] visit) {
        // 计算未被访问到元素
        int len = visit.length;
        Integer a = null, b = null;
        for (int i = 0; i < visit.length; i++) {
            if (visit[i]) {
                len--;
            } else if (a == null) {
                a = nums[i];
            } else if (b == null) {
                b = nums[i];
            }
        }

        if (len < 2) {
            return false;
        }

        // can be calculated
        if (len == 2) {
            if (target == a + b || target == a * b || target == a - b || target == b - a) {
                return true;
            }

            if ((b != 0 && a % b == 0 && a / b == target) || (a != 0 && b % a == 0 && b / a == target)) {
                return true;
            }

            return false;
        }

        // 剩余元素超过2个，继续dfs
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) {
                continue;
            }

            // 剪枝操作，比如处理重复的数字

            visit[i] = true;

            if (dfs(target + nums[i], nums, visit)
                    || dfs(target * nums[i], nums, visit)
                    || dfs(target - nums[i], nums, visit)
                    || dfs(nums[i] - target, nums, visit)) {
                return true;
            }

            if (nums[i] != 0 && target % nums[i] == 0 && dfs(target / nums[i], nums, visit)) {
                return true;
            }

            if (target != 0 && nums[i] % target == 0 && dfs(nums[i] / target, nums, visit)) {
                return true;
            }

            visit[i] = false;
        }

        return false;
    }
}