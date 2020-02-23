package com.hlj.arith.demo00022_最长回文子串;

import org.junit.Test;

/**
作者：

题目：最长回文子串

解题思路：

*/
public class 最长回文子串 {

    @Test
    public void test(){
        System.out.println(longestPalindrome("banana"));
    }


    public String longestPalindrome(String s) {
        int len = s.length();
        //只有一个字符的时候，肯定是回文
        if (len < 2) {
            return s;
        }

        // 初始化数组dp，全部设置为true
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int start = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

}
