package com.hlj.arith.demo00017_找出旋转有序数列的中间值;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author HealerJean
 * @ClassName 最长连续数列
 * @date 2019/11/28  19:32.
 * @Description 给出一个有序数列随机旋转之后的数列，如原有序数列为：[0,1,2,4,5,6,7] ，旋转之后为[4,5,6,7,0,1,2]。 假定数列中无重复元素，且数列长度为奇数。 求出旋转数列的中间值。如数列[4,5,6,7,0,1,2]的中间值为4。
 * 解题思路：先排序，在选择中间的
 *
 */
public class 找出旋转有序数列的中间值 {


    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            Integer array[] = Arrays.stream(line.split(",")).map(Integer::valueOf).toArray(Integer[]::new);
            Arrays.sort( array);

            System.out.println( array[array.length/2 + array.length%2 -1] );
        }
    }

}
