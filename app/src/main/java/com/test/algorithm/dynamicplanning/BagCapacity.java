package com.test.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

public class BagCapacity {

    /**
     * 有一个背包，容量为 C (Capacity)，现有n种不同的物品，编号为 0...n-1，
     * 其中每一件物品的重量为w(i)，其价值为v(i)，问可以向这个背包中放哪些物品，
     * 使得不超过背包容量的基础上，物品的总价值最大。
     */
    public static int knapsack(List<Integer> wList, List<Integer> vList, int c){
        if(wList == null || wList.size() == 0 || vList == null || vList.size() == 0 || c <= 0){
            return 0;
        }
        //采用记忆化搜索，有两个条件 index 和 c，因此采用 二维数组 的方式
        //第一维代表 背包的索引，第二维代表 剩余容量，这样可以记录下来 不同索引 不同容量 对应的最大价值
        List<List<Integer>> memoList = new ArrayList<>();
        for(int i = 0; i < wList.size(); i++){
            memoList.add(new ArrayList<Integer>());
            for(int j = 0; j < c + 1; j++){
                memoList.get(i).add(-1);
            }
        }
        return bestValue(wList, vList, wList.size() - 1, c, memoList);
    }

    //用 [0...index] 的物品，填充容积为 c 的背包的最大价值
    //wList 物品对应的容量集合，vList 物品对应的价值集合，第 index 个物品，c 剩余容量
    private static int bestValue(List<Integer> wList, List<Integer> vList, int index, int c, List<List<Integer>> memoList){
        //如果 已经没有物品了，或者 已经没有容量了
        if(index < 0 || c <= 0){
            return 0;
        }
        //如果 对应索引 对应容量 已经记录过最大价值，直接返回
        if(memoList.get(index).get(c) != -1){
            return memoList.get(index).get(c);
        }
        //判断第index个物品，存在两种可能，一种是不会放入背包，一种是会放入背包
        //不会放入背包的情况，最大价值为
        int res = bestValue(wList, vList, index - 1, c, memoList);
        //会放入背包的情况，最大价值为
        if(c >= wList.get(index)){//如果容量够放入这个物品
            int res2 = vList.get(index) + bestValue(wList, vList, index - 1, c - wList.get(index), memoList);
            res = Math.max(res, res2);//取两种可能的最大价值
        }
        memoList.get(index).set(c, res);
        return res;
    }

    //动态规划 的方式来求解
    public static int knapsack2(List<Integer> wList, List<Integer> vList, int c){
        if(wList == null || wList.size() == 0 || vList == null || vList.size() == 0 || c <= 0){
            return 0;
        }
        //采用记忆化搜索，有两个条件 index 和 c，因此采用 二维数组 的方式
        //第一维代表 背包的索引，第二维代表 剩余容量，这样可以记录下来 不同索引 不同容量 对应的最大价值
        List<List<Integer>> memoList = new ArrayList<>();
        for(int i = 0; i < wList.size(); i++){
            memoList.add(new ArrayList<Integer>());
            for(int j = 0; j < c + 1; j++){
                memoList.get(i).add(-1);
            }
        }
        //动态规划 是自底向上的求解，先求最基本的问题，然后自底向上的求出 不同索引 不同容量 对应的最大值
        //最基本问题的求解是 索引为0 容量为 0...c 的求解
        for(int j = 0; j < c + 1; j++){
            int res = j >= wList.get(0) ? vList.get(0) : 0;
            memoList.get(0).set(j, res);
        }
        //然后求解 其他索引 容量为 0...c 对应的最大价值
        for(int i = 1; i < wList.size(); i++){
            for(int j = 0; j < c + 1; j++){
                //不放入的情况，最大价值为
                int res = memoList.get(i - 1).get(j);
                //放入的情况，最大价值为
                if(j >= wList.get(i)){//容量够用
                    int res2 = vList.get(i) + memoList.get(i - 1).get(j - wList.get(i));
                    res = Math.max(res, res2);//保留两种情况的最大值
                }
                memoList.get(i).set(j, res);
            }
        }
        return memoList.get(wList.size() - 1).get(c);
    }

    //背包问题的优化1：
    //第i行元素只依赖于第i-1行元素，理论上只需要保持两行元素，通过对 memoList 模运算 奇偶 0 1
    public static int knapsack3(List<Integer> wList, List<Integer> vList, int c){
        if(wList == null || wList.size() == 0 || vList == null || vList.size() == 0 || c <= 0){
            return 0;
        }
        //采用记忆化搜索，有两个条件 index 和 c，因此采用 二维数组 的方式
        //第一维代表 背包的索引，第二维代表 剩余容量，这样可以记录下来 不同索引 不同容量 对应的最大价值
        List<List<Integer>> memoList = new ArrayList<>();
        //只需要保持两行就行
        for(int i = 0; i < 2; i++){
            memoList.add(new ArrayList<Integer>());
            for(int j = 0; j < c + 1; j++){
                memoList.get(i).add(-1);
            }
        }
        //动态规划 是自底向上的求解，先求最基本的问题，然后自底向上的求出 不同索引 不同容量 对应的最大值
        //最基本问题的求解是 索引为0 容量为 0...c 的求解
        for(int j = 0; j < c + 1; j++){
            int res = j >= wList.get(0) ? vList.get(0) : 0;
            memoList.get(0).set(j, res);
        }
        //然后求解 其他索引 容量为 0...c 对应的最大价值
        for(int i = 1; i < wList.size(); i++){
            for(int j = 0; j < c + 1; j++){
                //不放入的情况，最大价值为
                int res = memoList.get((i - 1) % 2).get(j);
                //放入的情况，最大价值为
                if(j >= wList.get(i)){//容量够用
                    int res2 = vList.get(i) + memoList.get((i - 1) % 2).get(j - wList.get(i));
                    res = Math.max(res, res2);//保留两种情况的最大值
                }
                memoList.get(i % 2).set(j, res);
            }
        }
        return memoList.get((wList.size() - 1) % 2).get(c);
    }

    //背包问题的优化2：
    //只保持一行
    public static int knapsack4(List<Integer> wList, List<Integer> vList, int c){
        if(wList == null || wList.size() == 0 || vList == null || vList.size() == 0 || c <= 0){
            return 0;
        }
        //采用记忆化搜索，有两个条件 index 和 c，因此采用 二维数组 的方式
        //第一维代表 背包的索引，第二维代表 剩余容量，这样可以记录下来 不同索引 不同容量 对应的最大价值
        List<Integer> memoList = new ArrayList<>();
        //只需要保持一行就行
        for(int j = 0; j < c + 1; j++){
            memoList.add(-1);
        }
        //动态规划 是自底向上的求解，先求最基本的问题，然后自底向上的求出 不同索引 不同容量 对应的最大值
        //最基本问题的求解是 索引为0 容量为 0...c 的求解
        for(int j = 0; j < c + 1; j++){
            int res = j >= wList.get(0) ? vList.get(0) : 0;
            memoList.set(j, res);
        }
        //然后求解 其他索引 容量为 0...c 对应的最大价值
        for(int i = 1; i < wList.size(); i++){
            for(int j = c; j >= wList.get(i); j--){
                int res = Math.max(memoList.get(j), vList.get(i) + memoList.get(j - wList.get(i)));
                memoList.set(j, res);
            }
        }
        return memoList.get(c);
    }
}
