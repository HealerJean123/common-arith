package com.hlj.arith.demo00101_计算右侧小于当前元素的个数;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
作者：HealerJean
题目：
 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 示例:
     输入: [5,2,6,1]
     输出: [2,1,1,0]
     解释:
     5 的右侧有 2 个更小的元素 (2 和 1).
     2 的右侧仅有 1 个更小的元素 (1).
     6 的右侧有 1 个更小的元素 (1).
     1 的右侧有 0 个更小的元素.
解题思路：
*/
public class 计算右侧小于当前元素的个数 {

    @Test
    public void test(){
        int[] nums = {5,2,6,1} ;
        System.out.println(countSmaller(nums));
    }

    /**
     * 暴力法 （）
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0 ){
            return list ;
        }
        //因为是从后往前的，保存最后一个
        list.add(0);
        for (int i = nums.length-2; i >=0; i--) {
            int count = 0 ;
            for(int j = i+1 ; j < nums.length; j ++){
                if ( nums[j] < nums[i] ){
                    count++;
                }
            }
            list.add(count);
        }
        //翻转
        Collections.reverse(list);
        return list;
    }
}
