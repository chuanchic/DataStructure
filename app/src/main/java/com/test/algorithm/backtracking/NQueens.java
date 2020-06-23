package com.test.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * n个皇后摆放在 n*n 的棋盘格中，使得横、竖和两个对角线方向均不会同时出现两个皇后
     * 例如 4 皇后有两个解：
     *    [
     *      ".Q..",
     *      "...Q",
     *      "Q...",
     *      "..Q."
     *    ]
     *    [
     *      ..Q.,
     *      Q...,
     *      ...Q,
     *      .Q..
     *    ]
     */
    public static List<List<String>> solveNQueens(int n){
        List<List<String>> resList = new ArrayList<>();
        List<Integer> rowList = new ArrayList<>();
        List<Boolean> colList = new ArrayList<>();//列对应的标记
        List<Boolean> diaList1 = new ArrayList<>();//对角线对应的标记
        List<Boolean> diaList2 = new ArrayList<>();//对角线对应的标记
        for(int i = 0; i < n; i++){
            colList.add(false);
        }
        for(int i = 0; i < 2*n-1; i++){
            diaList1.add(false);
            diaList2.add(false);
        }
        putQueen(n, 0, rowList, resList, colList, diaList1, diaList2);
        return resList;
    }

    //尝试在一个n皇后问题中，摆放第index行的皇后位置
    private static void putQueen(int n, int index, List<Integer> rowList, List<List<String>> resList, List<Boolean> colList, List<Boolean> diaList1, List<Boolean> diaList2){
        if(index == n){
            resList.add(generateBoard(n, rowList));
            return;
        }
        for(int i = 0; i < n; i++){
            //尝试将第index行的皇后摆放在第i列
            //列上不冲突，对角线上不冲突，这三个条件不太理解
            if(!colList.get(i) && !diaList1.get(index + i) && !diaList2.get(index - i + n - 1)){
                rowList.add(i);//符合条件，将第index行的皇后摆放在第i列
                colList.set(i, true);
                diaList1.set(index + i, true);
                diaList2.set(index - i + n - 1, true);
                //继续下一行的摆放，即 index + 1 行
                putQueen(n, index + 1, rowList, resList, colList, diaList1, diaList2);
                //需要回溯，并继续下一个循环，继续寻找其他情况
                rowList.remove(rowList.size() - 1);
                colList.set(i, false);
                diaList1.set(index + i, false);
                diaList2.set(index - i + n - 1, false);
            }
        }
    }

    private static List<String> generateBoard(int n, List<Integer> rowList){
        List<String> resList = new ArrayList<>();
        for(int i = 0; i < rowList.size(); i++){
            //为每一行生成一个字符串
            int index = rowList.get(i);
            String str = "";
            for(int j = 0; j < n; j++){
                if(j == index){
                    str += "Q";
                }else{
                    str += ".";
                }
            }
            resList.add(str);
        }
        return resList;
    }
}
