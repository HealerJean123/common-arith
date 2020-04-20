package com.hlj.arith.demo00045_跳跃游戏;

import org.junit.Test;

/**
作者：HealerJean
题目：跳跃游戏_2 ,能否调到最后
解题思路：
*/
public class 跳跃游戏_2 {

    @Test
    public void test(){
        int[] nums = {3,2,1,1,0};
        // int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }


    public boolean canJump(int[] nums) {
        int maxPositoin = 0 ;
        //如果能跳到，倒数第二位，并且满足下面的条件。就能跳跃成功
        for (int i = 0 ; i  < nums.length -1   ; i++ ){
            maxPositoin = Math.max(nums[i] + i , maxPositoin) ;
            //i 到达最终最大位移位置，并且 当前位置为0 ，表示不能跳跃，则结束
            if (i == maxPositoin && nums[i] == 0){
                return false;
            }
        }
        return true ;
    }
}



