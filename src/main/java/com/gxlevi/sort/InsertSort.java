package com.gxlevi.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1,-1,89};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        //使用逐步推导的方式
        //第一轮{101,34,119,1}=>{34,101,119,1}
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal找到插入的位置
            //说明
            //1.insetIndex >= 0 保证给insertVal找插入位置,不越界
            //2.insertVal < arr[insetIndex] 待插入的数,还没有找到插入位置
            //3.就需要将arr[insertIndex后移]
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明插入的位置找到,insertIndex+1
            //优化,判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.printf("第%d轮插入", i);
            //System.out.println(Arrays.toString(arr));
        }
    }
}
