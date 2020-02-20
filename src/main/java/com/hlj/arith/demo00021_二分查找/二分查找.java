package com.hlj.arith.demo00021_二分查找;

import org.junit.Test;


/**
 作者：HealerJean
 题目：二分查找
 解题思路：
 */
public class 二分查找 {

    @Test
    public void test() {
        int a[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 16, 17, 18, 19, 20};
        int x = 20;
        System.out.println(method(a, x));
    }

    public int method(int[] a, int x) {
        int low = 0;
        int high = a.length ;
        int mid;
        //最后一定的是low和hig重合和x得坐标相等
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] == x) {
                return mid;
            }
            if (a[mid] < x) {
                low = mid + 1;
            }
            if (a[mid] > x) {
                high = mid - 1;
            }
            if (low > high) {
                throw new IllegalArgumentException("查找失败");
            }
        }
        throw new IllegalArgumentException("查找失败");
    }

}
