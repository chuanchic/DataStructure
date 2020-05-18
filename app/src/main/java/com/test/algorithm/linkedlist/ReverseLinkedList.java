package com.test.algorithm.linkedlist;

public class ReverseLinkedList {

    /**
     * 反转链表
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public static ListNode reverseLinkedList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 创建链表
     */
    public static ListNode createLinkedList(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for(int i = 1; i < arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
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
