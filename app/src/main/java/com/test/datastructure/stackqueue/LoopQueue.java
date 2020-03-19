package com.test.datastructure.stackqueue;

/**
 * 基于 动态数组 实现的队列 循环队列
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        //因为 循环队列 会浪费一个空间，所以 数组的长度 要相对于 我们期望的长度 +1
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        //因为 循环队列 会浪费一个空间，所以容量要 -1
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if((tail + 1) % data.length == front){
            //队列已满，需要扩容
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            //需要缩容
            resize(getCapacity() / 2);
        }
        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            //原队列从 队首 开始，赋值给 新队列
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;

    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d \n", size, getCapacity()));
        res.append("front [");
        for(int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail){//不是最后一个元素
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

}
