package com.test.algorithm.binarytree;

public class PathSum {

    /**
     * 给出一棵二叉树以及一个数字sum，判断在这课二叉树上是否存在一条从
     * 根到叶子的路径，其路径上的所有节点和为sum
     */
    public static boolean hasPathSum(TreeNode root, int sum){
        //这种判断有问题，并不能代表到了叶子节点
//        if(root == null){
//            return sum == 0;
//        }
        if(root == null){
            return false;
        }
        //需要判断叶子节点
        if(root.left == null && root.right == null){
            return root.val == sum;
        }
        if(hasPathSum(root.left, sum - root.val)){
            return true;
        }
        if(hasPathSum(root.right, sum - root.val)){
            return true;
        }
        return false;
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
