package com.test.algorithm.linkedlist;

public class RemoveNodeFromEnd {

    /**
     * 从尾部开始，删除第n个节点
     */
    public static ListNode removeNode(ListNode head, int n){
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        //创建两个指针，使其相差n个节点
        ListNode p = dummyHead;
        ListNode q = dummyHead;
        for(int i = 0; i < n + 1; i++){
            if(q == null){//n不合法，不存在可以删除的节点
                return head;
            }else{
                q = q.next;
            }
        }

        //整体将两个指针向后移动到最后，使 q = null
        while (q != null){
            p = p.next;
            q = q.next;
        }

        //此时p的下一个节点就是要删除的节点
        ListNode delNode = p.next;
        p.next = delNode.next;
        delNode.next = null;

        //释放dummyHead
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
