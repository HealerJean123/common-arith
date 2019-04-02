package com.hlj.arith.domo00014数组算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Description
 * @Author HealerJean
 * @Date 2019/3/18  下午10:40.
 */
public class D01数组中找到最大的数据 {

    int a []  = {7,6,5,4,3,2,1,0} ;
    int b []  = {8,5,3,2} ;

    @Test
    public void success(){

        int c[] = new int[a.length+b.length],i =0 ,j = 0, n = 0;
        boolean isEquals = false ;
        while ( i < a.length && j < b.length ){ //保证两个数组同时遍历
            if(a[i]>b[j]){
                //防止重复赋值
                if(n>0 && c[n-1] ==a[i]){
                    i++ ;
                    continue;
                }

                c[n] = a[i];
                i++;
            }else {
                //防止重复赋值
                if(n>0 && c[n-1] ==b[j]){
                    j++ ;
                    continue;
                }
                c[n] = b[j];
                j++;
            }
            n++;
        }

        //上面有一方会提前结束
        while (i <a.length ){

            //防止重复赋值
            if(n>0 && c[n-1] ==a[i]){
                i++ ;
                continue;
            }

            c[n] = a[i];
            i++;
            n++;
        }

        while (j <b.length ){

            //防止重复赋值
            if(n>0 && c[n-1] ==b[j]){
                j++ ;
                continue;
            }

            c[n] = b[j];
            j++;
            n++;
        }


        System.out.println(Arrays.toString(c));

    }


}
