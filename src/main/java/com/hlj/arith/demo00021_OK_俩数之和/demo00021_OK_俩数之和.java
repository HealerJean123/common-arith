package com.hlj.arith.demo00021_OK_俩数之和;

import java.util.Arrays;

/**
 * @author HealerJean
 * @ClassName demo00021_OK_俩数之和
 * @date 2020/1/10  17:18.
 * @Description
 *  比如 2 ， 5 ， 5 ， 11 ，
 *  1、从 2 开始，分别和 5，5，11 相加然后和target比较，   i = 0 ; j = 1 ;
 *  2、从 5 开始，分表和 5，11    相加然后和target比较,    i = 1 ; j + 2 ;
 *  3、从 5 开始，分表和 11       相加然后和target比较,    i = 3 ; j = 4 ;
 *  也就是，两层for循环，
 */
public class demo00021_OK_俩数之和 {

    public static void main(String[] args) {
        int[] nums = {2, 5, 5, 11};
        int target = 16;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        for (int i = 0; i <= size - 2; i++) {
            for (int j = i + 1; j <= size - 1; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return nums;
    }
}
