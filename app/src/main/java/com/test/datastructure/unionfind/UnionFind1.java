package com.test.datastructure.unionfind;

/**
 * 第一版 UnionFind (Quick Find)
 */
public class UnionFind1 implements UF{
    private int[] id;

    public UnionFind1(int size){
        id = new int[size];
        //默认数组里存的值都是不一样的，即不是同一个集合
        for(int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    private int find(int p){
        if(p < 0 || p >= id.length){
            throw new IllegalArgumentException("p is out of bound. ");
        }
        return id[p];
    }

    @Override
    public int getSize() {
        return id.length;
    }

    //是否所属同一个集合
    //时间复杂度是 O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //将两组不同的集合合并到一个集合中
    //时间复杂度是 O(n)
    @Override
    public void unionElements(int p, int q) {
        int pID = id[p];
        int qID = id[q];
        if(pID == qID){
            return;//所属同一个集合，不用合并
        }
        for(int i = 0; i < id.length; i++){
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }

}
