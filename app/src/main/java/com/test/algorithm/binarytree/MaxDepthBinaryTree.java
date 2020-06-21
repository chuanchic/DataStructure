package com.test.algorithm.binarytree;

public class MaxDepthBinaryTree {

    /**
     * 求一棵二叉树的最高深度
     * 从根节点到叶子节点的最长路径长度
     */
    public static int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
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
