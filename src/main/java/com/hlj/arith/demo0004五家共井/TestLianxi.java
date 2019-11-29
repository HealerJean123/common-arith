package com.hlj.arith.demo0004五家共井;

import org.junit.Test;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/2/27  下午5:25.
 *作者：HealerJean
 * 由来：五家共井
 *      古代数学巨著《九章算数》中有这么一道题叫“五家共井，甲二绠（汲水用的井绳）不足，如（接上）乙一绠；乙三绠不足，如丙一绠；
 *      丙四绠不足，如丁一绠；丁五绠不足，如戊一绠；戊六绠不足，如甲一绠，皆及。
 * 题目：五家共井
 *     意思就是说五家人共用一口井
 *     甲家的绳子用两条不够，还要再用乙家的绳子一条才能打到井水；
 *     乙家的绳子用三条不够，还要再用丙家的绳子一条才能打到井水；
 *     丙家的绳子用四条不够，还要再用丁家的绳子一条才能打到井水；
 *     丁家的绳子用五条不够，还要再用戊家的绳子一条才能打到井水；
 *     戊家的绳子用六条不够，还要再用甲家的绳子一条才能打到井水。
 *
 *  最后问：井有多深？每家的绳子各有多长？
 *
 *
 a b c d e  l


 井深 h，绳长 a b c d e
 2*len1+len2=h
 3*len2+len3=h
 4*len3+len4=h
 5*len4+len5=h
 6*len5+len1=h

 通过上面的方程：
 len1 = len2+len3/2
 len2 = len3+len4/3
 len3 = len4+len5/4
 len4 = len5+len1/5


 *
 */
public class TestLianxi {

    @Test
    public void start(){

        for(int len5 = 4 ;;len5 += 4){
            for(int len1 = 5;;len1 +=5){
                int len4 = len5 +len1/5 ;
                if(len4%3==0){//表示可以被3整除
                    int len3 = len4 +len5/4 ;
                    if(len3%2==0){
                        int len2 =len3+len4/3 ;
                        if (len1 > len2 + len3 / 2) {
                            break;
                        } else if (len1 == len2 + len3 / 2) {
                             int len = len2 + len1 * 2;
                            System.out.println("井深为：" + len);
                            System.out.println("甲家绳子长度为：" + len1);
                            System.out.println("乙家绳子长度为：" + len2);
                            System.out.println("丙家绳子长度为：" + len3);
                            System.out.println("丁家绳子长度为：" + len4);
                            System.out.println("戊家绳子长度为：" + len5);
                        }
                    }

                }
            }
        }


    }


}
