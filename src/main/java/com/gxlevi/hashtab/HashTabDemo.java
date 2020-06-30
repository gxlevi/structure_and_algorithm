package com.gxlevi.hashtab;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add");
            System.out.println("order");
            System.out.println("list");
            System.out.println("find");
            System.out.println("delete");
            System.out.println("exit");
            String key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "order":
                    System.out.println("输入id");
                    int id3 = scanner.nextInt();
                    System.out.println("输入name");
                    String name1 = scanner.next();
                    Emp emp1 = new Emp(id3, name1);
                    hashTab.orderAdd(emp1);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找的id");
                    int id1 = scanner.nextInt();
                    hashTab.findEmpById(id1);
                    break;
                case "delete":
                    System.out.println("输入要删除的id");
                    int id2 = scanner.nextInt();
                    hashTab.delete(id2);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}

class HashTab {
    private int size;
    private EmpList[] empList;

    public HashTab(int size) {
        this.size = size;
        empList = new EmpList[size];
        for (int i = 0; i < size; i++) {
            empList[i] = new EmpList();
        }
    }

    public void add(Emp emp) {
        int hashIndex = getHash(emp.id);
        empList[hashIndex].add(emp);
    }

    public void orderAdd(Emp emp) {
        int hashIndex = getHash(emp.id);
        empList[hashIndex].orderAdd(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empList[i].list();
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列确定到哪条链表查找
        int hashNo = getHash(id);
        Emp emp = empList[hashNo].findEmpById(id);
        if (emp != null) {//找到了
            System.out.printf("在第%d条链表中找到雇员 雇员id = %d\n", hashNo + 1, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    public void delete(int id) {
        int hashNo = getHash(id);
        empList[hashNo].delete(id);
    }

    public int getHash(int id) {
        return id % size;
    }
}

class EmpList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp cur = head;
        while (true) {
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        cur.next = emp;
    }

    public void orderAdd(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        if (head.next == null && head.id > emp.id) {
            head.next = head;
            head = emp;
        }
        Emp cur = head;
        while (true) {
            if (cur.next == null) {
                cur.next = emp;
                break;
            }
            if (cur.next.id > emp.id) {
                emp.next = cur.next;
                cur.next = emp;
                break;
            }
            cur = cur.next;
        }
    }

    public void list() {
        if (head == null) {
            return;
        }
        Emp cur = head;
        while (true) {
            System.out.println(cur.id + ":" + cur.name);
            cur = cur.next;
            if (cur == null) {
                break;
            }
        }
    }

    //如果查找到就返回emp,如果没有找到就返回空
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                //找到
                break;//这时curEmp就指向要查找的雇员
            }
            if (curEmp.next == null) {
                //说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            //后移一下
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    public void delete(int id) {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            if (head.id == id) {
                head = null;
            }
            return;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
            }
            curEmp = curEmp.next;
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}