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
        String[] strs = {"flower","flow","flight"} ;
        System.out.println(longestCommonPrefix(strs));
    }


    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }


        String pre = strs[0];
        for (int i = 1 ; i < strs.length ; i ++){
            String str = strs[i];
            int j = 0 ;
            for ( ; j  < str.length() && j < pre.length(); j ++){
                if (str.charAt(j) != pre.charAt(j) ){
                    break;
                }
            }
            pre = pre.substring(0, j);
        }
        return pre;
    }

}
