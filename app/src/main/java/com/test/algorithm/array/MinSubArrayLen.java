package com.test.algorithm.array;

public class MinSubArrayLen {

    /**
     * 给定一个整型数组和一个数字 s，找到数组中最短的一个连续子数组，
     * 使得连续子数组的数字和 sum >= s，返回这个最短的连续子数组的返回值
     * 如给定数组[2，3，1，2，4，3]，s = 7
     * 答案为 [4，3]，返回 2
     *
     * 通过 "滑动窗口" 解法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public static int minSubArrayLen(int[] nums, int s){
        int l = 0;
        int r = -1;
        int sum = 0;
        int res = nums.length + 1;
        while(l < nums.length){//遍历每一个l
            if(sum < s && r + 1 < nums.length){
                r++;
                sum += nums[r];
            }else{
                //l向右移动一位
                sum -= nums[l];
                l++;
            }
            if(sum >= s){
                res = Math.min(res, r - l + 1);
            }
        }
        if(res == nums.length + 1){
            return 0;
        }else{
            return res;
        }
    }

    /**
     * 在一个字符串中寻找没有重复字母的最长子串
     * 如 abcabcbb，结果为 abc
     * 如 bbbbbb，结果为 b
     * 如 pwwkew，结果为 wke
     */
    public static int maxLenStr(String str){
        char[] chars = str.toCharArray();
        int[] freq = new int[256];
        int l = 0;
        int r = -1;
        int res = 0;
        while (l < str.length()){
            //chars[r + 1] 不代表一个字符，而是这个字符对应的ASCII码
            if(r + 1 < chars.length && freq[chars[r + 1]] == 0){
                //没有重复字母，r向右移动
                r++;
                freq[chars[r]]++;
            }else{
                //已经有重复字母，l向右移动
                freq[chars[l]]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
