package com.gxlevi.select;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int resultIndex = seqSearch(arr, 11);
        System.out.println(resultIndex);
    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
