package com.hlj.arith.demo00075_零钱兑换;


import org.junit.Test;

/**
作者：HealerJean
题目：零钱兑换
     给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     示例 1:
         输入: coins = [1, 2, 5], amount = 11
         输出: 3
         解释: 11 = 5 + 5 + 1
     示例 2:
         输入: coins = [2], amount = 3
         输出: -1
解题思路：
 假设你是个土豪，你有1，5，10，20，50，100的钞票，你要凑出666买瓶水喝，依据生活经验，我们一般采取这样的策略：能用100就用100的，否则就用50的，依此类推，在这种策略下，666=100*6 + 50 1 + 10 1 + 51 + 11, 一共用了10张钞票。

 这种策略就称为 贪心策略 ：贪心策略是在当前情况下做出最好的选择，根据需要凑出的金额来进行贪心，但是，如果我们换一组钞票面值，比如 1， 5， 11，我们要凑出15的时候， 贪心策略就会出错：

 15 = 11 * 1 + 1 * 4 (贪心策略）
 15 = 5 * 3（正确策略）
 贪心策略哪里出错了？
 鼠目寸光

 重新分析刚刚的例子。w=15时，我们如果取11，接下来就面对w=4的情况；如果取5，则接下来面对w=10的情况。我们发现这些问题都有相同的形式：“给定w，凑出w所用的最少钞票是多少张？” 接下来，我们用f(n)来表示“凑出n所需的最少钞票数量”。　　
 那么，如果我们取了11，最后的代价（用掉的钞票总数）是多少呢？

 明显 ，它的意义是：利用11来凑出15，付出的代价等于f(4)加上自己这一张钞票。现在我们暂时不管f(4)怎么求出来。
 依次类推，马上可以知道：如果我们用5来凑出15，cost就是f(10) + 1 = 2 + 1 = 3 。　
 　那么，现在w=15的时候，我们该取那种钞票呢？当然是各种方案中，cost值最低的那一个
 - 取11：　cost=  f(4)  + 1 = 4 + 1 = 5
 - 取5： 　cost = f(10) + 1 = 2 + 1 = 3
 - 取1： 　cost = f(14) + 1 = 4 + 1 = 5
 显而易见，cost值最低的是取5的方案。我们通过上面三个式子，做出了正确的决策!
 这给了我们一个至关重要的启示—— 只与 相关；更确切地说： f(n) 只与 f(n-1),f(n-5),f(n-11) 相关；更确切地说：
 f(n)=min{f(n-1),f(n-5),f(n-11)}+1
*/
public class 零钱兑换 {

    @Test
    public void test(){
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }


    public int coinChange(int[] coins, int amount) {

        //凑出多少钱需要多少 ，为了好看 讲amout设置为数组的边界索引位
        int [] dp = new int[amount + 1];
        //从1块钱开始算，一直到凑出amount为止
        for(int i = 1; i <= amount; i++){
            int cost = Integer.MAX_VALUE;
            //遍历 现在的纸币
            for(int j = 0; j < coins.length; j++){
                // 1、要凑的钱，必须大于等于目前的钱 才可能存在n
                // 2、如果 i > coins[j] 那么 fox[i - coins[j]] 可能不存在，所以也要加入判断
                if(i  >= coins[j]  && dp[i - coins[j]] != Integer.MAX_VALUE){
                     cost = Math.min(cost, dp[i - coins[j]] + 1);
                }
            }
            //当上面不进入 if的时候，这里的fox[i] 可能为 Integer.MAX_VALUE
            dp[i] = cost;
        }
        return  dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


}
