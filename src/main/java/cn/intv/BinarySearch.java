package cn.intv;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { 1, 5, 3 };
		System.out.println(binarySearch(arr, 2));
	}

	public static int binarySearch(int[] arr, int tar) {
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int middle = (high + low) >>> 1;
			if (tar == arr[middle]) {
				return middle;
			} else if (tar < arr[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}
}
