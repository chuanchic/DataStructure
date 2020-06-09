package com.test.algorithm.stack;

import android.text.TextUtils;

import java.util.Stack;

public class ValidParentheses {

    /**
     * 给定一个字符串，只包含 ( [ { } ] ) ，判定字符串中的括号匹配是否合法
     * 如 ()，()[]{} 是合法的
     * 如 (]，([)] 是非法的
     */
    public boolean isValid(String s){
        if(TextUtils.isEmpty(s)){
            return false;
        }
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            String c = s.substring(i, i + 1);
            if(c.equals("(") || c.equals("{") || c.equals("[")){
                stack.push(c);
            }else{
                String match;
                if(c.equals(")")){
                    match = "(";
                }else if(c.equals("}")){
                    match = "{";
                }else if(c.equals("]")){
                    match = "[";
                }else{
                    return false;
                }
                if(stack.size() == 0){
                    return false;
                }
                String ele = stack.pop();
                if(!ele.equals(match)){
                    return false;
                }
            }
        }
        if(stack.size() > 0){
            return false;
        }
        return true;
    }

}
