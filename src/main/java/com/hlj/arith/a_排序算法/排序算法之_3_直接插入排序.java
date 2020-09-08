package com.hlj.arith.a_排序算法;

import org.junit.Test;

import java.util.Arrays;


public class 排序算法之_3_直接插入排序 {
    @Test
    public void test() {
        int nums[] = {49, 38, 65, 97, 76, 13, 27, 50};
        直接插入排序(nums);
        System.out.println(Arrays.toString(nums));
    }


    public void 直接插入排序(int nums[]) {
        //从i等于1开始表示a[1] 也即是从第二个数字开始进行比较，进行n-1趟排序
        for (int i = 1; i < nums.length; i++) {
            // 待插入元素
            int temp = nums[i];
            int j;
            for (j = i; j > 0; j--) {
                // 将大于temp的往后移动一位,其实就是和temp进行比较移动，已经排序的二舅不会移动了
                if (nums[j - 1] > temp) {
                    nums[j] = nums[j - 1]; //执行完这个 j之后还要 继续执行下一个  j-- 最后代表的就是 实际 带待插入元素的位置
                } else {
                    break;
                }
            }

            //如果不变则原封不动给它(主要原因)，如果变了则将它赋值给j  进行归为，此时的j就是我们上面排序之后找到的j的位置
            nums[j] = temp;
        }

    }


}
