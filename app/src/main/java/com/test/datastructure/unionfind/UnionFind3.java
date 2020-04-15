package com.test.datastructure.unionfind;

/**
 * 第三版 UnionFind (树结构)
 * 根据 rank 做优化，减少树的深度
 * 解决第二版 不减反增 的问题
 * 同时在 find() 的时候进行路径压缩，进一步优化
 * 添加上 路径压缩 之后，rank就不在代表树的真实层数，仅仅是一个排名
 */
public class UnionFind3 implements UF{
    private int[] parent;//每个元素是一个节点
    private int[] rank;// rank[i] 表示以i为根节点对应的集合所表示的树的层数

    public UnionFind3(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;//默认每个节点指向自己，代表根节点
            rank[i] = 1;//默认每个根节点对应的集合所表示的树的层数为1
        }
    }

    //找到p的根节点(p对应的集合编号)
    //时间复杂度是 O(h)，h 为树的高度
//    private int find(int p){
//        if(p < 0 || p >= parent.length){
//            throw new IllegalArgumentException("p is out of bound. ");
//        }
//        //二者相等才是根节点
//        while (p != parent[p]){
//            parent[p] = parent[parent[p]];//进一步优化：路径压缩
//            p = parent[p];
//        }
//        return p;
//    }

    //递归算法 实现路径压缩
    //让每个根节点对应的集合所表示的树只有两层
    //由于是递归算法，所以整体性能要略差于上面那种
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound. ");
        }
        //二者相等才是根节点
        if (p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];
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
        if(rank[pRoot] < rank[qRoot]){//pRoot对应的集合所表示的树的层数更少
            parent[pRoot] = qRoot;
        }else if(rank[qRoot] < rank[pRoot]){//qRoot对应的集合所表示的树的层数更少
            parent[qRoot] = pRoot;
        }else{//他们层数一样
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
