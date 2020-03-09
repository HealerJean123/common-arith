package com.hlj.arith.demo00035_实现strStr;

import org.junit.Test;

/**
作者：HealerJean
题目：实现 strStr()
    给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     示例 1:
        输入: haystack = "hello", needle = "ll"
        输出: 2
    示例 2:
        输入: haystack = "aaaaa", needle = "bba"
        输出: -1
 解题思路：
*/
public class 实现strStr {


    @Test
    public void test(){
        System.out.println("".length());
        // String haystack = "hello", needle = "ll" ;
        // String haystack = "a", needle = "a" ;
        String haystack = "mississippi", needle = "issip" ;


        System.out.println(strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        //空字符串肯定匹配，且为0
        if (needle.length() == 0 ){
            return 0;
        }

        int i = 0 ;
        for (int j = 0 ; j < haystack.length() ; j ++){
            if (needle.charAt(i) == haystack.charAt(j)){
                if (i == needle.length()-1){
                    return j-needle.length()+1;
                }
                i++;
            }else {
                i = 0 ;
            }
        }
        return  -1 ;
    }

}
