package cn.intv.sort;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = { 49, 38, 65, 97, 76, 13, 13, 23, 3, 35, 25, 54, 51 };
		for (int n : arr) {
			System.out.print(n + ",");
		}
		System.out.println("");

		insertSort(arr);

		for (int n : arr) {
			System.out.print(n + ",");
		}
	}

	// 插入排序
	static void insertSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		int n = arr.length, j = 0, target = 0;
		for (int i = 1; i < n; i++) {
			j = i - 1;
			target = arr[i];
			while (j >= 0 && target < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}

			arr[j] = target;
		}
	}
}
