package com.test.algorithm.array;

public class SortColors {

    /**
     * 给定一个有n个元素的数组，数组中元素的取值只有0，1，2三种可能，
     * 为这个数组排序
     *
     * 时间复杂度O(n)
     * 空间复杂度O(k)，k为元素的取值范围，k为常数，= O(1)
     */
    public static void sortColors(int[] nums){
        int[] counts = new int[3];//存放0,1,2三个元素的频率
        for(int i = 0; i < nums.length; i++){
            int ele = nums[i];
            if(ele >= 0 && ele < 3){
                counts[ele] ++;
            }
        }

        int index = 0;
        for(int i = 0; i < counts[0]; i++){
            nums[index++] = 0;
        }
        for(int i = 0; i < counts[1]; i++){
            nums[index++] = 1;
        }
        for(int i = 0; i < counts[2]; i++){
            nums[index++] = 2;
        }
    }

    /**
     * 三路快排
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public static void quickSortColors(int[] nums){
        int zero = -1;
        int two = nums.length;
        for(int i = 0; i < two; ){
            if(nums[i] == 1){
                i++;
            }else if(nums[i] == 2){
                two--;
                int temp = nums[i];
                nums[i] = nums[two];
                nums[two] = temp;
            }else{
                zero++;
                int temp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = temp;
                i++;
            }
        }
    }

}
