package com.gxlevi.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        //int arr[] = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        radixSort(arr);
        //System.out.println("排序后=" + Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int ele : arr) {
            if (max < ele) {
                max = ele;
            }
        }
        int maxLength = ("" + max).length();
        //第1轮(针对每一个元素的个位进行排序处理)
        //定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        //1.二维数组包含10个一个维数组
        //2.为了防止在放入数的时候,数据溢出,则每一个一维数组(桶),大小定为arr.length
        //3.明显,基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中,实际存放了多少个数据,我们定义一个一维数组来记录各个桶每次放入的数据个数
        //可以这样理解
        //比如: bucketElementCounts[0] 记录的就是 bucket[0] 桶的放入的数据的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第1轮(针对每一个元素的个位进行排序处理)
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
            int index = 0;
            //遍历每一个桶,并将桶中数据,放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据,才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                    bucketElementCounts[k] = 0;
                }
            }
        }
    }
}
