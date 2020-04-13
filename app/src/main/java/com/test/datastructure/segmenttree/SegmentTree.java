package com.test.datastructure.segmenttree;

/**
 * 基于 静态数组 实现的线段树
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for(int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal. ");
        }
        return data[index];
    }

    public int leftChild(int index){
        return 2 * index + 1;
    }

    public int rightChild(int index){
        return 2 * index + 2;
    }

    //在treeIndex的位置创建表示区间[l, r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //int mid = (l + r) / 2;//避免int类型溢出，不推荐这种写法
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //如何处理 当前节点 和 左右两个子节点 之间的关系，由接口来实现
        //对于求和的情况，merge的内部可以 return a + b 就行了
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= tree.length ||
            queryR < 0 || queryR >= tree.length || queryL > queryR){
            throw new IllegalArgumentException("Index is illegal. ");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        if(queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if(queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal. ");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        if(index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }else{
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for(int i = 0; i < tree.length; i++){
            if(tree[i] == null){
                res.append("null");
            }else{
                res.append(tree[i]);
            }
            if(i != tree.length - 1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
