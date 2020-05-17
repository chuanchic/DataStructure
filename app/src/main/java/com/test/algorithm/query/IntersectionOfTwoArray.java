package com.test.algorithm.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IntersectionOfTwoArray {

    /**
     * 给定两个数组nums，求两个数组的公共元素
     * 如 num1 = [1, 2, 2, 1]，nums2 = [2, 2]
     * 结果为 [2]
     */
    public static int[] intersection(int[] nums1, int[] nums2){
        HashSet<Integer> record = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            record.add(nums1[i]);
        }
        HashSet<Integer> result = new HashSet<>();
        for(int i = 0; i < nums2.length; i++){
            if(record.contains(nums2[i])){
                result.add(nums2[i]);
            }
        }
        int[] nums3 = new int[result.size()];
        int i = 0;
        for(Integer num : result){
            nums3[i] = num;
            i++;
        }
        return nums3;
    }

    /**
     * 给定两个数组，求两个数组的交集
     * 如 nums1 = [1, 2, 2, 1]，nums2 = [2, 2]
     * 结果为 [2, 2]
     */
    public static int[] intersection2(int[] nums1, int[] nums2){
        HashMap<Integer, Integer> record = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            Integer value = record.get(nums1[i]);
            if(value == null){
                record.put(nums1[i], 1);
            }else{
                record.put(nums1[i], value + 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            Integer value = record.get(nums2[i]);
            if(value != null && value > 0){
                //有交集
                result.add(nums2[i]);
                record.put(nums2[i], value - 1);
            }
        }
        int[] nums3 = new int[result.size()];
        int i = 0;
        for(Integer num : result){
            nums3[i] = num;
            i++;
        }
        return nums3;
    }

}
