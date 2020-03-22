package com.test.datastructure.setmap;

/**
 * 基于 BST 实现的 Map
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key, value);
        }
        int compareResult = key.compareTo(node.key);
        if(compareResult < 0){
            node.left = add(node.left, key, value);
        }else if(compareResult > 0){
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }
        return node;
    }

    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if(compareResult < 0){
            return getNode(node.left, key);
        }else if(compareResult > 0){
            return getNode(node.right, key);
        }else{
            return node;
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if(compareResult < 0){
            node.left = remove(node.left, key);
            return node;
        }
        if(compareResult > 0){
            node.right = remove(node.right, key);
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

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
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

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException( key + " doesn't exist! ");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
