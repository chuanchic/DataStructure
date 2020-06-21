package com.test.algorithm.binarytree;

public class LowestCommonAncestor {

    /**
     * 给定一棵二分搜索树和两个节点，寻找这两个节点的最近公共祖先
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || p == null || q == null){
            return null;
        }
        // p，q 都在 root 的左面
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        // p，q 都在 root 的右面
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        //存在三种情况，都直接返回 root
        //1. p q 一个在左面，一个在右面
        //2. p 就是 root，q在左面或者右面
        //3. q 就是 root，p在左面或者右面
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
