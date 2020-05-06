package com.test.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 基于 二分搜索树 改进的 红黑树
 */
public class RBTree <K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
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
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;//保持根节点为黑色节点
    }

    /**
     * 递归添加(精简写法)
     */
    private Node add(Node node, K key, V value) {
        if(node == null){
            size++;
            return new Node(key, value);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = add(node.left, key, value);
        }else if (compareResult > 0) {
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }
        //维护红黑树的性质
        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }

    /**
     * 左旋转
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     */
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色翻转
     */
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 是否包含
     */
    public boolean contains(K key){
        return contains(root, key);
    }

    /**
     * 递归包含
     */
    private boolean contains(Node node, K key){
        if(node == null){
            return false;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return contains(node.left, key);
        }else if(compareResult > 0){
            return contains(node.right, key);
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
        System.out.println(node.value);
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
            System.out.println(cur.value);

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
        System.out.println(node.value);
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
        System.out.println(node.value);
    }

    /**
     * 二分搜索树的 层序遍历
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.value);

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
    public V minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).value;
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
    public V maximum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).value;
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
    public V removeMin(){
        V res = minimum();
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
    public V removeMax(){
        V res = maximum();
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
    public void remove(K key){
        root = remove(root, key);
    }

    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if(compareResult < 0){
            node.left = remove(node.left, key);
            return node;
        }else if(compareResult > 0){
            node.right = remove(node.right, key);
            return node;
        }else{//走到这儿，node就是要被删除的节点
            if(node.left == null){//情况一 没有左子树
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }else if(node.right == null){//情况二 没有右子树
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else{//情况三 左右子树都有
                //操作右子树最小节点
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                //这儿不需要size--，因为 removeMin 已经做了size--
                return successor;
            }
        }
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
        res.append(generateDepthString(depth) + node.value + "\n");
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
