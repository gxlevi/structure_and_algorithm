package com.gxlevi.linklist;

public class Josepfu {
    public static void main(String[] args) {
        //测试 看看构建环形链表,和遍历是否成功
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点,当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点,构建成一个环形的链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针,帮助构建环形链表
        //使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环状
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空,没有任何小孩");
            return;
        }
        //因为first不能动,因此仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //说明遍历完毕了
                break;
            }
            curBoy = curBoy.getNext();//curBoy 后移
        }
    }

    //根据用户的输入,计算出小孩出圈的顺序

    /**
     * @param startNo  从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入");
            return;
        }
        //创建一个辅助指针,帮助完成小孩出圈
        Boy helper = first;

        while (true) {
            if (helper.getNext() == first) {
                //说明指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //报数前,先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时,让first和helper指针同事移动m-1次,然后出圈
        //这里是一个循环操作,直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",helper.getNo());
    }
}

//创建一个Boy类,表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点,默认为null

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
