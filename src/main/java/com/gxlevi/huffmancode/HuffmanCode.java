package com.gxlevi.huffmancode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
        String srcFile = "D:\\1.jpg";
        String dstFile = "D:\\dst.zip";

        //zipFile(srcFile, dstFile);
        //System.out.println("压缩文件成功");

        unZipFile("D:\\dst.zip","D:\\newSrc.jpg");
        System.out.println("解压文件成功");

        /*String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);

        //压缩
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodeBytes));
        System.out.println("长度为=" + huffmanCodeBytes.length);

        //如何将数据进行解压(解码)
        //测试byteToBitString方法
        //System.out.println(byteToBitString(true, (byte) 1));

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串" + new String(sourceBytes));*/

        //List<Node> nodes = getNodes(contentBytes);
        //System.out.println("nodes:" + nodes);
        //System.out.println("赫夫曼树");
        //Node huffmanTree = createHuffmanTree(nodes);
        //huffmanTree.preOrder();

        //测试一把哈夫曼节点
        //getCodes(huffmanTree, "", stringBuilder);
        //Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //System.out.println("生成的赫夫曼编码表" + huffmanCodes);

        //测试
        //zip(contentBytes, huffmanCodes);

        //测试
        //byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        //System.out.println("huffmanCodeBytes" + Arrays.toString(huffmanCodeBytes));//17
    }

    //编写方法,将一个文件进行压缩

    /**
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dstFile 我们将压缩文件放到那个目录下
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        //创建一个文件的输入流
        FileInputStream is = null;

        try {

            is = new FileInputStream(srcFile);
            //创建一个和原文件大小一样的byte[]数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对原文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流,存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //是先把huffmanBytes,写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码 ,是为了以后我们恢复原文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //编写一个方法,完成对压缩文件的解压

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义一个文件的输出流
        OutputStream oos = null;

        //创建文件输入流
        try {
            is = new FileInputStream(zipFile);
            //创建一个和 is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte[]数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(codes, huffmanBytes);
            //将bytes数组写入到目标文件desFile
            oos = new FileOutputStream(dstFile);
            //写出数据到文件中
            oos.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                is.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes :[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  重新先转成 赫夫曼编码对应的二进制字符串 "1010100010111..."
    //2.赫夫曼编码对应的二进制的字符串"10100010111..." =>对照赫夫曼编码=>"i like like like java do you like a java"

    //编写一个方法,完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 得到的字符串数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes 对应的二进制的字符串 如"10100010111..."
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        //System.out.println("赫夫曼字节数组转对应的二进制字符串=" + stringBuilder.toString());

        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换,因为要反向查询 97->100 => 100->?
        Map<String, Byte> map = new HashMap<>();
        Set<Byte> keySet = huffmanCodes.keySet();
        for (Byte key : keySet) {
            map.put(huffmanCodes.get(key), key);
        }
        //System.out.println("反向map="+map);

        //2.创建一个集合,存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            stringBuilder.charAt(i);
            while (flag) {
                //递增的取出key 1->10->101->1010
                String key = stringBuilder.substring(i, i + count);//i 不动,让count移动,直到匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//让i直接移动到count的位置
        }
        //当for循环结束后,我们list中就存放了所有的字符 "i like like like java do you like a java"
        //把list中的数据存入byte[]并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制的字符串,可以参考二进制的源码反码补码
     *
     * @param b    传入的byte
     * @param flag 标志是否需要补高位如果是true,表示需要补高位,如果是false表示不补
     * @return 是该b 对应的二进制的字符串(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        if (flag) {
            temp |= 256;//按位与256 1 0000 0000 | 0000 0000 1 => 1 0000 0000 1
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static List<Node> getNodes(byte[] bytes) {
        //1创建一个arrayList
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        //遍历bytes,统计每一个byte出现的次数->map
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转为一个Node对象,并加入到nodes集合
        Set<Byte> keySet = counts.keySet();
        for (Byte b : keySet) {
            nodes.add(new Node(b, counts.get(b)));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将处理过的二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //编写一个方法,将字符串对应的byte数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码处理后的byte数组

    /**
     * @param bytes        原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte数组
     * 举例 String content = "i like like like java do you like a java";
     * 返回的是字符串 "101010001011111111001...."
     * =>对应的byte[] huffmanCodeBytes ,即8位对应一个byte,放入到huffmanCodeBytes中
     * huffmanCodeBytes[0] = 10101000(补码) => byte[推导 10101000=>10101000-1=>10100111(反码)=>11011000=>-88]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.先利用赫夫曼编码表,将bytes转成 赫夫曼编码对应的的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历byte 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试stringBuilder=" + stringBuilder.toString());
        //System.out.println(stringBuilder.length());
        int len = (stringBuilder.length() + 7) / 8;
        /*int len;
        if (stringBuilder.length()%8 == 0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8+1;
        }*/
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个byte,所以步长+8
            String strByte;
            if (i + 8 >= stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将stringbyte转成一个byte,放入到huffmanCodeBytes中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);//转换成二进制
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1.将赫夫曼编码表存在Map<Byte,String>形式
    // 32->01 97->100 100->11000 等等[形式]
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成赫夫曼编码表示,需要去拼接路径,定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便,重载getCode
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左指数
        getCodes(root.left, "0", stringBuilder);
        //处理root的右指数
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能 : 将传入的node节点的所有叶子节点的赫夫曼编码,存放到huffmanCodes集合
     *
     * @param node          传入的节点
     * @param code          路径:左子结点是0,右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {//如果node为空,不处理
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }
}

//创建node,带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身,比如'a'=>97 ' '=>32
    int weight;//权值,表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "data:" + this.data + ",weight:" + this.weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}