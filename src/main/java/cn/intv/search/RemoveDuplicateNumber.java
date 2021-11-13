package cn.intv.search;

import org.junit.Test;

public class RemoveDuplicateNumber {

    @Test
    public void main() {
        int[] arr = {1, 2, 2, 2, 3, 4, 5, 5, 5};
        int len = removeDuplicate(arr);
        // Output: 1 2 3 4 5
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }

        //
        int[] arr2 = {1, 2, 3, 2, 3, 3, 4, 5, 3};
        int len2 = removeDuplicate(arr2, 3);
        for (int i = 0; i < len2; i++) {
            System.out.print(arr2[i] + " ");
        }
    }

    /**
     * Remove elements from the given array with the value equal to target.
     * And return the length of new array.
     * */
    private int removeDuplicate(int[] arr, int target) {
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target) {
                arr[idx++] = arr[i];
            }
        }

        return idx;
    }

    /**
     * Removes duplicate elements from an given ordered array
     * */
    private int removeDuplicate(int[] arr) {
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[idx] != arr[i]) {
                arr[++idx] = arr[i];
            }
        }

        return ++idx;
    }
}