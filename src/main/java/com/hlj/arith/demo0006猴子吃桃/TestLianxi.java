package com.hlj.arith.demo0006猴子吃桃;

import org.junit.Test;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/2/27  下午5:25.
 目：猴子吃桃
 某天，一只猴子摘了一堆桃子，它每天吃掉其中的一半再多吃一个，第二天吃剩余的一半再多吃一个······
 到了第N天只剩下一个桃子，问在第一天时摘了多少桃子？
 解题思路：

 1 ,
 4 =  (1 + 1) * 2  ;
 7  = (4 + 1) * 1 ;



 */
public class TestLianxi {

    @Test
    public void start(){
        System.out.println(f(5));
    }

    public int f(int n){
        if(n==1){
            return 1;
        }else {
            return (f(n-1)+1)*2;
        }
    }

}
