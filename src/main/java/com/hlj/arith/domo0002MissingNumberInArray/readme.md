
### 2、给定一个 1-100 的整数数组，请找到其中缺少的数字。

#### 代码目录 (domo0002MissingNumberInArray)

#### 知识点
<font color="red">
  
 *  BitSet的应用场景  海量数据去重、排序、压缩存储
 *  BitSet的基本操作   and（与）、or（或）、xor（异或）
 *  
</font>


```
package com.hlj.arith.domo0002MissingNumberInArray;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @Desc: 给定一个 1-100 的整数数组，请找到其中缺少的数字。
 *
 * 解答：
 *  BitSet的应用场景  海量数据去重、排序、压缩存储
 *  BitSet的基本操作   and（与）、or（或）、xor（异或）
 *
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
        int missing = getMissingNumber(iArray, 5);

    }

    /**
     * BitSet   海量数据去重、排序、压缩存储
     * @param numbers
     * @param count
     */
    private  void printMissingNumber(int[] numbers, int count) {
        int missingCount = count - numbers.length;
        BitSet bitSet = new BitSet(count);

        for (int number : numbers) {
            bitSet.set(number - 1); //数据是从1开头的，这里是获取下标索引
        }

        System.out.println("一共有"+count+"个数据：目前数组为"+Arrays.toString(numbers));

        int lastMissingIndex = 0;
        for (int i = 0; i < missingCount; i++) {
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);//返回下个清零位的索引，（即，下一个零位），从由startIndex指定的索引开始
            System.out.println(++lastMissingIndex);
        }

    }

    /**
     * 如果是数组中之缺少一个，则根据1到100的累加和 减去 目前的数组的累加和
     * @param numbers
     * @param totalCount
     * @return
     */
    private  int getMissingNumber(int[] numbers, int totalCount) {
        int expectedSum = totalCount * ((totalCount + 1) / 2);
        int actualSum = 0;
        for (int i : numbers) {
            actualSum += i;
        }
        System.out.println("一共有"+totalCount+"个数据：目前数组为"+Arrays.toString(numbers));
        System.out.println(expectedSum - actualSum);

        return expectedSum - actualSum;
    }

}


```
