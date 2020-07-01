package com.test.algorithm.greedy;

import java.util.Arrays;

public class AssignCookies {

    /**
     * 假设你想给小朋友们饼干。每个小朋友最多只能给一块饼干，
     * 每个小朋友都有一个贪心指数，称为g(i)，表示这个小朋友需要饼干大小的最小值，
     * 每个饼干都有一个大小值s(j)，如果s(j)>=g(i)，将饼干j分给小朋友i后，小朋友就会很开心，
     * 给定数组s和g，问如何分配饼干，能够让最多的小朋友开心
     *
     * 如 g = [1, 2, 3], s = [1, 1]，结果为 1
     * 如 g = [1, 2], s = [1, 2, 3]，结果为 2
     *
     * 思路：每次分配饼干都是用 最大 的饼干试着分给 最贪心 的孩子，如果不能使这个孩子开心，
     *      那么其他饼干更不能使这个孩子开心，这个孩子就可以pass掉，继续分给下一个 最贪心 的孩子
     */
    public static int findContentChildren(int[] g, int[] s){
        if(g == null || g.length == 0 || s == null || s.length == 0){
            return 0;
        }
        //对数组进行升序排序
        Arrays.sort(g);
        Arrays.sort(s);
        //反转数组为降序
        reverse(g);
        reverse(s);
        int si = 0;//索引从0开始，代表当前最大的饼干
        int gi = 0;//索引从0开始，代表当前最贪心的孩子
        int res = 0;//记录次数
        while (gi < g.length && si < s.length){
            if(s[si] >= g[gi]){//最大的饼干 能满足 最贪心的孩子
                res++;
                si++;
                gi++;
            }else{//不能满足这个孩子，pass调这个孩子，继续分给下一个最贪心的孩子
                gi++;
            }
        }
        return res;
    }

    //实现数组元素的翻转
    private static void reverse(int[] arr){
        int length = arr.length;
        for(int i = 0; i < length / 2; i++){
            int temp = arr[length - i - 1];
            arr[length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }
}
