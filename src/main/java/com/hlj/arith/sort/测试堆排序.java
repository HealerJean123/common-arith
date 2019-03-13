package com.hlj.arith.sort;

import java.util.Arrays;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/3/13  下午11:31.
 * 类描述：
 */
public class 测试堆排序 {


    public static void main(String[] args) {
        int[] array = new int[] { 2, 1, 4, 3, 6, 5, 8, 7 };
        // 接下来就是排序的主体逻辑
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {

        for (int i = array.length-1; i >=0 ; i--) {
            adjustHeap(array,i,array.length);
        }

        for (int j = array.length-1; j>0 ; j--){
            sway(array,0 ,j );
            adjustHeap(array,0 ,j );
        }


    }



    public static void  adjustHeap(int array[],int i,int length){
        int temp = array[i];

        for(int k = 2 * i +1 ;k <length ; k = k*2 +1){

            if(k+1 < length && array[k]<array[k+1]){
                k++ ;
            }

            if(array[k]>temp){
                sway(array,k , i);
                i = k ;
            }else {
                break;
            }
        }


    }


    public static void sway(int array[],int a,int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;

    }

}
