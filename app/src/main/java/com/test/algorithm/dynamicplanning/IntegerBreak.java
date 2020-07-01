package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class IntegerBreak {

    /**
     * 给定一个正数n，可以将其分割成多个数字的和，若要将这些数字的乘积最大，
     * 求分割的方法（至少分成两个数），算法返回这个最大的乘积。
     * 如 n = 2，返回 1（2 = 1 + 1）
     * 如 n = 10，返回 36（10 = 3 + 3 + 4）
     *
     * 思路：以 4 为例，分割 4 的情况有三种
     * 1 + 3：转为 1 乘以 分割 3 获得的最大乘积
     * 2 + 2：转为 2 乘以 分割 2 获得的最大乘积
     * 3 + 1：转为 3 乘以 分割 1 获得的最大乘积
     *
     * 对于一个递归问题，存在 重叠子问题，最优子结构，就可以使用以下两种方式解决：
     * 方式一：记忆化搜索，自顶向下的解决问题
     * 方式二：动态规划，自底向上的解决问题
     */
    public static int integerBreak(int n){
        if(n == 0){
            return 0;
        }
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < n + 1; i++){
            memoList.add(-1);
        }
        return breakInteger(n, memoList);
    }

    //将 n 进行分割(至少分割两部分)，可以获得的最大乘积
    private static int breakInteger(int n, List<Integer> memoList){
        if(n == 1){//不能继续分割
            return 1;
        }
        if(memoList.get(n) != -1){
            return memoList.get(n);
        }
        int res = -1;//保存最大的乘积，返回
        for(int i = 1; i <= n - 1; i++){
            //将 n 分割成 i 和 n - i
            res = Math.max(res, i * breakInteger(n - i, memoList));
            //陷阱：有可能 i * (n - i) 会更大，所以还需要下面：
            res = Math.max(res, i * (n - i));
        }
        memoList.set(n, res);
        return res;
    }

    /**
     * 动态规划的方式
     */
    public static int integerBreak2(int n){
        if(n == 0){
            return 0;
        }
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < n + 1; i++){
            memoList.add(-1);
        }
        memoList.set(1, 1);
        for(int i = 2; i <= n; i++){
            //求 i 的最大乘积
            int res = -1;
            for(int j = 1; j <= i - 1; j++){
                //分割成 j 和 i - j
                res = Math.max(res, j * memoList.get(i - j));
                res = Math.max(res, j * (i - j));
            }
            memoList.set(i, res);
        }
        return memoList.get(n);
    }
}
