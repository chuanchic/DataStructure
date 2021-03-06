package com.test.datastructure.segmenttree;

public class Note {
    /**
     * 为什么要使用 线段树(区间树) ？
     *   对于有一类问题，我们只关注他的区间
     *
     *   最经典的线段树问题：区间染色
     *   有一面墙，长度为n，每次选择一个区间进行染色
     *   m次操作后，可以看见多少种颜色？
     *   m次操作后，在区间[i， j]内可以看见多少种颜色？
     *
     *   使用 数组 解决这类问题：
     *      1. 染色操作(更新区间)   时间复杂度O(n)
     *      2. 查询操作(查询区间)   时间复杂度O(n)
     *
     *   另一类经典问题：区间查询
     *   查询一个区间[i, j]内的最大值、最小值、区间数字之和
     *
     *   使用 线段树 解决这类问题：
     *      1. 染色操作(更新区间)   时间复杂度O(logn)
     *      2. 查询操作(查询区间)   时间复杂度O(logn)
     *
     * 什么是线段树？
     *   也是二叉树，以求和为例，例如数组 A[0...7]
     *   根节点是 A[0...7] 之和，它的两个子节点分别是 A[0...3] A[4...7] 之和
     *   以此类推，直到 A[0] A[1] A[2] A[3] A[4] A[5] A[6] A[7]
     *   注意：在一分为二的时候，如果不能平分，可以让左子节点少一个元素
     *        这种情况下就不是 满二叉树，也不是 完全二叉树，是 平衡二叉树
     *
     * 什么是 平衡二叉树？
     *   叶子节点不一定在 最后一层，也可能在 倒数第二层
     *   也就是 最大深度 和 最小深度 的差为1
     *
     * 使用 数组 实现平衡二叉树？
     *   可以设定数组的 长度 可以容纳 满二叉树 情况下所有节点
     *   那么 0层：     1个节点
     *       1层：     2个节点
     *       2层：     4个节点
     *       3层：     8个节点
     *       h-1层：   2^(h-1)个节点
     *       最后一层的节点数 大致等于 前面所有层的节点数 之和
     *       如果区间有n个元素，那么满二叉树的情况需要 2n 的空间
     *                                其他情况需要 4n 的空间
     *   结论 如果区间有n个元素，那么数组的长度为 4n
     *       不考虑添加元素，4n的静态空间即可
     *
     */
}
