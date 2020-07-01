package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class ClimbingStairs {

    /**
     * 有一个楼梯，总共有 n 阶台阶，每一次可以上一个台阶，也可以上两个台阶，
     * 爬这样的楼梯一共有多少种不同的方法？
     * 如 n = 3，爬上这个楼梯的方法有：
     * [1, 1, 1], [1, 2], [2, 1]
     *
     * 思路：爬上第n个台阶有两种情况，
     * 情况一：爬上 n-1 台阶的时候，再爬一个，
     * 情况二：爬上 n-2 台阶的时候，再爬两个，
     * 那么爬上第n个台阶的方式就有 f(n) = f(n-1) + f(n-2)
     * 也就是对 斐波那契数列 的求解
     */
     public static long climbStairs(int n){
         if(n == 0){
             return 0;
         }
         List<Long> memoList = new ArrayList<>();
         for(int i = 0; i < n + 1; i++){
             memoList.add(-1l);
         }
         return calcWays(n, memoList);
     }

     //递归写法：自上而下
     private static long calcWays(int n, List<Long> memoList){
         if(n == 1){
             return 1;//只有一阶台阶，就只有一种情况：迈一个台阶
         }
         if(n == 2){
             return 2;//有两阶台阶，就有两种情况：迈一个台阶或迈两个台阶
         }
         if(memoList.get(n) == -1){
             long result = calcWays(n - 1, memoList) + calcWays(n - 2, memoList);
             memoList.set(n, result);
         }
         return memoList.get(n);
     }

    /**
     * 动态规划 的实现方式：自下而上
     */
     public static long climbStairs2(int n){
         if(n == 0){
             return 0;
         }
         List<Long> memoList = new ArrayList<>();
         for(int i = 0; i < n + 1; i++){
             memoList.add(-1l);
         }
         memoList.set(1, 1l);
         memoList.set(2, 2l);
         for(int i = 3; i <= n; i++){
             long result = memoList.get(i - 1) + memoList.get(i - 2);
             memoList.set(i, result);
         }
         return memoList.get(n);
     }
}
