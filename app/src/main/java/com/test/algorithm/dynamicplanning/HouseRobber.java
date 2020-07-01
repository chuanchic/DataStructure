package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class HouseRobber {

    /**
     * 假设一个专业的小偷，打算洗劫一条街的所有房子。每个房子里有不同价值的宝物，
     * 如果选择偷窃连续的两栋房子，就会触发警报系统，求出最多可以偷窃多少宝物？
     * 如[3, 4, 1, 2]，则返回 6 = 4 + 2
     * 如[4, 3, 1, 2]，则返回 6 = 4 + 2
     *
     * 思路：
     * 假设有 n 间房子，偷取 [0...n-1] 范围的所有房子，
     * 偷取第一间房子有 0 到 n-1 种情况：
     * 偷取 0 + 偷取 [2...n-1]，注意 1 不能要，因为不能偷相邻的房子
     * 偷取 1 + 偷取 [3..n-1]，注意 2 不能要，同上
     * ...
     * 偷取 n-1 + 偷取 []
     *
     * 通过以上分析，可以采用 记忆化搜索 或者 动态规划 来解决
     */
    public static int rob(int[] nums){
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            memoList.add(-1);
        }
        return tryRob(nums, 0, memoList);
    }

    //考虑抢劫 nums[index...nums.size()) 这个范围的所有房子
    private static int tryRob(int[] nums, int index, List<Integer> memoList){
        if(index >= nums.length){
            return 0;//已经没有房子可以抢了
        }
        if(memoList.get(index) != -1){
            return memoList.get(index);
        }
        int res = 0;
        for(int i = index; i < nums.length; i++){
            res = Math.max(res, nums[i] + tryRob(nums, i + 2, memoList));
        }
        memoList.set(index, res);
        return res;
    }

    //动态规划 解决这个问题
    public static int rob2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            memoList.add(-1);
        }
        //从最后一间房子开始往前规划，最后一间房子的宝物就为自身所有的
        int lastIndex = nums.length - 1;
        memoList.set(lastIndex, nums[lastIndex]);
        //从倒数第二间房子开始往前规划
        for(int i = nums.length - 2; i >= 0; i--){
            //求解memoList.get(i)
            int res = -1;
            for(int j = i; j < nums.length; j++){
                int temp = j + 2 < nums.length ? memoList.get(j + 2) : 0;
                res = Math.max(res, temp);
            }
            memoList.set(i, res);
        }
        return memoList.get(0);
    }
}
