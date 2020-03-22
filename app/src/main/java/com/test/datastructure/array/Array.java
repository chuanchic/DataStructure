package com.test.datastructure.array;

/**
 * 二次封装我们自己的 数组
 * 技术一：泛型
 * 技术二：动态数组
 */
public class Array<E> {

    private E[] data;
    private int size;

    public Array(){
        this(10);
    }

    public Array(int capacity){
        //java语法规范不许 new E[capacity]，可以间接通过 Object
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for(int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 获取 数组大小
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取 数组容量
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 数组末尾 添加元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 数组开始 添加元素
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 指定位置 添加元素
     */
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add last failed. Require index >= 0 and index <= size.");
        }
        if(size == data.length){
            resize(data.length * 2);
        }
        // index 位置(包括index)往后的元素 后移，空出 index 位置
        for(int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取元素
     */
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    /**
     * 更新元素
     */
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 是否包含
     */
    public boolean contains(E e){
        for(int i = 0; i < size; i++){
            if(e.equals(data[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找索引
     */
    public int find(E e){
        for(int i = 0; i < size; i++){
            if(e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * 移除元素
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E remove = data[index];
        for(int i = index + 1; i < size; i++){
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;//这句不是必须的，置为 null 有利于垃圾回收
        if(size == data.length / 4){//减少容量，为四分之一的时候才减少，可以规避 复杂度震荡
            if(data.length / 2 != 0){//可能为0
                resize(data.length / 2);
            }
        }
        return remove;
    }

    /**
     * 移除第一个元素
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 移除最后一个元素
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 移除某个元素
     */
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    /**
     * 数值交换
     */
    public void swap(int i, int j){
        if(i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("Index is illegal. ");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d \n", size, data.length));
        res.append("[");
        for(int i = 0; i < size; i++){
            res.append(data[i]);
            if(i < size - 1){//不是最后一个元素
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 数组扩容
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
