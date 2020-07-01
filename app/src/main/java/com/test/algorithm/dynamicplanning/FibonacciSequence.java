package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSequence {

    /**
     * 斐波那契数列
     * F(0) = 1, F(1) = 1, F(n) = F(n - 1) + F(n - 2)
     * 优化技巧：通过 记忆化搜索，使用一个集合 memoList
     * 这是 自上而下 的解决问题，自下而上 的解决问题参考 fib2
     * 这种 自下而上 的解决问题称作 动态规划，效率更优
     */
    private static long fib(int n, List<Long> memoList){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        //当前 n 没有被记忆过，需要计算一次，再次访问的时候，直接从集合取，不用再次计算
        if(memoList.get(n) == -1){
            long result = fib(n - 1, memoList) + fib(n - 2, memoList);
            memoList.set(n, result);
        }
        return memoList.get(n);
    }

    private static long fib2(int n){
        List<Long> memoList = new ArrayList<>();
        for(int i = 0; i < n + 1; i++){
            memoList.add(-1l);
        }
        memoList.set(0, 0l);
        memoList.set(1, 1l);
        for(int i = 2; i <= n; i++){
            long result = memoList.get(i - 1) + memoList.get(i - 2);
            memoList.set(i, result);
        }
        return memoList.get(n);
    }

    public static void test(){
        //计算 40 的斐波那契数列
        int n = 40;
        //统计从 0 到 n 的每个数字是否被记忆过，-1 代表没有被记忆
        List<Long> memoList = new ArrayList<>();
        for(int i = 0; i < n + 1; i++){
            memoList.add(-1l);
        }
        fib(n, memoList);
    }
}
