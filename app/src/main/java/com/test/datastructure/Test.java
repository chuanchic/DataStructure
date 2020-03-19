package com.test.datastructure;

import com.test.datastructure.array.Array;
import com.test.datastructure.stackqueue.ArrayQueue;
import com.test.datastructure.stackqueue.ArrayStack;
import com.test.datastructure.stackqueue.BracketMatching;
import com.test.datastructure.stackqueue.LoopQueue;

/**
 * 测试类
 */
public class Test {

    /**
     * 测试动态数组 Array
     */
//    public static void main(String[] args){
//        Array<Integer> arr = new Array<>(20);
//        for(int i = 0; i < 10; i++){
//            arr.addLast(i);
//        }
//        System.out.println(arr.toString());
//        arr.add(1, 100);
//        arr.addFirst(200);
//        System.out.println(arr.toString());
//        arr.remove(1);
//        arr.removeFirst();
//        arr.removeLast();
//        System.out.println(arr.toString());
//    }

    /**
     * 测试栈 ArrayStack
     */
//    public static void main(String[] args){
//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
//        for(int i = 0; i < 4; i++){
//            arrayStack.push(i);
//        }
//        System.out.println(arrayStack.toString());
//        arrayStack.pop();
//        System.out.println(arrayStack.toString());
//    }

    /**
     * 测试 括号匹配 BracketMatching
     */
//    public static void main(String[] args){
//        BracketMatching bracketMatching = new BracketMatching();
//        System.out.println(bracketMatching.isValid("[{()}]"));
//    }

    /**
     * 测试队列 ArrayQueue
     */
//    public static void main(String[] args){
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        for(int i = 0; i < 4; i++){
//            arrayQueue.enqueue(i);
//        }
//        System.out.println(arrayQueue.toString());
//        arrayQueue.dequeue();
//        System.out.println(arrayQueue.toString());
//    }

    /**
     * 测试循环队列 LoopQueue
     */
    public static void main(String[] args){
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for(int i = 0; i < 4; i++){
            loopQueue.enqueue(i);
        }
        System.out.println(loopQueue.toString());
        loopQueue.dequeue();
        System.out.println(loopQueue.toString());
    }

}
