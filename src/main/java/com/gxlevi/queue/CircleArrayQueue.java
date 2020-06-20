package com.gxlevi.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        CircleArray queue = new CircleArray(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据是什么
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize;//表示数组的最大容量
    private int front;//front的初始值=0
    private int rear;//对列尾的初始值=0
    private int[] arr;//该数据用于存放数据,模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//指向队列的第一个元素
        rear = 0;//指向队列的最后一个元素的后一个位置,就是指你新插入的位置
    }

    //判断队列是否满
    public boolean isFull() {
        //当前队尾是rear,rear+1后变为front+maxsize则表明当前队列为满
        return (rear+1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return rear == front;//rear==front==0为空
    }

    //添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满,不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;//插入后arr[0]=11,rear+1!=front,但是front=0依然可以去除数字
        //将rear后移,这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //取出数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空,不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移,考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空,没有数据");
            return;
        }
        //思路:从front开始遍历,遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空,没有数据");
        }
        return arr[front];
    }
}
