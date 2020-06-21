package com.test.algorithm.binarytree;

public class PathSum2 {

    /**
     * 给出一棵二叉树以及一个数字sum，判断在这棵二叉树上存在多少条路径，
     * 其路径上的所有节点的和为sum
     * 其中路径不一定要起始于根节点，终止于叶子节点
     * 路径可以从任意节点开始，但是只能是向下走的
     */
    public static int pathSum(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int res = findPath(root, sum);
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    /**
     * 在以node为根节点的二叉树中，寻找包含node的路径，和为sum
     * 返回这样的路径个数
     */
    private static int findPath(TreeNode node, int sum){
        if(node == null){
            return 0;
        }
        int res = 0;
        if(node.val == sum){
            res += 1;
            //注意这儿不能return，因为node的值可能为负数，几次加减之后也可能为sum
        }
        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);
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
