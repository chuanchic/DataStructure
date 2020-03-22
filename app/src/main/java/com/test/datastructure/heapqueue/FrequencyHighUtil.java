package com.test.datastructure.heapqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 给定数组，取出 频次最高的 k个元素
 * 通过我们自己写的 优先队列 来实现
 */
public class FrequencyHighUtil {

    private class Freq implements Comparable<Freq>{
        public int e;//元素
        public int freq;//频次

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            if(this.freq < o.freq){
                return 1;//应该返回-1，这儿返回1，可以让 最大优先队列 变成 最小优先队列
            }else if(this.freq > o.freq){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int num : nums){
            if(treeMap.containsKey(num)){
                treeMap.put(num, treeMap.get(num) + 1);
            }else{
                treeMap.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();//最小优先队列
        for(int key : treeMap.keySet()){
            if(pq.getSize() < k){
                pq.enqueue(new Freq(key, treeMap.get(key)));
            }else if(treeMap.get(key) > pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Freq(key, treeMap.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()){
            res.add(pq.dequeue().e);
        }
        return res;

    }

}
