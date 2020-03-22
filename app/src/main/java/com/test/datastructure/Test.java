package com.test.datastructure;

import com.test.datastructure.linked.LinkedList;

/**
 * 测试类
 */
public class Test {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < 6; i++){
            list.addFirst(i);
        }
        list.add(2, 4);
        list.add(2, 4);
        list.add(2, 4);
        list.addFirst(4);
        list.addFirst(4);
        list.addLast(4);
        list.addLast(4);
        System.out.println(list.toString());
        list.removeElement(4);
        System.out.println(list.toString());
    }

}
