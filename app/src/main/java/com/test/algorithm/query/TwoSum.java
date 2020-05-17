package com.test.algorithm.query;

import java.util.HashMap;

public class TwoSum {

    /**
     * 给出一个整型数组nums，返回这个数组中两个数字的索引i和j
     * 使得 nums[i] + nums[j] 等于一个给定的target值
     * 两个索引不能相等
     * 如 nums = [2, 7, 11, 15]，target = 9
     * 返回 [0, 1]
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> record = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(record.containsKey(complement)){//找到了
                return new int[]{record.get(complement), i};
            }
            record.put(nums[i], i);
        }
        return null;
    }
}
