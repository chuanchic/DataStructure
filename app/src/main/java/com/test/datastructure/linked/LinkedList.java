package com.test.datastructure.linked;

/**
 * 链表
 */
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;//定义一个 虚拟头节点
    private int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    /**
     * 获取链表中的元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向 表头 添加元素
     */
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        以上3行等于这1行
//        head = new Node(e, head);
//        size++;
        add(0, e);
    }

    /**
     * 向 末尾 添加元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 向 指定位置 添加元素(一般不用)
     */
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index. ");
        }
        //为链表设计 虚拟头结点 可以规避下面这个if判断
//        if(index == 0){
//            addFirst(e);
//        }else{
            Node prev = dummyHead;
            for(int i = 0; i < index; i++){
                prev = prev.next;
            }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
            prev.next = new Node(e, prev.next);
            size++;
//        }
    }

    /**
     * 获取第一个元素
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取最后一个元素
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 获取元素
     */
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Add failed. Illegal index. ");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 更新元素
     */
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index. ");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 是否包含
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 移除第一个
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 移除最后一个
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 移除某个元素
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index. ");
        }
        Node prev = dummyHead;
        for(int i = 0; i < index; i++){
            prev = prev.next;
        }
        Node res = prev.next;
        prev.next = res.next;
        res.next = null;
        size--;
        return res.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

}
