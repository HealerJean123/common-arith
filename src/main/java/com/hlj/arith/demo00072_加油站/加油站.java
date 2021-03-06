package com.hlj.arith.demo00072_加油站;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName 加油站
 * @date 2020/5/20  16:25.
 * @Description
 */
/**
作者：HealerJean
题目：加油站
 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 说明: 如果题目有解，该答案即为唯一答案。
 示例 1:
     输入:
     gas  = [1,2,3,4,5]
     cost = [3,4,5,1,2]

     输出: 3
     解释:
     从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     因此，3 可为起始索引。
解题思路：
 total：跑完全程总共还剩多少油量
 cur：当前油箱剩余的油量,
 需要重点理解的是，
 1、只遍历一遍数组，肯定能够求得total剩余的总油量，当它 >= 0 的时候，肯定能跑完。所以生效的就是求从哪里出发了
 2、遍历一遍数组，相当于每个都作为过一次起点的情况了， cur：当前剩余油量如果小于 0 ，表示走不下去了，将下一个节点设置为起点，
 3、遍历数组。最后先以total的结果判断
 （油箱内的油肯定越来越多，所以只要遇到油量是负数，start经过的就不成立）
*/
public class 加油站 {

    @Test
    public void test() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));

    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        //跑完全程总共还剩多少油量
        int total = 0;
        //出发到下一站，当前油箱剩余的油量
        int cur = 0;
        int start = 0;
        for (int i = 0; i < n; ++i) {
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];
            if (cur < 0) {
                //下一个点作为起点
                start = i + 1;
                cur = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

}
