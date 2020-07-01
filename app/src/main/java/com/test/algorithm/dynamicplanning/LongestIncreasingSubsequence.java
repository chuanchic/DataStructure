package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    /**
     * 给定一个整数序列，求其中的最长上升子序列的长度
     * 如 [10, 9, 2, 5, 3, 7, 101, 18]
     * 其最长上升子序列的长度为 4，为 [2, 5, 7, 101]
     *
     * 暴力解法的思路：找出所有的子序列，再选出最长的上升子序列，时间复杂度 O((2^n)*n)
     *
     * 记忆化搜索思路：略
     *
     * 动态规划思路：从第一个元素开始，LIS(i)表示以第i个数字为结尾的最长上升子序列的长度
     */
    public static int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        // memo[i] 表示以 nums[i] 为结尾的最长上升子序列的长度
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            memoList.add(1);//默认值为1，自身的长度
        }
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    memoList.set(i, Math.max(memoList.get(i), 1 + memoList.get(j)));
                }
            }
        }
        int res = 1;
        for(int i = 0; i < nums.length; i++){
            res = Math.max(res, memoList.get(i));
        }
        return res;
    }
}
