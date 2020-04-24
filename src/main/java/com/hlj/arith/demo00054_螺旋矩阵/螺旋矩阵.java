package com.hlj.arith.demo00054_螺旋矩阵;

import com.hlj.arith.z_common.MatrixPrint;
import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName 螺旋矩阵
 * @date 2020/4/22  18:56.
 * @Description
 */
/**
作者：HealerJean
题目： 螺旋矩阵 II
解题思路：
*/
public class 螺旋矩阵 {

    @Test
    public void test(){

        int [][] matrix = generateMatrix(4);
        MatrixPrint.print(matrix);
    }

    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) {
                mat[t][i] = num++;
            }
            t++;
            for(int i = t; i <= b; i++) {
                mat[i][r] = num++;
            }
            r--;
            for(int i = r; i >= l; i--) {
                mat[b][i] = num++;
            }
            b--;
            for(int i = b; i >= t; i--) {
                mat[i][l] = num++;
            }
            l++;
        }
        return mat;
    }


}
