package com.hlj.arith.demo00063_最大矩形;

import org.junit.Test;

/**
作者：HealerJean
题目：给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     示例:
         输入:
         [
         ['1','0','1','0','0'],
         ['1','0','1','1','1'],
         ['1','1','1','1','1'],
         ['1','0','0','1','0']
         ]
         输出: 6
 解题思路：
*/
public class 最大矩形 {

    @Test
    public void test(){
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {

        int max = 0 ;
        for (int i = 0 ; i < matrix.length ; i++){
            for (int j = 0 ; j < matrix[0].length ; j++){
                if (matrix[i][j] == '1'){
                    max = Math.max(xunzhao(matrix, i, j), max);
                }
            }
        }

        return max ;
    }

    public int xunzhao(char[][] matrix, int i, int j) {
        if (i >= 0 && i < matrix.length
            && j >= 0 && j < matrix[0].length
            && matrix[i][j] == '1') {

            matrix[i][j] = '0';
            int num = xunzhao(matrix, i - 1, j)
                    + xunzhao(matrix, i + 1, j)
                    + xunzhao(matrix, i, j - 1)
                    + xunzhao(matrix, i, j + 1)
                    + 1;
            matrix[i][j] = '2';
            return num;
        }
        return 0 ;
    }

}
