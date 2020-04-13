package com.test.datastructure.trie;

import java.util.TreeMap;

public class MapSum {

    private class Node{
        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    public MapSum(){
        root = new Node();
    }

    public void insert(String word, int val){
        Node cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix){
        //1. 找到以 prefix 为开头的节点
        Node cur = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;//没有找到
            }
            cur = cur.next.get(c);
        }
        //2. 递归遍历所有以 prefix 为开头的，并且是单词的节点，将他们的值加起来
        return sum(cur);
    }

    private int sum(Node node){
        int res = node.value;
        for(char c : node.next.keySet()){
            res += sum(node.next.get(c));
        }
        return res;
    }

}
