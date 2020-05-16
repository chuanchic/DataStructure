package com.test.algorithm.array;

public class BinarySearch {

    //二分查找法
    public int binarySearch(int[] arr, int target){
        int l = 0, r = arr.length - 1;
        while(l <= r){
            //int mid = (l + r) / 2;// l+r 可能越界
            int mid = l + (r - l) / 2;
            if(target == arr[mid]){
                return mid;
            }else if(target > arr[mid]){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return -1;
    }

}
