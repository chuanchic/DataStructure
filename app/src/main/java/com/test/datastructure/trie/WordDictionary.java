package com.test.datastructure.trie;

import java.util.TreeMap;

public class WordDictionary {

    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    public WordDictionary(){
        root = new Node();
    }

    public void addWord(String word){
        Node cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(String word){
        return match(root, word, 0);
    }

    //对于单词包含 . 的搜索( . 可以匹配任意字符)
    private boolean match(Node node, String word, int index){
        if(index == word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        }
        for(char nextChar : node.next.keySet()){
            if(match(node.next.get(nextChar), word, index + 1)){
                return true;
            }
        }
        return false;
    }

}
