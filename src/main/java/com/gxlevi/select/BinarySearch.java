package com.gxlevi.select;

import java.util.ArrayList;

/**
 * 二分法查找(只查有序的)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        int binarySearch1 = binarySearch1(arr, 0, arr.length - 1, 8);
        System.out.println(binarySearch1);
        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
    }

    public static int binarySearch1(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal > arr[right] || findVal < arr[left]) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (findVal < arr[mid]) {
            return binarySearch1(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            return binarySearch1(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }

    //优化,可查重复数据索引
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal > arr[right] || findVal < arr[left]) {
            return new ArrayList<>();
        }
        int mid = (right + left) / 2;
        if (findVal < arr[mid]) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                } else {
                    list.add(temp);
                    temp--;
                }
            }

            list.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != findVal) {
                    break;
                } else {
                    list.add(temp);
                    temp++;
                }
            }
            return list;
        }
    }
}
