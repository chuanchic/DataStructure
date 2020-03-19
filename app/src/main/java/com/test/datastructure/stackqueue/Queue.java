package com.test.datastructure.stackqueue;

/**
 * 队列 接口
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
