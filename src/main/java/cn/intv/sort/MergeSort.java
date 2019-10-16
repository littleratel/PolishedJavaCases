package cn.intv.sort;

public class MergeSort {
	// 归并排序
	public static void mergeSort(int[] arr, int left, int right) {
		if (left >= right)
			return;

		int mid = (left + right) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		merge(arr, left, mid, right);
		System.out.print("排序中:\t");
		print(arr);
	}

    public static void merge(int[] arr,int low,int mid,int high) {
        int i,j,k;
        int[] b=arr.clone();
		
        for (k = i = low, j = mid + 1; i <= mid && j <= high; k++) {
			arr[k] = b[i] <= b[j] ? b[i++] : b[j++];
		}
        
        while(i<=mid) arr[k++]=b[i++];
        while(j<=high) arr[k++]=b[j++];
    }
	
	public static void main(String[] args) {
		int[] data = new int[] { 12, 4, -1, 3, 15, 8, 1, 13, -3, 0 };
		System.out.println("初始化：");
		print(data);
		mergeSort(data, 0, data.length - 1);
		System.out.println("排序后:");
		print(data);
	}

	public static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
}
