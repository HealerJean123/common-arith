package com.hlj.arith.z_common;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
public class TestMain {

    @Test
    public void test() {
        int[][] matrix = {
                { 1,  2,  3,  4},
                { 5,  6,  7,  8},
                { 9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        MatrixPrint.print(matrix);
        System.out.println( searchMatrix(matrix, 14));
        MatrixPrint.print(matrix);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[0].length){
            if (matrix[i][j] == target){
                return true;
            }else if (matrix[i][j] <target ){
                j ++ ;
            }else {
                i-- ;
            }
        }
        return false;
    }
}
