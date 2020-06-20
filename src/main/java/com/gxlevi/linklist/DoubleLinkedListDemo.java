package com.gxlevi.linklist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "吴俊逸", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        System.out.println("---------------------------------");
        HeroNode2 newHero = new HeroNode2(4, "林冲冲", "豹子头~~~~");
        doubleLinkedList.update(newHero);
        doubleLinkedList.list();

        System.out.println("---------------------------------");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();


        System.out.println("---------------------------------");
        HeroNode2 hero02 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero01 = new HeroNode2(2, "吴俊逸", "玉麒麟");
        HeroNode2 hero04 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero03 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList();
        doubleLinkedList1.orderAdd(hero01);
        doubleLinkedList1.orderAdd(hero02);
        doubleLinkedList1.orderAdd(hero03);
        doubleLinkedList1.orderAdd(hero04);
        doubleLinkedList1.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表(和单链表相似)
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //根据顺序添加
    public void orderAdd(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode2.no) {
                break;
            }
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next.pre = heroNode2;
        }
        heroNode2.next = temp.next;
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //修改一个节点的内容,和单向链表几乎一样,只是节点的类型改成了HeroNode2
    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode2.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
        } else {
            System.out.printf("没有找到编号%d的节点,不能修改\n", heroNode2.no);
        }
    }

    //从双向链表中删除一个节点
    //对于双向链表,可以直接找到要删除的这个节点
    //找到后自我删除即可
    public void delete(int no) {
        //判断当前链表是否为null
        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return;
        }
        HeroNode2 temp = head.next;//辅助变量
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                //如果是最后一个节点就不需要执行下面,否则会出现空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d 节点不存在\n", temp.no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点,默认为null
    public HeroNode2 pre;//指向前一个节点,默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
