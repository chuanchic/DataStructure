package com.test.datastructure.linked;

/**
 * 移除某个重复元素(借助 递归 实现)
 *
 * 题外：对于线性数据结构来说，通过 for 循环操作更简单
 *      但是对于非线性数据结构来说，只能通过 递归 来操作
 *
 */
public class RemoveElements3<E> {

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
     * 通过添加变量 depth: 0 来打印递归调用的过程
     */
    public Node removeElements(Node head, E val, int depth){
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + "in " + head);

        if(head == null){

            System.out.print(depthString);
            System.out.println("Return： null");

            return null;
        }else if(head.e == val){
            Node res = removeElements(head.next, val, depth + 1);

            System.out.print(depthString);
            System.out.println("After remove " + val + ": " + res);

            System.out.print(depthString);
            System.out.println("Return: " + res);

            return res;
        }else{
            Node res = removeElements(head.next, val, depth + 1);

            System.out.print(depthString);
            System.out.println("After remove " + val + ": " + res);

            head.next = res;

            System.out.print(depthString);
            System.out.println("Return: " + head);

            return head;
        }
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }

}