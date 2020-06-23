package com.test.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    /**
     * 回溯法解决排序问题：
     * 给定一个整型数组，其中的每个元素都各不相同，返回这些元素所有的排列可能
     * 如对于 [1, 2, 3]
     * 返回[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     */
    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return resList;
        }
        List<Integer> tempList = new ArrayList<>();
        List<Boolean> usedList = new ArrayList<>();
        for(int num : nums){
            usedList.add(false);
        }
        generatePermutation(nums, 0, tempList, usedList, resList);
        return resList;
    }

    private static void generatePermutation(int[] nums, int cursor, List<Integer> tempList, List<Boolean> usedList, List<List<Integer>> resList){
        if(cursor == nums.length){
            resList.add(new ArrayList<>(tempList));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!usedList.get(i)){
                tempList.add(nums[i]);
                usedList.set(i, true);
                generatePermutation(nums, cursor + 1, tempList, usedList, resList);
                tempList.remove(tempList.size() - 1);
                usedList.set(i, false);
            }
        }
    }

}
