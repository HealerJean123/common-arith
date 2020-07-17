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
        int target = 5;
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target){
                return i ;
            }
        }
        return nums.length;
    }


}
