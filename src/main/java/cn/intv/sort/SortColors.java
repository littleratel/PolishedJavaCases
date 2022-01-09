package cn.intv.sort;

/**
 * LeetCode 75. 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 */
public class SortColors {
    public static void main(String[] args) {
        SortColors tool = new SortColors();
        int[] nums = {2, 0, 1, 0, 1, 0, 1, 0, 2, 0, 2, 1, 1, 0};
        tool.sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 计数
     * */
    public void sortColors(int[] nums) {
        int[] arr = new int[3];
        for (int v : nums) {
            arr[v]++;
        }

        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                nums[idx++] = i;
            }
        }
    }

    /**
     * 时间复杂度： O(N)
     */
    public void sortColors2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        int zero = 0, two = len, i = 0;
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
