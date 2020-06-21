package com.gxlevi.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式(逆波兰表达式)
 */
public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的方法
        //说明
        //1.1+((2+3)*4)-5 => 1 2 3 + 4 * 5 -
        //2.因为直接对字符串进行操作不方便,因此先将 "1+((2+3)*4)-5" => 中缀表达式对应的list
        //  即 "1+((2+3)*4)-5"=>ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的list => 后缀表达式对应的list
        //  即 [1,+,(,(,2,+,3,),*,4,),-,5] => [1,2,3,+,4*,+5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list" + infixExpressionList);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list:" + parseSuffixExpressionList);

        System.out.printf("expression=%d\n",calculate(parseSuffixExpressionList));

        //先定义一个逆波兰表达式
        //(3+4)*5-6=>3 4 + 5 * 6 -
        //说明为了方便,逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression1 = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将suffixExpression => 放到ArrayList中
        //2.将ArrayList 传递给一个方法,遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算结果时=" + res);
        System.out.println("计算结果时=" + calculate(getListString(suffixExpression1)));
    }

    //方法,将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//这是一个指针,用于遍历中缀表达式字符串
        String str;//对多位数的拼接工作
        char c;//每遍历到一个字符,就放入到c
        do {
            //如果c是一个非数字,加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {//如果是一个数,需要考虑多位数
                str = "";//先将str 制成空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //方法
    //将得到的中缀表达式对应的list => 后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //Stack<String> s2 = new Stack<>();//存储中间结果的栈,因为没有弹栈的操作,可以用list来替代
        List<String> s2 = new ArrayList<String>();//用于存储中间结果的list

        //遍历ls
        for (String item : ls) {
            //如果是一个数,加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!!将 ( 弹出 s1栈,消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级,将s1栈顶运算符弹出并加入到s2中
                //缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //最后还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存在List,因此按顺序输出就是逆波兰表达式正确的顺序
    }

    //将一个逆波兰表达式,依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建栈,只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数,并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}

