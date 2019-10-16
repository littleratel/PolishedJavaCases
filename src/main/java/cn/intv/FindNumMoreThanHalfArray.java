package cn.intv;

public class FindNumMoreThanHalfArray {

	public static void main(String[] args) {
		int[] arr = { 11, 15, 16, 22, 14, 13, 13, 13, 21, 13, 13, 13, 22, 13, 13 };
		System.out.println(find(arr));
	}

	private static int find(int[] arr) {
		int times = 1, result = arr[0];
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			if (times == 0) {
				result = arr[i];
				times = 1;
			} else if (result == arr[i]) {
				times++;
			} else {
				times--;
			}
		}
		
		return result;
	}

}
