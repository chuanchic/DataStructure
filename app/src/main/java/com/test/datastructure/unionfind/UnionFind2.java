package com.test.datastructure.unionfind;

/**
 * 第二版 UnionFind (树结构)
 * 根据 size 做优化，减少树的深度
 * 缺点：根据 size 做优化可能会存在 树的深度 不减反增 的情况
 */
public class UnionFind2 implements UF{
    private int[] parent;//每个元素是一个节点
    private int[] sz;// sz[i] 表示以i为根节点对应的集合中元素的个数

    public UnionFind2(int size){
        parent = new int[size];
        sz = new int[size];
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;//默认每个节点指向自己，代表根节点
            sz[i] = 1;//默认每个根节点对应的集合中元素的个数为1
        }
    }

    //找到p的根节点(p对应的集合编号)
    //时间复杂度是 O(h)，h 为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound. ");
        }
        //二者相等才是根节点
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //时间复杂度 O(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //时间复杂度 O(h)
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        //合并他们的根节点
        if(sz[pRoot] < sz[qRoot]){//pRoot对应的集合中元素的个数更少
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{//qRoot对应的集合中元素的个数更少
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
