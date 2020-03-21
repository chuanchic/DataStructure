package com.test.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加元素(完整写法)
     */
//    public void add(E e){
//        if(root == null){
//            root = new Node(e);
//            size++;
//        }else{
//            add(root, e);
//        }
//    }

    /**
     * 递归添加(完整写法)
     */
//    private void add(Node node, E e) {
//        int compareResult = e.compareTo(node.e);
//        if (compareResult == 0) {
//            return;//重复元素 不用添加
//        }
//        if (compareResult < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        }
//        if (compareResult > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//        if (compareResult < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

    /**
     * 添加元素(精简写法)
     */
    public void add(E e){
        root = add(root, e);
    }

    /**
     * 递归添加(精简写法)
     */
    private Node add(Node node, E e) {
        if(node == null){
            size++;
            return new Node(e);
        }
        int compareResult = e.compareTo(node.e);
        if (compareResult < 0) {
            node.left = add(node.left, e);
        }else if (compareResult > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 是否包含
     */
    public boolean contains(E e){
        return contains(root, e);
    }

    /**
     * 递归包含
     */
    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        int compareResult = e.compareTo(node.e);
        if (compareResult < 0) {
            return contains(node.left, e);
        }else if(compareResult > 0){
            return contains(node.right, e);
        }else{
            return true;
        }
    }

    /**
     * 二分搜索树的 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树 前序遍历 非递归实现
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二分搜索树的 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的 层序遍历
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 最小值
     */
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 最大值
     */
    public E maximum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 移除最小值
     */
    public E removeMin(){
        E res = minimum();
        root = removeMin(root);
        return res;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 移除最大值
     */
    public E removeMax(){
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 移除某个节点
     */
    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if(node == null){
            return null;
        }
        int compareResult = e.compareTo(node.e);
        if(compareResult < 0){
            node.left = remove(node.left, e);
            return node;
        }
        if(compareResult > 0){
            node.right = remove(node.right, e);
            return node;
        }
        //走到这儿，node就是要被删除的节点
        //情况一 没有左子树
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        //情况二 没有右子树
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        //情况三 左右子树都有
        //操作右子树最小节点
        Node successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = null;
        node.right = null;
        //这儿不需要size--，因为 removeMin 已经做了size--
        return successor;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }

}
