package cn.intv.search;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void main() {
        int[] arr = {1, 2, 3, 5, 5, 5, 6, 7, 9, 11, 12, 23};
        System.out.println(doSearch(arr, 5));
    }

    private int doSearch(int[] arr, int tar) {
        int l = 0, middle, r = arr.length - 1;

        while (l <= r) {
            middle = (r + l) >>> 1;

            if (tar == arr[middle]) {
                return middle;
            } else if (tar < arr[middle]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        return -1;
    }

    //
    public int findTheFirst(int[] arr, int tar) {
        int l = 0, middle, r = arr.length - 1;

        while (l <= r) {
            middle = (r + l) >>> 1;

            if (tar == arr[middle]) {
                if (middle > 0 && arr[middle] == arr[middle - 1]) {
                    r = middle - 1;
                    continue;
                }
                return middle;
            } else if (tar < arr[middle]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        return -1;
    }
}
