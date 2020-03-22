package com.test.datastructure.setmap;

public class Note {

    /**
     * BSTSet
     *
     *   基于 BST 实现的集合，有序集合
     *
     *   时间复杂度分析：
     *
     *   add()        每一步查找都是一分为二，跟 层级(高度) 有关，所以是 O(h)，换算成 O(logn)
     *
     *   contains()   同上
     *
     *   remove()     同上
     *
     *
     * LinkedListSet
     *
     *   基于 LinkedList 实现的集合，无序集合
     *
     *   时间复杂度分析：
     *
     *   add()        在表头添加 O(1)，为了保证不重复又做了 contains() 操作，所以也是 O(n)
     *
     *   contains()   O(n)
     *
     *   remove()     需要先找到 被删除元素 的前一个元素，所以也是 O(n)
     *
     *
     *   结论：O(logn) 理想状态下比 O(n) 快一个量级，最差情况下(退化成链表)等于 O(n)
     *        如何解决？借助平衡二叉树
     *
     *
     * 映射 LinkedListMap (无序映射，太慢，基于 哈希表 就会很快)
     *
     * class Node{
     *     K key;
     *     V value;
     *     Node next;
     * }
     *
     * 时间复杂度分析：
     *
     * add            O(n)
     *
     * remove         O(n)
     *
     * set            O(n)
     *
     * get            O(n)
     *
     * contains       O(n)
     *
     *
     * 映射 BSTMap (有序映射，key 就是顺序)
     *
     * class Node{
     *     K key;
     *     V value;
     *     Node left;
     *     Node right;
     * }
     *
     * 时间复杂度分析：
     *
     * add            O(h) 或 O(logn) 最差情况退化成 O(n)
     *
     * remove         O(h) 或 O(logn) 最差情况退化成 O(n)
     *
     * set            O(h) 或 O(logn) 最差情况退化成 O(n)
     *
     * get            O(h) 或 O(logn) 最差情况退化成 O(n)
     *
     * contains       O(h) 或 O(logn) 最差情况退化成 O(n)
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
