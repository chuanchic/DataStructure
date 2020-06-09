package com.test.algorithm.linkedlist;

public class RemoveNthNodeFromEnd {

    /**
     * 给定一个链表，删除倒数第n个节点
     * 例如 1->2->3->4->5->NULL，n = 2
     * 返回 1->2->3->5
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
        if(head == null || n < 0){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        ListNode q = dummyHead;
        for(int i = 0; i <= n; i++){
            if(q == null){
                return head;
            }else{
                q = q.next;
            }
        }
        while (q != null){
            p = p.next;
            q = q.next;
        }
        ListNode delNode = p.next;
        p.next = delNode.next;
        delNode.next = null;

        ListNode retNode = dummyHead.next;
        dummyHead.next = null;
        return retNode;
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
