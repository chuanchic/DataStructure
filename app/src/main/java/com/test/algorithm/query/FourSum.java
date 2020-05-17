package com.test.algorithm.query;

import java.util.HashMap;

public class FourSum {

    /**
     * 给出四个整型数组 A,B,C,D，寻找有多少i,j,k,l的组合，
     * 使得A[i] + B[j] + C[k] + D[l] == 0，
     * 其中A,B,C,D中均含有相同的元素个数N，且 0 <= N <= 500
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(n^2)
     *
     */
    public static int fourSum(int[] A, int[] B, int[] C, int[] D){
        HashMap<Integer, Integer> record = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                Integer value = record.get(A[i] + B[j]);
                if(value == null){
                    record.put(A[i] + B[j], 1);
                }else{
                    record.put(A[i] + B[j], value + 1);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                //A[i] + B[j] + C[k] + D[l] == 0
                if(record.containsKey(0 - C[i] - D[j])){
                    res += record.get(0 - C[i] - D[j]);
                }
            }
        }
        return res;
    }

}
