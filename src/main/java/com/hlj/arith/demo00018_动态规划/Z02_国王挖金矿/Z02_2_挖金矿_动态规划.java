package com.hlj.arith.demo00018_动态规划.Z02_国王挖金矿;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName Z02_1_挖金矿_递归
 * @date 2020/1/19  16:04.
 * @Description 当n = 1 ;  w < P[0];        F(n, w) = 0 ；
 * 当n = 1 ;  w >= P[0];       F(n. w) = G[0] ;
 * <p>
 * //通过上面推导出来的 n-1为当前的金矿
 * 当n > 1 ;  w <  P[n - 1];  F(n, w) = F(n - 1, w) ；
 * 当n > 1 ;  w >= P[n - 1];  F(n, w) = max(F(n-1,w), F(n-1,w-p[n-1])+g[n-1]) ;
 */
public class Z02_2_挖金矿_动态规划 {


    @Test
    public void method() {
        int g[] = new int[]{400, 500, 200, 300, 350};//黄金量
        int p[] = new int[]{5, 5, 3, 4, 3};//人数
        int maxGold = getMostGold(5, 10, g, p);
        System.out.println(maxGold);
    }

    /**
     * @param n 第几个金矿
     * @param w 总共有几个人
     * @param g 数组，存放每个金矿的黄金数
     * @param p 数组，存放每个金矿需要的工人数
     * @return
     */
    public static int getMostGold(int n, int w, int[] g, int[] p) {

        //存放上一行的结果
        int[] preResults = new int[w];
        //存放当前行的结果
        int[] results = new int[w];

        //填充边界格子的值
        //保存的是第一行的数据
        for (int i = 1; i <= w; i++) {
            if (i < p[0]) {
                preResults[i-1] = 0;
            } else {
                preResults[i-1] = g[0];
            }
        }

        //填充其余格子的值，从上一行推出下一行，外层循环是金矿数量，内层循环是工人数
        //内层循环工人数从第二行数据开始，所以i = 1
          for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i]) {
                    //这里表示的是当前人数不足以挖当前的矿的时候，等于上一行相同人数挖的矿
                    results[j-1] = preResults[j-1];
                } else {
                    int a = preResults[j-1] ;
                    int b ;
                    //自下而上，存在一种情况，挖了当前的矿，剩下的不足以挖别的矿 j-p[j] = 剩余人数
                    if (j - p[i]  < 1 ){
                        b =  g[i];
                    }else {
                        b = preResults[j - p[i] -1] +  g[i] ;
                    }
                    results[j-1] = Math.max(a, b);
                }
            }
            for (int j = 0; j < w; j++) {
                preResults[j] = results[j];
            }
        }
        return results[w-1];
    }


}
