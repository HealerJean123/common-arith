### 五家共井

```java

package com.hlj.arith.demo0004五家共井;

import org.junit.jupiter.api.Test;

/**
作者：HealerJean
由来：
     古代数学巨著《九章算数》中有这么一道题叫“五家共井，甲二绠（汲水用的井绳）不足，如（接上）乙一绠；乙三绠不足，如丙一绠；
     丙四绠不足，如丁一绠；丁五绠不足，如戊一绠；戊六绠不足，如甲一绠，皆及。
题目：
    意思就是说五家人共用一口井
    甲家的绳子用两条不够，还要再用乙家的绳子一条才能打到井水；
    乙家的绳子用三条不够，还要再用丙家的绳子一条才能打到井水；
    丙家的绳子用四条不够，还要再用丁家的绳子一条才能打到井水；
    丁家的绳子用五条不够，还要再用戊家的绳子一条才能打到井水；
    戊家的绳子用六条不够，还要再用甲家的绳子一条才能打到井水。

 最后问：井有多深？每家的绳子各有多长？

解题思路：

  井深 h，绳长 a b c d e
     2*len1+len2=h
     3*len2+len3=h
     4*len3+len4=h
     5*len4+len5=h
     6*len5+len1=h
 通过上面的方程：
    len1 = len2+len3/2 1和2
    len2 = len3+len4/3 2和3
    len3 = len4+len5/4 3和4
    len4 = len5+len1/5 4和5
 得到上面的结果之后，我们限制一下，假定都是整数，古代么，哪里来那么多小数。所以假定都是整数
 len3为2的倍数 len4为3的倍数 len5为4的倍数 len1为5的倍数 len1肯定是最长的，通过上述理解

 */
public class TestMain {

    @Test
    public void start() {
        //因为没有最大值，所以for循环中不设置最大
        int len,len1,len2,len3,len4,len5;
        for(len5=4;;len5+=4){
            for(len1=5;;len1+=5){ //这里我们写入它，因为我一步步走到它的时候，可以通过判断它的大小进行结束循环
                len4 = len5 + len1 / 5;
                if(len4%3==0){//如果len4能被3整除
                    len3 = len4 + len5 / 4;
                    if (len3 % 2 == 0) { //如果len3能被2整除
                        len2 = len3 + len4 / 3;
                        //这里非常关键，用的是第二层for循环中的数据，len1 相等的时候是正确的，
                        // 当大于计算结果的时候，就切出当前循环因为这个时候的len1太大了，再这么下去会让for'循环中的len1更大
                        // 当结果比较小的是，我们继续循环
                        if (len1 > len2 + len3 / 2) {
                            break;
                        } else if (len1 == len2 + len3 / 2) {
                            len = len2 + len1 * 2;
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


```
