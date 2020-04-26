package com.test.datastructure.tree;

import com.test.datastructure.setmap.BSTMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树(平衡二叉树)
 * 平衡二叉树定义：
 *    对于任意一个节点，左子树和右子树的 高度差 不能超过1
 *    通过 平衡因子 表达高度差
 * 实现自平衡通过 左旋转 和 右旋转
 * 维护自平衡的时机：在插入节点之后，沿着插入节点向上维护平衡性
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;//当前节点所处的高度值

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;//新添加的节点都会添加在二叉树的最后，作为叶子节点，所以默认高度为1
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //获取节点所处的高度值
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    //获取节点的平衡因子
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断当前树是不是二分搜索树
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1; i < keys.size(); i++){
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;//中序遍历的结果是升序，如果不是升序，就不是二分搜索树
            }
        }
        return true;
    }

    /**
     * 是不是 平衡二叉树
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 是不是 平衡二叉树(递归算法)
     */
    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
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
        //进行平衡维护
        return balanceAssert(node);
    }

    /**
     * 更新节点数据
     */
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException( key + " doesn't exist! ");
        }
        node.value = value;
    }

    /**
     * 进行平衡维护
     */
    private Node balanceAssert(Node node){
        if(node == null){
            return null;
        }
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){//平衡因子大于1
            System.out.println("unbalancee: " + balanceFactor);
        }
        //平衡维护
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){//LL情况
            return rightRotate(node);//进行右旋转
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){//RR情况
            return leftRotate(node);//进行左旋转
        }
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){//LR情况
            node.left = leftRotate(node.left);//转成LL情况
            return rightRotate(node);//进行右旋转
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){//RL情况
            node.right = rightRotate(node.right);//转成RR情况
            return leftRotate(node);//进行左旋转
        }
        return node;
    }

    /**
     * 对节点y进行向右旋转操作，返回旋转后新的节点x
     *         y                             x
     *        /  \                         /    \
     *       x   T4                       z      y
     *      /  \        向右旋转 ->       / \    /  \
     *     z   T3                      T1  T2  T3  T4
     *    /  \
     *   T1  T2
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转
        x.right = y;
        y.left = T3;
        //更新height，x 和 y 的高度值改变了
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        //向左旋转
        x.left = y;
        y.right = T2;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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
     * 获取节点
     */
    public V getNode(K key){
        Node node = getNode(root, key);
        if(node == null){
            return null;
        }
        return node.value;
    }

    /**
     * 获取节点
     */
    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return getNode(node.left, key);
        }else if(compareResult > 0){
            return getNode(node.right, key);
        }else{
            return node;
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
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
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
     * 没有进行平衡维护，用remove方法替换
     */
//    public V removeMin(){
//        V res = minimum();
//        root = removeMin(root);
//        return res;
//    }

//    private Node removeMin(Node node){
//        if(node.left == null){
//            Node rightNode = node.right;
//            node.right = null;
//            size--;
//            return rightNode;
//        }
//        node.left = removeMin(node.left);
//        return node;
//    }

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
    public V remove(K key){
        Node node = getNode(root, key);
        if(node == null){
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        Node retNode;
        int compareResult = key.compareTo(node.key);
        if(compareResult < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if(compareResult > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }else{//node就是要被删除的节点
            if(node.left == null){//情况一 没有左子树
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }else if(node.right == null){//情况二 没有右子树
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else{//情况三 左右子树都有
                //操作右子树最小节点
                Node successor = minimum(node.right);
//                successor.right = removeMin(node.right);
                //removeMin方法替换成remove方法，因为removeMin方法没有进行平衡维护
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                //这儿不需要size--，因为 removeMin 已经做了size--
                retNode = successor;
            }
        }
        //进行平衡维护
        return balanceAssert(retNode);
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
