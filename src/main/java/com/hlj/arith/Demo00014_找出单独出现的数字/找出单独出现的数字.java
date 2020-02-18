package com.hlj.arith.Demo00014_找出单独出现的数字;

import java.util.*;

/**
 题目：
    给出N个数字。其中仅有一个数字出现过一次，其他数字均出现过两次，找出这个出现且只出现过一次的数字。要求时间和空间复杂度最小。
 解题思路：
    依次放入集合中，让集合中判断是否存在
 */
public class 找出单独出现的数字 {


    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String [] nums = line.split(" ");
            List<String> list = new ArrayList<>();
            for (String str:nums){
                if(list.contains(str)){
                    list.remove(str);
                }else {
                    list.add(str);
                }
            }
            System.out.println(list.get(0));
        }
    }
}

/**
 1 2 3 4 2 1 3
 4
 */
