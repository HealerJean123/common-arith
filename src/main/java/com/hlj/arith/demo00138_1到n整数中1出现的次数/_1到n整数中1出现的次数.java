package com.hlj.arith.demo00138_1到n整数中1出现的次数;

import org.junit.Test;

/**
 * 作者：HealerJean
 * 题目：
 * 解题思路：
 */
public class _1到n整数中1出现的次数 {


    @Test
    public void test(){
        System.out.println(countDigitOne(3412));
    }

    public int countDigitOne(int n) {
        //digit = 1 表示个位
        int digit = 1, res = 0;
        //high：高位（1-10）初始进来是0
        int high = n / 10;
        //cur：初始进来 就是个位数
        int cur = n % 10;
        //low：低位为0
        int low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }

            //开始走下一个
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

}
