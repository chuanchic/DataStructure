package com.test.datastructure.stackqueue;

/**
 * 栈 接口
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();

}
