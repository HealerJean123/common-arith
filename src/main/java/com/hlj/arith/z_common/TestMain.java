package com.hlj.arith.z_common;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
public class TestMain {

    @Test
    public void test(){
        // int[] prices = {3,3,5,0,0,3,1,4} ;
        int[] prices = {1,2,3,4,5} ;

        System.out.println(maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }

        //i 表示某一天(i = 0 是第一天)， j表示自己看吧
        int[][] dp = new int[prices.length][5] ;

        //有一个未知的数字，我们需要将它设置
        for (int i = 0 ; i < dp.length ; i++){
            dp[i][3] = Integer.MIN_VALUE;
        }
        dp[0][1] = -prices[0];
        for (int i = 1 ; i < prices.length ; i++){
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], prices[i] + dp[i-1][1]);

            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] -prices[i] );
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }
        return  Math.max(dp[prices.length-1][2], dp[prices.length-1][4]);
    }


}
