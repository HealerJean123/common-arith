package com.hlj.arith.Demo00014_找出单独出现的数字;

import org.junit.Test;

/**
 作者：HealerJean
 题目：只出现一次的数字
 解题思路：异或特性，不相同为1

 8421

 4  0100
 1  0001
 2  0010
 1  0001
 2  0010

 除了只出现1次的，其它都出现2次，所以其他的异或后肯定为  0 ，全部异或后 最后的结果就是唯一的那个
 */
public class 找出单独出现的数字 {


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

