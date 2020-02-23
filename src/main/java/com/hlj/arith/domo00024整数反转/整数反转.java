package com.hlj.arith.domo00024整数反转;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName 整数反转
 * @date 2020-02-24  00:33.
 * @Description
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
 */
public class 整数反转 {

    @Test
    public void test(){
        System.out.println(Integer.MAX_VALUE / 10 + 8);
        System.out.println(reverse(521));
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }


}
