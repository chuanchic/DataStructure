package com.test.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    /**
     * 给出两个整数 n 和 k，求在 1...n 这n个数字中选出k个数字的所有组合，
     * 如 n = 4， k = 2
     * 结果为 [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
     */
    public static List<List<Integer>> combine(int n, int k){
        List<List<Integer>> resList = new ArrayList<>();
        if(n <= 0 || k <= 0 || k > n){
            return resList;
        }
        List<Integer> tempList = new ArrayList<>();
        generateCombinations(n, k, 1, tempList, resList);
        return resList;
    }

    private static void generateCombinations(int n, int k, int start, List<Integer> tempList, List<List<Integer>> resList){
        if(tempList.size() == k){
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        //优化写法：(剪枝)
        //for(int i = start; i <= n - (k - tempList.size()) + 1; i++))
        for(int i = start; i <= n; i++){
            tempList.add(i);
            generateCombinations(n, k, i + 1, tempList, resList);
            tempList.remove(tempList.size() - 1);
        }
    }

}
