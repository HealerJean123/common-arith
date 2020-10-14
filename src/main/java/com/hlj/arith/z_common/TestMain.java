package com.hlj.arith.z_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {

    @Test
    public void test(){
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println( Arrays.toString(nums));
    }


    public void sortColors(int[] nums) {
        int i = 0 ;
        int j = nums.length-1;
        int index = 0 ;
        while (index <= j){
            if (nums[index] == 0){
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                i++ ;
                index ++;
            }else  if(nums[index] == 2){
                int temp = nums[index];
                nums[index] = nums[j];
                nums[j] = temp;
                j--;
            }else {
                index ++;
            }
        }
    }


}
