package cn.intv;

/**
 * 一个数组中只有两个数字是出现一次，其他所有数字都出现了两次，找出这两个数字
 */
public class FindNumAppearOnce {

	public static void main(String[] args) {
		int[] arr = { 11, 22, 33, 14, 15, 16, 21, 13, 16, 15, 14, 33, 22, 11 };
		find(arr);
	}

	private static void find(int[] arr) {
		int arrLen = arr.length;
		int result = arr[0];
		for (int i = 1; i < arrLen; i++) {
			result ^= arr[i];
		}

		// 找出result的二进制数中不为1的位
		int bit = 1;
		for (;;) {
			if ((result & bit) == bit) {
				break;
			}
			bit = bit << 1;
		}

		int a = 0, b = 0;
		for (int i = 0; i < arrLen; i++) {
			if ((arr[i] & bit) == bit) {
				a ^= arr[i];
			} else {
				b ^= arr[i];
			}
		}
		a ^= 0;
		b ^= 0;
		System.out.println("a = " + a + ", b= " + b);
	}

}
