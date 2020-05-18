package com.test.algorithm.linkedlist;

public class RemoveElements {

    /**
     * 在链表中删除值为val的所有节点
     */
    public static ListNode removeElements(ListNode head, int val){
        while (head != null && head.val == val){
            ListNode del = head;
            head = del.next;
            del.next = null;
        }
        if(head == null){
            return null;
        }
        ListNode cur = head;
        while (cur.next != null){
            if(cur.next.val == val){
                ListNode del = cur.next;
                cur.next = del.next;
                del.next = null;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 在链表中删除值为val的所有节点
     */
    public static ListNode removeElements2(ListNode head, int val){
        if(head == null){
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null){
            if(cur.next.val == val){
                ListNode del = cur.next;
                cur.next = del.next;
                del.next = null;
            }else{
                cur = cur.next;
            }
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
