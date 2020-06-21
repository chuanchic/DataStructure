package com.test.algorithm.binarytree;

public class InvertBinaryTree {

    /**
     * 反转一棵二叉树
     */
    public static TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        //交换两个子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
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
