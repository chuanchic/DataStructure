package com.test.datastructure.stackqueue;


/**
 * 栈应用 - 括号匹配
 */
public class BracketMatching {

    public boolean isValid(String s){
        ArrayStack<Character> stack = new ArrayStack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char topChar = stack.pop();
                if(c == ')' && topChar != '('){
                    return false;
                }
                if(c == ']' && topChar != '['){
                    return false;
                }
                if(c == '}' && topChar != '{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();// 栈为空才能表明 括号 是成对的
    }

}
