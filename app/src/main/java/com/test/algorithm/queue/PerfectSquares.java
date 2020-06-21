package com.test.algorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PerfectSquares {

    /**
     * 给出一个正整数n，寻找最少的完全平方数，使他们的和为n
     * 完全平方数：1，4，9，16 ...
     * 12 = 4 + 4 + 4
     * 13 = 4 + 9
     *
     * 对问题建模：
     * 整个问题转化为一个图论问题，从n到0，每个数字表示一个节点，
     * 如果两个数字x到y相差一个完全平方数，则连接一条边，
     * 这样我们得到了一个无权图，原问题转化成，
     * 求这个无权图中从n到0的最短路径
     */
    public static int numSquares(int n){
        if(n <= 0){
            return -1;
        }
        LinkedList<NumStep> queue = new LinkedList<>();
        queue.add(new NumStep(n, 0));

        //已经在队列里的数字，就不需要再次入队了
        List<Boolean> visited = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            visited.add(false);
        }

        while(!queue.isEmpty()){
            NumStep numStep = queue.removeFirst();
            if(numStep.num == 0){
                return numStep.step;
            }
            for(int i = 1; numStep.num - i*i >= 0; i++){
                if(!visited.get(numStep.num - i*i)){
                    visited.set(numStep.num - i*i, true);
                    queue.add(new NumStep(numStep.num - i*i, numStep.step + 1));
                }
            }
        }
        return -1;
    }

    private static final class NumStep{
        private int num;
        private int step;

        public NumStep(int num, int step){
            this.num = num;
            this.step = step;
        }
    }
}
