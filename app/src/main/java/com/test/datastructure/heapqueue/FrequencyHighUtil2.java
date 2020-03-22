package com.test.datastructure.heapqueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.PriorityQueue;

/**
 * 给定数组，取出 频次最高的 k个元素
 * 通过java提供的 优先队列 来实现
 * 不同点子在于可以自定义 Comparator，这样 Freq 可以不实现 Comparable 接口了
 */
public class FrequencyHighUtil2 {

    private class Freq{
        public int e;//元素
        public int freq;//频次

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }
    }

    //自定义比较器
    private class FreqComparator implements Comparator<Freq>{
        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.freq - o2.freq;
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

        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreqComparator());//最小优先队列
        for(int key : treeMap.keySet()){
            if(pq.size() < k){
                pq.add(new Freq(key, treeMap.get(key)));
            }else if(treeMap.get(key) > pq.peek().freq){
                pq.remove();
                pq.add(new Freq(key, treeMap.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()){
            res.add(pq.remove().e);
        }
        return res;

    }

}
