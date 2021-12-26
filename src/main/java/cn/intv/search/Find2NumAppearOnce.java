package cn.intv.search;

/**
 * 一个数组中只有两个数字是出现一次，其他所有数字都出现了两次，找出这两个数字
 */
public class Find2NumAppearOnce {

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

		/***
		 * 假设那两个数是 a 和 b
		 * 1  计算异或 a^b 的值 re
		 * 2  找出 re 的二进制不为 1 的位，它也是 a 与 b 不同的位 index
		 * 3  将所有的元素按照 第 index 位的不同分开两队，
		 * 4  然后分别异或, 可计算出a b 的值
		 */
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

		System.out.println("a = " + a + ", b= " + b);
	}

}
