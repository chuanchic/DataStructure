package com.test.datastructure.linked;

/**
 * 移除某个重复元素(不借助 dummyHead 实现)
 */
public class RemoveElements<E> {

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

    /**
     * 移除某个重复元素
     */
    public Node removeElements(Node head, E val){
        while (head != null && head.e == val){
//            Node delNode = head;
//            head = head.next;
//            delNode.next = null;//断开连接
//            可以简写成一句
            head = head.next;
        }
        if(head == null){
            return null;
        }
        Node prevNode = head;
        while (prevNode.next != null){
            if(prevNode.next.e == val){
//                Node delNode = prevNode.next;
//                prevNode.next = delNode.next;
//                delNode.next = null;//断开连接
//                可以简写成一句
                prevNode.next = prevNode.next.next;
            }else{
                prevNode = prevNode.next;
            }
        }
        return head;
    }

}
