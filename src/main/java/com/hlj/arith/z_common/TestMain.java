package com.hlj.arith.z_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {

    @Test
    public void test() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4} ;
        System.out.println(maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int curMax = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], curMax+ nums[i]);
            max = Math.max(max, curMax);
        }
        return max ;
    }


}
