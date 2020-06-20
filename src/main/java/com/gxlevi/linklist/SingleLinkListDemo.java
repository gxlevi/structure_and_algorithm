package com.gxlevi.linklist;

import java.util.Stack;

public class SingleLinkListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "吴俊逸", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkList singleLinkList = new SingleLinkList();
        //加入链表
        singleLinkList.addByOrder(hero2);
        singleLinkList.addByOrder(hero1);
        singleLinkList.addByOrder(hero4);
        singleLinkList.addByOrder(hero3);
        //测试修改节点
        HeroNode newHero2 = new HeroNode(2, "小吴", "玉麒麟~~~");
        singleLinkList.update(newHero2);
        //显示一把
        singleLinkList.list();

        System.out.println();
        System.out.println("删除后结果");
        //删除一个节点
        singleLinkList.delete(1);
//        singleLinkList.delete(4);
//        singleLinkList.delete(2);
//        singleLinkList.delete(3);
        singleLinkList.list();

        //测试:求单链表的有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singleLinkList.getHead()));
        //测试:看看是否得到了倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkList.getHead(), 2);
        System.out.println("res=" + res);
        //测试:单链表的反转
        System.out.println("原来链表的情况");
        singleLinkList.list();
        System.out.println("反转链表的情况");
        reverseList(singleLinkList.getHead());
        singleLinkList.list();
        //测试:逆序打印链表,没有改变链表本身的结构
        System.out.println("测试逆序打印");
        singleLinkList.list();
        System.out.println("逆序打印");
        reversePrint(singleLinkList.getHead());
        //测试:顺序合并两个单链表
        SingleLinkList singleLinkList1 = new SingleLinkList();
        singleLinkList1.addByOrder(hero3);
        singleLinkList1.addByOrder(hero1);

        SingleLinkList singleLinkList2 = new SingleLinkList();
        singleLinkList2.addByOrder(hero4);
        singleLinkList2.addByOrder(hero2);
        System.out.println("顺序合并");
        HeroNode merge = merge(singleLinkList1.getHead().next, singleLinkList2.getHead().next);
        while (true) {
            if (merge == null) {
                break;
            }
            System.out.println(merge);
            merge = merge.next;
        }
    }

    //方法:获取到单链表的节点的个数(如果是带头节点的链表,需要不统计头结点)

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        //没有统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //方法:查找单链表的倒数第k个结点
    //思路
    //1.编写一个方法,接收head节点,同时接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历一下,得到链表的总的长度getLength()
    //4.得到size后,从链表的第一个开始遍历,遍历(size-index个),就可以得到
    //5.如果找到了则返回该节点,否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空,返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size-index,就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量,for循环定位到倒数的index
        HeroNode cur = head.next;//3个,找倒数第一个,移动两项3-1=2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表的反转
    //思路:
    //1.先去定义一个节点,reverseHead=new HeroNode
    //2.从头到尾遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表的最前端
    //3.原来的链表的head.next = reverseHead.next
    public static void reverseList(HeroNode head) {
        //如果当前的链表为空,或者只有一个节点,就无需反转,直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针(变量),帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表,每遍历一个节点,就将其取出,并放入新的链表
        while (cur != null) {
            next = cur.next;//先暂时的保留当前节点的下一个节点,因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur 连接到新的链表上
            cur = next;//让cur后移一次
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    //从尾到头遍历打印单链表
    //思路:
    //1.逆序打印单链表
    //2.方式一:先将单链表进行一个反转操作,然后再遍历即可:这样做的问题是会破坏原来的单链表的结构,不可取
    //3.方式二:可以利用栈这个数据结构,将各个节点压入到栈中,然后利用栈的先进后出的特点,实现了逆序打印的效果
    //使用方式二来实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表无法打印
        }
        //创建一个栈,将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//后移一下,可以压入下一个节点
        }
        //将栈中的节点进行打印,pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//逆序的弹出,先进后出
        }
    }

    //合并两个有序的单链表,合并之后的链表依然有序的
    public static HeroNode merge(HeroNode heroNode1, HeroNode heroNode2) {
        if (heroNode1 == null) {
            return heroNode2;
        }
        if (heroNode2 == null) {
            return heroNode1;
        }
        if (heroNode1.no <= heroNode2.no) {
            heroNode1.next = merge(heroNode1.next, heroNode2);
            return heroNode1;
        } else {
            heroNode2.next = merge(heroNode1, heroNode2.next);
            return heroNode2;
        }
    }
}

class SingleLinkList {
    //定义头节点,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //按照no顺序添加
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在,默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移,遍历当前链表
        }
        //判断
        if (flag) {
            //不能添加,编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了,不能加入\n", heroNode.no);
        } else {
            //加入到链表中,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息,根据no编号来修改,即no编号不能改
    //单链表的修改
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点,根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//到链表的最后,已经遍历完链表了
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            //没有找到
            System.out.printf("没有找到编号%d的节点,不能修改\n", heroNode.no);
        }
    }

    //删除节点
    public void delete(int no) {
        //1.找到需要删除节点的前一个节点
        //2.temp.next = temp.next.next
        HeroNode temp = head;//标志是否找到待删除节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//已经到链表的最后
            }
            if (temp.next.no == no) {
                //找到了待删除节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移,遍历
        }
        if (flag) {
            //说明找到
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }

    //显示链表(遍历)
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


