package com.hlj.arith.demo00038_两数相除;

import org.junit.Test;

/**
作者：HealerJean
题目：两数相除
    给定两个整数，被除数 dividend 和除数 divisor。将两数相除，
    要求不使用乘法、除法和 mod 运算符。返回被除数 dividend 除以除数 divisor 得到的商。
    示例 1:
        输入: dividend = 10, divisor = 3
        输出: 3
    示例 2:
        输入: dividend = 7, divisor = -3
        输出: -2
 解题思路：
 */
public class 两数相除 {


    @Test
    public void test(){
        System.out.println(divide(3, -10));
    }


    public int divide(int a, int b) {

        //b的边界
        if (b == Integer.MIN_VALUE && a == -1) {
            return Integer.MAX_VALUE;
        }
        if (b == Integer.MIN_VALUE && a == Integer.MIN_VALUE) {
            return 1;
        }


        //a 如果为最小值，因为结果肯定为整数且大于0，所以当他为边界值的时候，肯定结果不存在，返回0
        if (a == Integer.MIN_VALUE) {
            return 0;
        }

        int fix = 0;
        if (b == Integer.MIN_VALUE) {
            if (a > 0) {
                b = a + b;
            } else {
                b = b- a;
            }
            fix = -1;
        }

        //false 表示结果为正数，true表示结果为负数
        boolean flag = false;
        if ((a > 0 && b > 0) || (a < 0 && b < 0)){
            flag = true;
        }
        //将 a 和 b 都变成正数
        if (b < 0) {
            b = -b;
        }
        if (a < 0) {
            a = -a;
        }


        int[] nums = new int[5];
        int sum = 0  ;
        while (b >= a) {
            int j = 1 ;
            while ((a << j) < b) {
                j ++ ;
            }
            j = j - 1 ;
            b = b - (a << j);

            nums[sum] = j  ;
            sum ++ ;
        }

        //数组的个数
        int result = 0 ;
        for (int i = 0 ; i < sum -1 ; i++){
            result = result + 1 << nums[i];
        }
        return result;

    }



    public int divide2(int a, int b) {
        // a = -2^31, b = -1, a/b = 2^31
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        // a = -2^31, b = -2^31, a/b = 1
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) return 1;
        // a != -2^31, b = -2^31, a/b = 0
        if (b == Integer.MIN_VALUE) return 0;
        // a = -2^31, b != -2^31:  a <= a + abs(b), fix = b > 0 ? -1 : 1
        int fix = 0;
        if (a == Integer.MIN_VALUE) {
            if (b > 0) {
                a += b;
                fix = -1;
            } else {
                a -= b;
                fix = 1;
            }
        }
        boolean neg = false;
        if (a < 0) {
            a = -a;
            neg = !neg;
        }
        if (b < 0) {
            b = -b;
            neg = !neg;
        }
        int result = 0;
        while (a >= b) {
            int x = b;
            int r = 1;
            while (x <= (a>>1)) {
                x <<= 1;
                r <<= 1;
            }
            a -= x;
            result += r;
        }
        return (neg ? -result : result) + fix;
    }


}
