package com.test.algorithm.query;

import java.util.ArrayList;
import java.util.List;

public class ContainsDuplicate {

    /**
     * 给出一个整型数组nums和一个整数k，是否存在索引i和j，
     * 使得 nums[i] == nums[j]，并且i和j之间的差不超过k
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k){
        List<Integer> record = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(record.contains(nums[i])){
                return true;
            }
            record.add(nums[i]);
            //当前已经相差 k + 1 - 1 = k ，因此移除第一个
            if(record.size() == k + 1){
                record.remove(0);
            }
        }
        return false;
    }

    /**
     * 给出一个整型数组nums，是否存在索引i和j，
     * 使得 nums[i] 和 nums[j] 之间的差别不超过给定的整数t，
     * 且 i 和 j 之间的差别不超过给定的整数k
     */
    public static boolean containsNearbyDuplicate(int[] nums, int t, int k){
        List<Integer> record = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            for(int temp : record){
                if(Math.abs(temp - nums[i]) <= t){
                    return true;
                }
            }
            record.add(nums[i]);
            //当前已经相差 k + 1 - 1 = k ，因此移除第一个
            if(record.size() == k + 1){
                record.remove(0);
            }
        }
        return false;
    }

}
