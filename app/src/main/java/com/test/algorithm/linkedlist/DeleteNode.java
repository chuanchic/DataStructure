package com.test.algorithm.linkedlist;

public class DeleteNode {

    /**
     * 删除节点，除了尾结点
     */
    public static void deleteNode(ListNode node){
        if(node == null){
            return;
        }
        if(node.next == null){
            return;
        }
        node.val = node.next.val;
        ListNode delNode = node.next;
        node.next = delNode.next;
        delNode.next = null;
    }

    private static final class ListNode{
        private int val;
        private ListNode next;

        public ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

}
