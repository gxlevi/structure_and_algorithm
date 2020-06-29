package com.gxlevi.select;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割)查找
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int result = fibSearch(arr, 1000);
        System.out.println(result);
    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契

    /**
     * 使用非递归的方式编写算法
     *
     * @param a   数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标, 如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;//指的就是数组最后一个元素的下标
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数组的下标
        while (high > f[k] - 1) {
            k++;
        }
        //以为f[k]的值可能大于数组a的长度,因此需要使用Array类,构造一个新的数组,并指向a
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际需要使用a数组的最后的数,填充temp
        //temp = {1, 8, 10, 89, 1000, 1234,0,0,0}=>{1, 8, 10, 89, 1000, 1234,1234,1234,1234};
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理,找到我们的数key
        while (low <= high) {//只要这个条件满足就可以一直找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//说明应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为什么k--
                //说明
                //1.全部元素 = 前面的元素+后边的元素
                //2.f[k] = f[k-1]+f[k-2]
                //因为 前面有f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2]+f[k-3]
                //即在f[k-1]的前面继续查找 k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {//说明应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k-2
                //说明
                //1.全部元素 = 前面的元素+后边的元素
                //2.f[k] = f[k-1]+f[k-2]
                //3.因为 后面有f[k-2]个元素,所以可以继续拆分 f[k-1] = f[k-3]+f[k-4]
                //4.即在f[k-2]的前面可以继续查找 k-=2
                //5.即下次循环mid = f[k-1-2]-1
                k -= 2;
            } else {//找到
                //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
