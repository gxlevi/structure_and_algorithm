package com.gxlevi.tree;

import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        //以ArrayList为例,看看底层怎样进行数组扩容
        //维护了一个数组 transient Object[] elementData 初始化是一个空数组
        /**
         * ArrayList 底层仍然进行了数组扩容
         *
         */
        ArrayList<Object> arrayList = new ArrayList<>();
    }
}
