package com.test.datastructure.heapqueue;

import com.test.datastructure.array.Array;

/**
 * 基于 动态数组 实现的最大堆
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        //heapify 操作就是对所有的非叶子节点进行 下沉 siftDown
        for(int i = parent(arr.length - 1); i >= 0; i--){
            siftDown(i);
        }
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 获取 父节点 的索引
     */
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent. ");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮的过程
     */
    private void siftUp(int k){
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Can not findMax when heap is empty. ");
        }
        return data.get(0);
    }

    //取出最大元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 下沉的过程
     */
    private void siftDown(int k){
        while (leftChild(k) < data.getSize()){
            //有左子节点
            int j = leftChild(k);//索引值为 左子节点的索引
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                //有右子节点，并且 右子节点 比 左子节点 大
                j = rightChild(k);//索引值为 右子节点的索引
            }
            if(data.get(k).compareTo(data.get(j)) >= 0){
                //父节点 大于等于 左右两个子节点，不需要下沉
                break;
            }
            //做交换(下沉)，然后继续下一次循环
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 替换堆中最大元素
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
