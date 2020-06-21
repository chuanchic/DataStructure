package com.test.algorithm.queue;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopFrequentElement {

    /**
     *  给定一个非空数组，返回前k个出现频率最高的元素
     *  如给定[1, 1, 1, 2, 2, 3]，k = 2
     *  返回 [1, 2]
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Queue<Pair> topKFrequent(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0){
            return null;
        }
        //统计每个元素出现的频率
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            Integer value = map.get(nums[i]);
            if(value == null){
                map.put(nums[i], 1);
            }else{
                map.put(nums[i], value + 1);
            }
        }
        if(k > map.size()){
            return null;
        }
        //扫描map，维护当前出现频率最高的k个元素
        Queue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.frequent - p2.frequent;
            }
        });
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer ele = (Integer) entry.getKey();
            Integer frequent = (Integer) entry.getValue();
            if(queue.size() == k){
                if(frequent > queue.peek().frequent){
                    queue.poll();//最小频率对应的元素出队
                    queue.add(new Pair(frequent, ele));//新的元素入队
                }
            }else{
                queue.add(new Pair(frequent, ele));//新的元素入队
            }
        }
        return queue;
    }

    private static final class Pair{
        private int frequent;//频率
        private int ele;//频率对应的元素

        public Pair(int frequent, int ele){
            this.frequent = frequent;
            this.ele = ele;
        }
    }

}
