package com.gxlevi.stack;

/**
 * 链表实现栈
 */
public class ArrayStackDemo2 {
    public static void main(String[] args) {
        ArrayStack2 arrayStack2 = new ArrayStack2(3);
        System.out.println(arrayStack2.isNull());
        System.out.println(arrayStack2.isFull());
        arrayStack2.push(1);
        arrayStack2.push(2);
        arrayStack2.push(3);
        arrayStack2.list();
        System.out.println("-----------------------");
        int pop1 = arrayStack2.pop();
        int pop2 = arrayStack2.pop();
        int pop3 = arrayStack2.pop();
        System.out.println(pop1);
        System.out.println(pop2);
        System.out.println(pop3);
        arrayStack2.list();
    }
}

class ArrayStack2 {
    private int maxSize;
    private Point head = new Point(0);

    public ArrayStack2(int max) {
        this.maxSize = max;
    }

    public boolean isNull() {
        return head.getNext() == null;
    }

    public boolean isFull() {
        Point temp = head.getNext();
        int num = 0;
        if (temp == null) {
            return false;
        }
        while (temp.getNext() != null) {
            num++;
            temp = temp.getNext();
        }
        return num == maxSize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        Point cur = new Point(value);
        cur.setNext(head.getNext());
        head.setNext(cur);
    }

    public int pop() {
        if (isNull()) {
            throw new RuntimeException("栈空");
        }
        Point cur = head.getNext();
        int no = cur.getNo();
        cur = cur.getNext();
        head.setNext(cur);
        return no;
    }

    public void list() {
        if (isNull()) {
            throw new RuntimeException("栈空");
        }
        Point cur = head.getNext();
        while (cur != null) {
            System.out.println(cur.getNo());
            cur = cur.getNext();
        }
    }
}

class Point {
    private int no;
    private Point next;

    public Point(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Point{" +
                "no=" + no +
                '}';
    }
}
