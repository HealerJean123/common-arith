package com.hlj.arith.demo00017_找出旋转有序数列的中间值;

import java.util.Arrays;
import java.util.Scanner;


/**
 题目：给出一个有序数列随机旋转之后的数列，如原有序数列为：[0,1,2,4,5,6,7] ，旋转之后为[4,5,6,7,0,1,2]。    假定数列中无重复元素，且数列长度为奇数。 求出旋转数列的中间值。如数列[4,5,6,7,0,1,2]的中间值为4。
 解题思路：先排序，再选择中间的
 */
public class 找出旋转有序数列的中间值 {


    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] str = line.split(",");
            int length = str.length ;
            int[] array = new int[length];
            for(int i = 0 ; i < length ; i++  ){
                array[i] = Integer.valueOf( str[i]);
            }

            Arrays.sort(array);
            System.out.println(array[length/2]);
        }
    }
}
