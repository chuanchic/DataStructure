package com.test.datastructure.stackqueue;

public class Note {

    /**
     * 栈 Stack 是一种 后进先出 的数据结构，Last In First Out (LIFO)
     *
     * 时间复杂度分析：
     *
     *     int getSize();         O(1)
     *
     *     boolean isEmpty();     O(1)
     *
     *     void push(E e);        O(1)    会有 resize 操作，通过均摊，依然是 O(1)
     *
     *     E pop();               O(1)
     *
     *     E peek();              O(1)
     */

    /**
     * 队列 Queue 是一种 先进先出 的数据结构，First In First Out (FIFO)
     *
     * 只能从一端(队尾)添加元素，从另一端(队首)取出元素
     *
     * 时间复杂度分析：
     *
     *     int getSize();        O(1)
     *
     *     boolean isEmpty();    O(1)
     *
     *     void enqueue(E e);    O(1)
     *
     *     E dequeue();          O(n)    如何变为O(1)，通过 循环队列 来解决
     *
     *     E getFront();         O(1)
     *
     * 循环队列：
     *
     * 需要两个指针 front 队首指针 tail 队尾指针
     *
     * 设定 front == tail 表示队列为空，因此需要队列浪费一个空间
     *
     * 设定 (tail + 1) % capacity == front 表示队列满
     *
     * 时间复杂度分析：
     *
     *     int getSize();        O(1)
     *
     *     boolean isEmpty();    O(1)
     *
     *     void enqueue(E e);    O(1)
     *
     *     E dequeue();          O(1)
     *
     *     E getFront();         O(1)
     *
     */

}
