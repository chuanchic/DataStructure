package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.List;

public class MoveZeros {

    /**
     * 给定一个数组nums，写一个函数，将数组中所有的0挪到数组的末尾，
     * 而维持其他所有非0元素的相对位置
     * 例如 nums = [0, 1, 0, 3, 12]，
     * 结果应该为   [1, 3, 12, 0, 0]
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)  因为开辟了额外的内存空间 nonZeroElements
     */
    public void moveZeros(int[] nums){
        List<Integer> nonZeroElements = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nonZeroElements.add(nums[i]);
            }
        }
        for(int i = 0; i < nonZeroElements.size(); i++){
            nums[i] = nonZeroElements.get(i);
        }
        for(int i = nonZeroElements.size(); i < nums.length; i++){
            nums[i] = 0;
        }
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public void moveZeros2(int[] nums){
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[k] = nums[i];
                k++;
            }
        }
        for(int i = k; i < nums.length; i++){
            nums[i] = 0;
        }
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public void moveZeros3(int[] nums){
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                if(i != k){//两个相等的不用交换，直接k++就行了
                    int temp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = temp;
                }
                k++;
            }
        }
    }

}
