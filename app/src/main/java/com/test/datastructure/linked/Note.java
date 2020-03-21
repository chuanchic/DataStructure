package com.test.datastructure.linked;

public class Note {

    /**
     * 链表 LinkedList
     *
     * 数据存储在 节点 Node 中
     *
     * class Node{
     *     E e;
     *     Node next;
     * }
     *
     * 优点：真正的动态，不需要处理固定容量的问题
     *
     * 缺点：丧失了随机访问的能力
     *
     *
     *
     * 时间复杂度分析：
     *
     * addLast()              O(n)  需要遍历到最后一个
     *
     * addFirst()             O(1)
     *
     * add(index, e)          O(n/2) = O(n)
     *
     * removeLast()           O(n)  需要遍历到最后一个
     *
     * removeFirst()          O(1)
     *
     * remove(index)          O(n/2) = O(n)
     *
     * set(index, e)          O(n)
     *
     * get(index)             O(n)
     *
     * contains(e)            O(n)
     *
     * 总体上对于 链表 的增删改查 时间复杂度是 O(n)
     *
     *
     *
     * 如果只对表头进行操作 增 删 查，时间复杂度就是 O(1)，可以根据这个特性实现一个 栈 LinkedListStack
     *
     * LinkedListStack 相对于 ArrayStack 的优点：
     *
     * 由于 ArrayStack 存在 扩容 和 缩容 ，所以性能会稍微差一点，但这不是一定的
     *
     * 因为对于 LinkedListStack 来说，存在很多 new Node() 操作，性能也可能会比 ArrayStack 差
     *
     *
     *
     * 通过链表实现 队列，需要在队尾添加一个指针 tail
     *
     * 这样在队尾添加一个元素时间复杂度就是 O(1)，在队首删除一个元素时间复杂度也是 O(1)
     *
     * 这种实现方式里，可以不需要 dummyHead 虚拟头结点
     *
     * LinkedListQueue 和 LoopQueue 性能差不多，但是都比 ArrayQueue 性能要高很多
     *
     *
     *
     * 双向循环链表
     *
     * class Node{
     *     E e;
     *     Node next, prev;
     * }
     *
     */

}
