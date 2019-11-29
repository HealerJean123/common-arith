package com.hlj.arith.demo00014两个有序数组合并成同一个;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Description
 * @Author HealerJean
 * @Date 2019/3/18  下午10:40.
 */
public class D01两个有序数组合并成同一个 {

    int a[] = {7, 6, 5, 4, 3, 2, 1, 0};
    int b[] = {9, 9, 9, 9, 8, 5, 3, 2};

    @Test
    public void success() {

        int c[] = new int[a.length + b.length], i = 0, j = 0, n = 0;
        //保证两个数组同时遍历
        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                c[n] = a[i];
                i++;
            } else {
                c[n] = b[j];
                j++;
            }
            n++;
        }

        //上面有一方会提前结束
        while (i < a.length) {
            c[n] = a[i];
            i++;
            n++;
        }

        while (j < b.length) {
            c[n] = b[j];
            j++;
            n++;
        }


        System.out.println(Arrays.toString(c));

    }


}
