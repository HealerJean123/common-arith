package com.hlj.arith.demo00017最长连续数列;

import java.util.*;

/**
 * @author HealerJean
 * @ClassName Main
 * @date 2019/11/28  18:53.
 * @Description
 * 输入一个乱序的连续数列，输出其中最长连续数列长度，要求算法复杂度为 O(n) 。
 * 输入 ：54,55,300,12,56
 * 输出 ：3
 *
 * 解题思路 ：
 * 1、首先他说连续，那么第一件事肯定是排序，所以先排序
 * 2、排好序的基础上，我们需要两个长度，一个是临时长度tempLength，一个是最大长度bigLength。
 * 3、然后从第一个开始遍历，第一个我们添加一个临时数据temp，
 *     遍历数组， temp每次加1 和 下一个进行比较，
 *              如果相等的话，临时长度+1，
 *              否则则是不连续的，需要我们的临时长度和最大长度进行比较，如果大于最大长度，则讲结果赋值给最大长度
 *     然后临时数据重新赋值，临时长度再初始化为1
 *  4、防止已经是连续数据，不进入else的情况，我们再进行比较，最终输出
 */
public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();

            Integer array[] = Arrays.stream(line.split(",")).map(Integer::valueOf).toArray(Integer[]::new);
            Arrays.sort( array);

            //最大长度默认是1
            //临时长度默认为1
            int bigLength = 1 ;
            int tempLength = 1 ;
            int size = array.length;
            int temp = array[0] ;
            for(int i = 1 ; i < size ; i++){
                if((++temp) == array[i].intValue()){
                    tempLength = tempLength+1 ;
                }else {
                    if(tempLength > bigLength){
                        bigLength = tempLength;
                    }
                    temp = array[i];
                    tempLength = 1 ;
                }
            }
            if(tempLength > bigLength){
                bigLength = tempLength;
            }
            System.out.println(bigLength );
        }
    }
}
