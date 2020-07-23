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
        int[][] matrix = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(matrix));
    }


    public int minPathSum(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < grid[0].length ; j++ ){
            dp[0][j] = grid[0][j] + dp[0][j-1];
        }

        for (int i = 1 ; i< grid.length; i++){
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        for (int i = 1 ; i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] =  grid[i][j] +Math.min(dp[i-1][j], dp[i][j-1]) ;
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }


}
