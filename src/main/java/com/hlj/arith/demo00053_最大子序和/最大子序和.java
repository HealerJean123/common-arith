package com.hlj.arith.demo00053_最大子序和;

import org.junit.Test;

/**
作者：HealerJean
题目：最大子序和
解题思路：
*/
public class 最大子序和 {

    @Test
    public void test(){

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
    public int maxSubArray(int[] nums) {
        int maxTemp = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; ++i) {
            //当前元素, 当前元素位置的最大和
            maxTemp = Math.max(nums[i], maxTemp + nums[i]);

            max = Math.max(max, maxTemp);
        }
        return max;
    }

}
