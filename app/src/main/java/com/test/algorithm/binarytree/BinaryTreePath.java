package com.test.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePath {

    /**
     * 给定一棵二叉树，返回所有表示从根节点到叶子节点路径的字符串
     */
    public static List<String> binaryTreePath(TreeNode root){
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        if(root.left == null && root.right == null){
            res.add(String.valueOf(root.val));
            return res;
        }
        List<String> leftRes = binaryTreePath(root.left);
        for(String path : leftRes){
            res.add(root.val + "->" + path);
        }
        List<String> rightRes = binaryTreePath(root.right);
        for(String path : rightRes){
            res.add(root.val + "->" + path);
        }
        return res;
    }

    private static final class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
