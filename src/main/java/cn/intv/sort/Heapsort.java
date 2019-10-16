package cn.intv.sort;

public class Heapsort {
	public static void main(String[] args) {

		int[] arr = { 3, 7, 2, 11, 3, 0, 4, 9, 2, 18 };
		System.out.println("排序前：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();

		HeapSort(arr);

		System.out.println("排序后：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}

	// 堆排序函数
	public static void HeapSort(int[] arr) {
		int n = arr.length - 1;

		// 初始化堆,使数组成为一个最大二叉堆
		for (int i = (n - 1) / 2; i >= 0; i--) {
			HeapAdjust(arr, i, n);
		}

		// 交换数据, 交换a[0]和a[i], a[0]是a[0...i]中最大的
		for (int i = n; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// 调整a[0...i-1],使得a[0...i-1]仍然是一个最大堆
			HeapAdjust(arr, 0, i - 1);
		}
	}

	// 构造大顶堆函数
	public static void HeapAdjust(int[] arr, int parent, int length) {
		int temp = arr[parent];

		for (int i = parent * 2 + 1; i <= length; i = i * 2 + 1) {
			// 选出左右孩子中最大的那个值
			if (i < length && arr[i] < arr[i + 1]) {
				i++;
			}
			// 如果父节点 >= 较大的孩子，那就退出循环
			if (temp >= arr[i]) {
				break;
			}

			arr[parent] = arr[i];
			parent = i;
		}

		arr[parent] = temp;
	}

	static void printArr(int[] arr, int i) {
		System.out.println("第" + i + "次排序：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}
