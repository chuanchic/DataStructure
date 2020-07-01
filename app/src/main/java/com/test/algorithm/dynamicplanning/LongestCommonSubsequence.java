package com.test.algorithm.dynamicplanning;

public class LongestCommonSubsequence {

    /**
     * 给出两个字符串S1和S2，求这两个字符串的最长公共子序列的长度
     * S1[0...m] 和 S2[0...n] 的最长公共子序列
     *
     * 思路：分两种情况
     * 情况1：S1[m] == S2[n]
     * LCS(m, n) = 1 + LCS(m - 1, n - 1)
     * 情况2：S1[m] != S2[n]
     * LCS(m, n) = max(LCS(m - 1, n), LCS(m, n - 1))
     *
     */

}
