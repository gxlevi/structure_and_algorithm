package com.gxlevi.tree;

public class ThreadTreeDemo {
    public static void main(String[] args) {
        ThreadNode root = new ThreadNode(1, "tom");
        ThreadNode node2 = new ThreadNode(3, "iu");
        ThreadNode node3 = new ThreadNode(6, "gd");
        ThreadNode node4 = new ThreadNode(8, "kitty");
        ThreadNode node5 = new ThreadNode(10, "twice");
        ThreadNode node6 = new ThreadNode(14, "git");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadTree threadTree = new ThreadTree(root);
        threadTree.infixThreadNode();
        ThreadNode left = node5.getLeft();
        ThreadNode right = node5.getRight();
        System.out.println("前驱节点是" + left);
        System.out.println("后继节点是" + right);
        threadTree.infixThreadedList();
    }
}

class ThreadTree {
    private ThreadNode root;
    private ThreadNode pre;

    public ThreadTree(ThreadNode root) {
        this.root = root;
    }

    public void setRoot(ThreadNode root) {
        this.root = root;
    }

    public void infixThreadNode() {
        this.infixThreadNode(root);
    }

    public void infixThreadedList() {
        ThreadNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void infixThreadNode(ThreadNode node) {
        if (node == null) {
            return;
        }
        infixThreadNode(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        infixThreadNode(node.getRight());

    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    public ThreadNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public ThreadNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public ThreadNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no) {
        if (root == null) {
            System.out.println("二叉树为空,无法再删除");
            return;
        } else {
            if (root.getId() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }
    }
}

class ThreadNode {
    private int id;
    private String name;
    private ThreadNode left;
    private ThreadNode right;
    private int leftType;
    private int rightType;

    public ThreadNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadNode getLeft() {
        return left;
    }

    public void setLeft(ThreadNode left) {
        this.left = left;
    }

    public ThreadNode getRight() {
        return right;
    }

    public void setRight(ThreadNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    //前序遍历查找
    public ThreadNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        if (this.getId() == no) {
            return this;
        }
        ThreadNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public ThreadNode infixOrderSearch(int no) {
        ThreadNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序查找");
        if (this.getId() == no) {
            return this;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public ThreadNode postOrderSearch(int no) {
        ThreadNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        if (this.getId() == no) {
            return this;
        }
        return resNode;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.getId() == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getId() == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}


