package com.hlj.arith.domo0007舍罕王赏麦;

import org.junit.Test;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/2/27  下午5:25.
 题目：舍罕王赏麦问题，是一个著名的数级求和问题
 舍罕王为了奖励宰相西萨-班-达依尔，达依尔灵机一动，说：陛下，请你按照棋盘上的格子，赏赐我，
 第一个格子赏我一个麦子，第二个格子赏我两个麦子，第三个格子赏我四个麦子.......以此类推，
 以后每个格子比前一个麦子增加一倍。只要把64个格子上的麦子赏赐给我就行了。

 1 = 1
 2  =1 * 2 = 2
 3 = 2 * 2 = 4
 4 = 4 * 2 = 8
 5 = 8 * 2 = 16

 */
public class TestLianxi {

    @Test
    public void start(){
        System.out.println(fx(64));
    }

    public double fx(double n ){
        if(n==1){
            return  1 ;
        }else {
            return fx(n-1)*2  ;
        }
    }



}
