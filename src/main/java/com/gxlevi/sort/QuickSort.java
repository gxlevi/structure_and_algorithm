package com.gxlevi.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序(填坑法)
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {-1, 10, 2, 11, -9, 20};
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println("排序后=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int l, r, tmp, base;
        l = left;
        r = right;
        base = arr[right];

        while (l < r) {
            while (l < r && arr[l] <= base) {
                l++;
            }
            while (l < r && arr[r] >= base) {
                r--;
            }
            if (l < r) {
                //满足条件则,交换位置
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
            }
        }
        //交换基准值和l的位置
        arr[right] = arr[l];
        arr[l] = base;

        //左递归
        quickSort(arr, left, r - 1);
        //右递归
        quickSort(arr, l + 1, right);
    }
}
