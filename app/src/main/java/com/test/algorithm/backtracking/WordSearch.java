package com.test.algorithm.backtracking;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    /**
     * 给定一个二维平面的字母和一个单词，是否可以在这个二维平面上找到这个单词。
     * 规则是：从一个字母出发，可以横向或者纵向连接二维平面上的其他字母，
     * 同一个位置的字母只能使用一次。
     */
    public static boolean exist(List<List<String>> board, String word){
        if(board == null || board.size() == 0 || TextUtils.isEmpty(word)){
            return false;
        }
        List<List<Boolean>> visited = new ArrayList<>();
        for(int i = 0; i < board.size(); i++){
            visited.add(new ArrayList<Boolean>());
            for(int j = 0; j < board.get(i).size(); j++){
                visited.get(i).add(false);
            }
        }
        m = board.size();
        for(int i = 0; i < board.size(); i++){
            n = board.get(i).size();
            for(int j = 0; j < board.get(i).size(); j++){
                if(searchWord(board, i, j, word, 0, visited)){
                    return true;//已经找到，直接结束，返回 true
                }
            }
        }
        return false;
    }

    private static boolean searchWord(List<List<String>> board, int startX, int startY, String word, int index, List<List<Boolean>> visited){
        if(index == word.length() - 1){
            return board.get(startX).get(startY).equals(word.substring(index, index + 1));
        }
        if(board.get(startX).get(startY).equals(word.substring(index, index + 1))){
            visited.get(startX).set(startY, true);
            //从 startX,startY 开始，向 上下左右 四个方向寻找
            for(int i = 0; i < 4; i++){
                int newX = startX + d[i][0];
                int newY = startY + d[i][1];
                //没有越界，并且没有访问过
                if(inArea(newX, newY) && !visited.get(newX).get(newY)){
                    if(searchWord(board, newX, newY, word, index + 1, visited)){
                        return true;//已经找到，整个搜索过程结束，返回true
                    }
                }
            }
            visited.get(startX).set(startY, false);
        }
        return false;
    }

    //代表 上下左右 四个方向
    private static final int[][] d = new int[][]{
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };
    private static int m, n;
    private static boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
