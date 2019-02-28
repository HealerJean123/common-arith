package com.hlj.arith.domo0007舍罕王赏麦;


/**
作者：HealerJean
题目：舍罕王赏麦问题，是一个著名的数级求和问题
 舍罕王为了奖励宰相西萨-班-达依尔，达依尔灵机一动，说：陛下，请你按照棋盘上的格子，赏赐我，
 第一个格子赏我一个麦子，第二个格子赏我两个麦子，第三个格子赏我四个麦子.......以此类推，
 以后每个格子比前一个麦子增加一倍。只要把64个格子上的麦子赏赐给我就行了。

解题思路：

 我们已知第一个是 1 所以可以正推
   sum=1+2^1+2^3+2^4+......+2^64

    第一个是 1
    第二个是 1*2 = 2
    第三个是 2*2 = 4
    第四个是 4*2 = 8
    第五个是 8*2 = 16

 */
public class TestMain {


    public static void main(String[] args) {
        double sum=1;//仔细 观察下面会发现第一个没有加进去
        double temp=1;
        for(int i=1;i<3;i++)
        {
            temp=temp*2;
            sum=sum+temp;
        }
        System.out.println(sum);       //迭代法


        System.out.println(f(64));  //递归算法
    }
    public static double f(double n) {

        if(n==1) {
            return 1;
        } else {
            return f(n-1)*2+n;
        }

    }





}
