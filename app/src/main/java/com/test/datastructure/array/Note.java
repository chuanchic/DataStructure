package com.test.datastructure.array;

/**
 * 简单的时间复杂度分析
 * O(1)  O(n)  O(lgn)  O(nlogn)  O(n^2)
 * 大O描述的是 算法的运行时间 和 输入数据 之间的关系
 */
public class Note {

    /**
     * 时间复杂度是 O(n)
     * n 是 nums 中的元素个数，算法和 n 呈线性关系
     */
    public static int sum(int[] nums){
        int sums = 0;
        for(int num : nums){
            sums += num;
        }
        return sums;
    }

    /**
     * 我们封装的数组 Array
     *
     * 添加操作
     * addLast(e)     O(1)
     * addFirst(e)    O(n)
     * add(index, e)  O(n/2) = O(n)
     * 结论：添加操作 的时间复杂度是 O(n) 级别的
     *
     * 删除操作 同上
     *
     * 修改操作
     * set(index, e)   O(1)  已知索引
     * set(e)          O(n)  未知索引
     *
     * 查找操作
     * get(index)      O(1)  已知索引
     * contain(e)      O(n)  未知索引
     * find(e)         O(n)  未知索引
     *
     * addLast + resize 操作
     * 数组 容量为8，9 次 addLast 操作，会触发 resize 操作，一共触发 17 次操作
     * 平均每次 addLast 触发两次操作，这样均摊计算，时间复杂度是 O(1) 级别的(均摊复杂度)
     *
     * removeLast + resize 操作 同上，也是 O(1) 级别的
     *
     * addLast + removeLast 同时出现的时候
     * 即 addLast 导致 resize 操作，这时候再 removeLast，又会导致 resize ...
     * 这就是 复杂度震荡，即当 size = capacity / 2 时，立即将 capacity 减半
     * 解决方案：当 size = capacity / 4 时，才将 capacity 减半
     *
     */
}
