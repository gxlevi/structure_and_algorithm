package com.gxlevi.select;

/**
 * 插值查找算法
 * 对均匀分布的数据集比二分法快
 */
public class InsertSearch {
    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        int result = insertSearch(arr, 0, arr.length - 1, 1);
        System.out.println(result);
    }

    public static int insertSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || arr[left] > findVal || findVal > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (arr[mid] < findVal) {
            return insertSearch(arr, mid + 1, right, findVal);
        } else if (arr[mid] > findVal) {
            return insertSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
