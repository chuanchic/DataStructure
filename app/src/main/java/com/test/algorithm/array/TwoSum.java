package com.test.algorithm.array;

public class TwoSum {

    /**
     * 给定一个有序整型数组和一个整数target，在其中寻找两个元素，使其和为target,
     * 返回这两个数的索引
     * 如 nums = [2, 7, 11, 15]，target = 9，
     * 返回数字2，7的索引1，2（索引从1开始计算）
     *
     * 二分搜索是一种解法，对于元素i，寻找 target - nums[i]
     * 时间复杂度是 O(nlogn)
     *
     * 通过 对撞指针 是另一种解法(双索引技术)
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     */
    public static int[] twoSum(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        while (l < r){//两个指针相等的不算
            if(nums[l] + nums[r] == target){
                return new int[]{l+1, r+1};//+1是因为索引从1开始
            }else if(nums[l] + nums[r] < target){
                l++;
            }else{
                r--;
            }
        }
        return null;
    }

}
