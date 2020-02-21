package com.hlj.arith.demo00023_岛屿的最大面积;


import org.junit.Test;


/**
 * 作者：HealerJean
 * 题目：岛屿的最大面积
 * 解题思路：遍历二维数组，如果为1，则进行上下左右遍历。注意边界；
 */
public class 岛屿的最大面积 {

    @Test
    public void test() {
        int[][] array = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        int aLength = array.length;
        int bLength = array[0].length;
        int bigSize = 0;
        for (int i = 0; i < aLength; i++) {
            for (int j = 0; j < bLength; j++) {
                //当为0或者2的时候就不会进入
                if (array[i][j] == 1) {
                    bigSize = Math.max(kuosan(i, j, array), bigSize);
                }
            }
        }

        System.out.println("最大岛屿为：" + bigSize);
    }

    /**
     * 从某个节点开始扩散
     */
    public int kuosan(int i, int j, int[][] array) {
        int aLength = array.length;
        int bLength = array[0].length;
        if (i >= 0 && i < aLength &&
            j >= 0 && j < bLength &&
            array[i][j] == 1) {
            //已经使用过了，讲当前节点设置为0，防止重复读取
            array[i][j] = 0;
            //每次进入就 + 1
            int num = kuosan(i - 1, j, array) +
                    kuosan(i + 1, j, array) +
                    kuosan(i, j - 1, array) +
                    kuosan(i, j + 1, array) +
                    1;

            //防止重复执行 kuosan方法。
            array[i][j] = 2;
            return num;
        }
        return 0;
    }


}
