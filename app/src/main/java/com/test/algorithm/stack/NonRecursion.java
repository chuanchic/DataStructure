package com.test.algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NonRecursion {

    /**
     * 通过 stack 非递归实现 前序、中序、后序遍历
     */
    public static List<Integer> traversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.empty()){
            Command command = stack.pop();
            if("print".equals(command.s)){
                result.add(command.node.val);
            }else if("go".equals(command.s)){
                //前序遍历
//                if(command.node.right != null)
//                    stack.push(new Command("go", command.node.right));
//                if(command.node.left != null)
//                    stack.push(new Command("go", command.node.left));
//                stack.push(new Command("print", command.node));

                //中序遍历
//                if(command.node.right != null)
//                    stack.push(new Command("go", command.node.right));
//                stack.push(new Command("print", command.node));
//                if(command.node.left != null)
//                    stack.push(new Command("go", command.node.left));

                //后序遍历
                stack.push(new Command("print", command.node));
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right));
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left));
            }
        }
        return result;
    }

    private static final class Command{
        private String s; // go, print
        private TreeNode node;

        public Command(String s, TreeNode node){
            this.s = s;
            this.node = node;
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
