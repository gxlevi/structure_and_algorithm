package com.gxlevi.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int arr[]) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heap(arr, i, arr.length);
        }
        int temp = 0;
        System.out.println(Arrays.toString(arr));
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heap(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void heap(int arr[], int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
            arr[i] = temp;
        }
    }

}
