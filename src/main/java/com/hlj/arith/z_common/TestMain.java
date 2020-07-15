package com.hlj.arith.z_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {

    @Test
    public void test(){
        System.out.println(numTrees(3));
    }
    public int numTrees(int n) {

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2 ; i <= n ; i ++){
            for (int j = 1 ; j <=i ; j++){
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n] ;
    }



}
