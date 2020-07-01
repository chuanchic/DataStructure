package com.test.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NonOverlappingIntervals {

    /**
     * 给定一组区间，问最少删除多少个区间，可以让这些区间之间互相不重叠
     * 给定区间的起始点永远小于终止点
     *
     * 如 [[1, 2], [2, 3], [3, 4], [1, 3]]，返回 1，删掉 [1, 3]
     * 如 [[1, 2], [1, 2], [1, 2]]，返回 2，删掉 [1, 2], [1, 2]
     *
     * 暴力解法：找出所有子区间的组合，之后判断它不重叠，O((2^n) * n)
     *
     * 思路1：动态规划
     * 最少删除多少区间 转化为 最多保留多少区间，然后对所有区间进行升序排序，方便判断不重叠
     * 这样就转为了 动态规划 问题，求最长上升子序列
     *
     * 思路2：贪心算法
     * 按照区间的结尾升序排序，这样每次选择的就是结尾最早的，且和前一个区间不重叠的区间
     *
     * 题外：有些 动态规划 问题可以使用 贪心算法 来解决，需要满足 贪心选择性质
     *
     */
    //动态规划 方式解决
    public static int eraseOverlapIntervals(Interval[] intervals){
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        //升序排序
        Arrays.sort(intervals, new MyComparator());
        //memo[i] 表示intervals[0...i]的区间能构成的最长不重叠区间子序列
        List<Integer> memoList = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            memoList.add(1);// 1 代表初值
        }
        for(int i = 1; i < intervals.length; i++){
            //求 memo[i]
            for(int j = 0; j < i; j++){
                if(intervals[i].start >= intervals[j].end){
                    memoList.set(i, Math.max(memoList.get(i), 1 + memoList.get(j)));
                }
            }
        }
        //求出最长上升子序列的长度
        int res = 0;
        for(int i = 0; i < memoList.size(); i++){
            res = Math.max(res, memoList.get(i));
        }
        //返回需要删除的区间个数
        return intervals.length - res;
    }

    //贪心算法 方式解决
    public static int eraseOverlapIntervals2(Interval[] intervals){
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        //升序排序，将 end 为最小的区间排在前面
        Arrays.sort(intervals, new MyComparator2());
        int res = 1;
        int pre = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start >= intervals[pre].end){
                res++;
                pre = i;
            }
        }
        //返回需要删除的区间个数
        return intervals.length - res;
    }

    //用于动态规划
    private static final class MyComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            if(o1.start != o2.start){
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        }
    }

    //用于贪心算法
    private static final class MyComparator2 implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            if(o1.end != o2.end){
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        }
    }

    private static final class Interval{
        private int start;
        private int end;

        public Interval(){
            this(0, 0);
        }

        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
