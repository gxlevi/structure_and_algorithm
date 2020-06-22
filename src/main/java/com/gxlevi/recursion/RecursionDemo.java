package com.gxlevi.recursion;

public class RecursionDemo {
    public static void main(String[] args) {
        //通过打印问题,实现递归调用机制
        test(4);

        int res = factorial(5);
        System.out.println("res=" + res);
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
