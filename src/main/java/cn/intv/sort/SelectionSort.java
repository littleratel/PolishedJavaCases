package cn.intv.sort;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 12, 10, 11, 10, 6, 10, 4, 3, 2, 1};
		System.out.println("排序前：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
		sort(arr);
		System.out.println("排序后：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
	
	// 选择排序
	private static void sort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			
			if(min != i){
				swap(arr,min,i);
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
	}
}
