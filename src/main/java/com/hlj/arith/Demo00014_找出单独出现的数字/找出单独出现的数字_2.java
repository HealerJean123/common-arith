package com.hlj.arith.Demo00014_找出单独出现的数字;

import org.junit.Test;

/**
 作者：HealerJean
 题目：只出现一次的数字
 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 示例 1:
     输入: [2,2,3,2]
     输出: 3
 示例 2:
     输入: [0,1,0,1,0,1,99]
     输出: 99
 解题思路：
 */
public class 找出单独出现的数字_2 {


    @Test
    public void test(){
        int[] nums = {4,1,2,1,2};
        System.out.println(1 ^ 0);
        System.out.println(singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int result = nums[0];
        for(int i = 1; i < nums.length ; i++){
            result = result ^ nums[i];
        }
        return result;
    }
}

