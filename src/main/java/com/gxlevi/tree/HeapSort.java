package com.gxlevi.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        //int arr[] = {4, 6, 8, 5, 9};
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        heapSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //编写一个堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序");

        //分步完成
        //adjustHeap(arr, 1, arr.length);
        //System.out.println("第一次" + Arrays.toString(arr));// 4 9 8 5 6
        //adjustHeap(arr, 0, arr.length);
        //System.out.println("第二次"+Arrays.toString(arr));//9 6 8 5 4

        //完成最终代码
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }


        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        //System.out.println("数组 = " + Arrays.toString(arr));
    }

    //将一个数组(二叉树),调整成一个大顶堆

    /**
     * 功能: 完成 将 以 i对应的非叶子节点的树,调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整 length是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        //k = i * 2 + 1   k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;///!!! 让i指向k,继续循环比较
            } else {
                break;//!
            }
        }
        //当for循环结束后,我们已经将以i为父节点的数的最大值,放在了最顶上(局部的)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
