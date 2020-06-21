package com.test.algorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrderTraversal {

    /**
     * 借助队列 非递归实现 层序遍历
     */
    public static List<List<Integer>> traversal(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        LinkedList<ResultNode> queue = new LinkedList<>();
        queue.add(new ResultNode(root, 0));

        while (!queue.isEmpty()){
            ResultNode rNode = queue.removeFirst();

            //res里存的是集合，代表每一层的数据集，所以res的大小代表层数
            if(rNode.level == res.size()){
                res.add(new ArrayList<Integer>());
            }
            res.get(rNode.level).add(rNode.node.val);

            //把左右孩子加入队列
            if(rNode.node.left != null)
                queue.add(new ResultNode(rNode.node.left, rNode.level + 1));
            if(rNode.node.right != null)
                queue.add(new ResultNode(rNode.node.right, rNode.level + 1));
        }
        return res;
    }

    private static final class ResultNode{
        private TreeNode node;
        private int level;

        public ResultNode(TreeNode node, int level){
            this.node = node;
            this.level = level;
        }
    }

    private static final class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

}
