package com.hlj.arith.z_common;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
public class TestMain {

    @Test
    public void test(){
        System.out.println(strStr("a", "a"));
    }

    public int strStr(String txt, String pattern) {
        if (pattern.length() == 0) {
            return 0;
        }
        if (txt.length() == 0) {
            return -1;
        }


        int  i = 0 ;
        int j = 0 ;
        int index = 0 ;
        while (j < pattern.length() && index <= txt.length() - pattern.length()) {
            if (txt.charAt(i) == pattern.charAt(j)) {
                i++;

                j++;
                if (j == pattern.length()) {
                    return i - pattern.length();
                }
            } else {
                index++;
                j = 0;
                i = index;
            }
        }
        return -1 ;
    }

}
