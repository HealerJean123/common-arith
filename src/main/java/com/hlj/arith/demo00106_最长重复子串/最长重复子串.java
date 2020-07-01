package com.hlj.arith.demo00106_最长重复子串;

import org.junit.Test;


/**
作者：HealerJean
题目：
 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 “”。）
     示例 1：
         输入：“banana”
         输出：“ana”
     示例 2：
         输入：“abcd”
         输出：""
 解题思路：
*/
public class 最长重复子串 {


    @Test
    public void test(){
        System.out.println(longestDupSubstring("banana"));
    }

    public String longestDupSubstring(String str) {
        int[][] dp = new int[str.length()][str.length()];

        String res = "";
        int count = 0 ;
        //第一行肯定为0，所以j从2开始，而且肯定是在左上方
        for (int j = 2; j < str.length(); j++) {
            for (int i = j -1; i >=1; i--) {
                if (str.charAt(i) == str.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1 ;
                    if (dp[i][j] > count){
                        count = dp[i][j];
                        res  = str.substring(i - count + 1, i + 1);
                    }
                }
            }
        }
        return res;
    }

}
