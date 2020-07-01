package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class PartitionEqualSubsetSum {

    /**
     * 给定一个非空数组，其中所有的数字都是正整数，是否可以将这个数组的元素分成两部分，
     * 使得每部分的数字和相等？
     * 如 [1, 5, 11, 5] 可以分成 [1, 5, 5] 和 [11]
     * 如 [1, 2, 3, 5] 就无法分成元素和相等的两部分
     *
     * 限定条件
     * 1. 最多有200个数字
     * 2. 每个数字最大为100
     * 3. 所有数字和为20000，背包最大为10000，n * sum / 2 = 100 * 10000 = 100万
     *
     * 思路：典型的背包问题，在n个物品中选出一定物品，填满 sum/2 的背包
     */
    public static boolean canPartition(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0){
                return false;//必须是正整数
            }else{
                sum += nums[i];
            }
        }
        if(sum % 2 != 0){
            return false;//不能被平分，直接返回
        }
        //记忆化搜索：-1表示未计算，0表示不可填充，1表示可以填充
        List<List<Integer>> memoList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < sum / 2 + 1; j++){
                memoList.get(i).add(-1);
            }
        }
        return tryPartition(nums, nums.length - 1, sum / 2, memoList);
    }

    //使用nums[0...index]，是否可以完全填充一个容量为sum的背包
    private static boolean tryPartition(int[] nums, int index, int sum, List<List<Integer>> memoList){
        if(sum == 0){
            return true;//已经被平分了
        }
        if(sum < 0 || index < 0){
            return false;//无法被平分
        }
        if(memoList.get(index).get(sum) != -1){
            return memoList.get(index).get(sum) == 1;
        }
        //分为两种情况，考虑 当前元素 和不考虑 当前元素
        if(tryPartition(nums, index - 1, sum, memoList) ||
                tryPartition(nums, index - 1, sum - nums[index], memoList)){
            memoList.get(index).set(sum, 1);
        }else{
            memoList.get(index).set(sum, 0);
        }
        return memoList.get(index).get(sum) == 1;
    }

    //动态规划来求解：使用优化后的方式
    public static boolean canPartition2(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0){
                return false;//必须是正整数
            }else{
                sum += nums[i];
            }
        }
        if(sum % 2 != 0){
            return false;//不能被平分，直接返回
        }
        int n = nums.length;
        int C = sum / 2;
        List<Boolean> memoList = new ArrayList<>();
        for(int i = 0; i < C + 1; i++){
            memoList.add(false);
        }
        //判断初始情况，不同容量的背包放入第一个元素，是否可以放满整个背包
        for(int i = 0; i < C + 1; i++){
            memoList.set(i, nums[0] == i);
        }
        for(int i = 1; i < n; i++){
            for(int j = C; j >= nums[i]; j--){
                //分为两种情况：放入 当前元素和不放入 当前元素
                boolean res = memoList.get(j) || memoList.get(j - nums[i]);
                memoList.set(j, res);
            }
        }
        return memoList.get(C);
    }
}
