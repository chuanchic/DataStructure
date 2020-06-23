package com.test.algorithm.backtracking;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    /**
     * 给出一个数字字符串，返回这个数字字符串能表示的所有字母组合
     */
    public static List<String> letterCombinations(String digits){
        List<String> resList = new ArrayList<>();
        if(!TextUtils.isEmpty(digits)){
            findCombination(digits, 0, "", resList);
        }
        return resList;
    }

    private static void findCombination(final String digits, int index, String res, List<String> resList){
        if(index == digits.length()){
            resList.add(res);//最终结果保存到集合
            return;
        }
        try {
            //每个字符必须是0-9之间的数字，不包括1，否则不合法
            int num = Integer.parseInt(digits.substring(index, index + 1));
            if(num >= 0 && num <= 9 && num != 1){
                String letters = letterMap[num];
                for(int i = 0; i < letters.length(); i++){
                    findCombination(digits, index + 1, res + letters.substring(i, i+1), resList);
                }
            }
        }catch (Exception e){
        }
    }

    private static final String[] letterMap = {
            " ",        //0
            "",         //1
            "abc",      //2
            "def",      //3
            "ghi",      //4
            "jkl",      //5
            "mno",      //6
            "pqrs",     //7
            "tuv",      //8
            "wxyz",     //9
    };
}
