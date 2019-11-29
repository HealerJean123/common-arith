package com.hlj.arith.demo0002MissingNumberInArray;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @Desc: 给定一个 1-100 的整数数组，请找到其中缺少的数字。
 *
 * 解答：
 *  BitSet的应用场景  海量数据去重、排序、压缩存储
 *  BitSet的基本操作   and（与）、or（或）、xor（异或）
    Bitset，也就是位图，由于可以用非常紧凑的格式来表示给定范围的连续数据而经常出现在各种算法设计中。
    基本原理是，用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过。使用用的时候既可根据某一个是否为0表示此数是否出现过。

 *
 * @Author HealerJean
 * @Date 2018/10/10  下午2:18.
 */
public class TestMain {


    @Test
    public void start(){
        // 丢失3个数据
        printMissingNumber(new int[]{1, 2, 3, 4, 6, 9, 8}, 10);


        // Only one missing number in array
        int[] iArray = new int[]{1, 2, 3, 5};
       printMissingNumber(iArray, 5);

    }

    /**
     * BitSet   海量数据去重、排序、压缩存储
     * @param numbers
     * @param count
     */
    private  void printMissingNumber(int[] numbers, int count) {

        System.out.println("一共有"+count+"个数据：目前数组为"+Arrays.toString(numbers));

        int missingCount = count - numbers.length; //一共丢失了多少位
//      Java2 版本允许指定它的初始大小，即比特，它可以容纳的数量。所有位初始化为零
        BitSet bitSet = new BitSet(count); //一共有多少数字,

        for (int number : numbers) {
            bitSet.set(number-1); //将指定索引处的位设置为 true。
        }
        System.out.println("bitSet长度"+bitSet.toString());


        int lastMissingIndex = 0; //索引从0开始
        for (int i = 0; i < missingCount; i++) {
            //返回第一个设置为 false 的位的索引，这发生在指定的   起始索引或之后的索引上。
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex);
        }

    }


}
