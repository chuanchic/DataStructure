package com.test.algorithm.linkedlist;

public class SwapNodes {

    /**
     * 交换两个相邻节点
     */
    public static ListNode swapNodes(ListNode head){
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (pre.next != null && pre.next.next != null){
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            pre.next = node2;
            node2.next = node1;
            node1.next = next;

            pre = node1;
        }

        ListNode req = dummyHead.next;
        dummyHead.next = null;
        return req;
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
