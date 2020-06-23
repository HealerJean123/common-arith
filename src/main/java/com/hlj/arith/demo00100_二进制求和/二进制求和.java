package com.hlj.arith.demo00100_二进制求和;

import org.junit.Test;


/**
作者：HealerJean
题目：
 给你两个二进制字符串，返回它们的和（用二进制表示）。
 输入为 非空 字符串且只包含数字 1 和 0。
     示例 1:
         输入: a = "11", b = "1"
         输出: "100"
     示例 2:
         输入: a = "1010", b = "1011"
         输出: "10101"
解题思路：充分利用 取余 和 除法
*/
public class 二进制求和 {


    @Test
    public void test(){
        System.out.println(addBinary("1010", "1011"));
    }

    /**
     * 我的的初始代码
     */
    public String addBinary2(String a, String b) {
        int[] res = new int[a.length()+ b.length()];
        int aIdx = a.length()-1 ;
        int bIdx = b.length()-1;
        int cIdx = 0;
        int temp = 0;
        while (aIdx >= 0 && bIdx >= 0) {
            Integer aVal = Integer.valueOf(String.valueOf( a.charAt(aIdx)));
            Integer bVal = Integer.valueOf(String.valueOf( b.charAt(bIdx)));
            Integer temVal = aVal + bVal + temp;

            int pop = temVal % 2;
            res[cIdx] = pop ;

            temp = temVal / 2;
            cIdx++;
            aIdx--;
            bIdx--;
        }

        while (aIdx >= 0){
            Integer aVal = Integer.valueOf(String.valueOf( a.charAt(aIdx)));
            Integer temVal = aVal  + temp;

            int pop = temVal % 2;
            res[cIdx] = pop ;

            temp = temVal / 2;
            cIdx++;
            aIdx--;
        }


        while (bIdx >= 0){
            Integer bVal = Integer.valueOf(String.valueOf( b.charAt(bIdx)));
            Integer temVal = bVal  + temp;

            int pop = temVal % 2;
            res[cIdx] = pop ;

            temp = temVal / 2;
            cIdx++;
            bIdx--;
        }

        if (temp != 0 ){
            int pop = temp % 2;
            res[cIdx] = pop ;
            cIdx ++ ;
            temp = temp/2 ;
        }

        StringBuilder stringBuilder = new StringBuilder();
        // cIdx 多走了1位
        for (int i = cIdx-1 ; i >=0 ; i--){
            stringBuilder.append(res[i]);
        }
        return stringBuilder.toString() ;
    }


    /**
     * 优化后的
     */
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int aIdx = a.length()-1 ;
        int bIdx = b.length()-1;
        int temp = 0;
        // temp != 0 ，防止最后temp还有值
        while (aIdx >= 0 || bIdx >= 0 || temp != 0) {
            Integer aVal = aIdx >= 0 ? Integer.valueOf(String.valueOf(a.charAt(aIdx--))) : 0;
            Integer bVal = bIdx >= 0 ? Integer.valueOf(String.valueOf(b.charAt(bIdx--))) : 0;
            Integer temVal = aVal + bVal + temp;
            int pop = temVal % 2;
            stringBuilder.append(pop);
            //向上前进几位
            temp = temVal / 2;
        }
        return stringBuilder.reverse().toString() ;
    }

}
