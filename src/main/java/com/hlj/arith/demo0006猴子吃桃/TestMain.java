package com.hlj.arith.demo0006猴子吃桃;


import java.util.Scanner;

/**
作者：HealerJean
题目：猴子吃桃
     某天，一只猴子摘了一堆桃子，它每天吃掉其中的一半再多吃一个，第二天吃剩余的一半再多吃一个······
     到了第N天只剩下一个桃子，问在第一天时摘了多少桃子？
解题思路：
    通过上面的我们可以知道，这道题是可以逆推的

    最后一天 剩下1
     上一天 eat(h)
     下一天 eat(h-1)
 二者关系,所以很明显可以使用递归
 eat(h)  = (eat(h-1)+1)*2

 */
public class TestMain {

    public static void main(String[] args) {
        System.out.println("请输入天数");
        int h = new Scanner(System.in).nextInt();
        System.out.println(eat(h));
    }

    /**

     */
    public static int eat(int h){
        if(h==1){
            return 1;
        }else {
            return (eat(h-1)+1)*2 ; //上一天等于下一天
        }
    }

}
