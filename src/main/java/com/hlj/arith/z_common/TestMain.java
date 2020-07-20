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
    public void test() {
       String s1 = "aa", s2 = "ab", s3 = "abaa" ;
        System.out.println(isInterleave(s1, s2, s3));
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        int m = s1.length();
        int n = s2.length();
        if (m + n!= s3.length()){
            return false;
        }

        if (s1.equals("")){
            return s2.equals(s3);
        }
        if (s2.equals("")){
            return s1.equals(s3);
        }


        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 1 ;

        for (int i = 1 ; i <= m ; i++ ){
            if (s1.charAt(i-1) == s3.charAt(i-1)){
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int j = 1 ; j <= n ; j++){
            if (s2.charAt(j-1) == s3.charAt(j-1)){
                dp[0][j] = dp[0][j-1];
            }
        }


        for (int i  = 1 ; i <= m ; i++){
            for (int j = 1 ; j <= n ; j++){
                int t = i + j ;
                if (s1.charAt(i-1) == s3.charAt(t-1)){
                    dp[i][j] = dp[i-1][j] | dp[i][j];
                }

                if (s2.charAt(j-1) == s3.charAt(t-1)){
                    dp[i][j] = dp[i][j-1] | dp[i][j];
                }
            }
        }


        return dp[m][n] == 1 ? true :false;
    }


}
