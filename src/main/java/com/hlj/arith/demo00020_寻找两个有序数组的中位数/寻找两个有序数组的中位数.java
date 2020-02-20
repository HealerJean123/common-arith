package com.hlj.arith.demo00020_寻找两个有序数组的中位数;

import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName 寻找两个有序数组的中位数
 * @date 2020/2/20  13:45.
 * @Description
 */
public class 寻找两个有序数组的中位数 {


    @Test
    public void test() {
        int[] A = new int[]{1, 3};
        int[] B = new int[]{2};

        System.out.println(findMedianSortedArrays(A, B));
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        //数组 A 的长度
        int la = A.length;
        //数组 B 的长度
        int lb = B.length;

        //如果数组 A 比较长，则交换 A、B 数组
        if (la > lb) {
            int[] temp = A;
            A = B;
            B = temp;

            int tempL = la;
            la = lb;
            lb = tempL;
        }
        //A 数组折半查找左边界
        int aMin = 0;
        //A 数组折半查找右边界
        int aMax = la;

        // halfLen 的作用就是中点坐标，当 A 数组中折半查找向右移动时，B 数组以 halfLen 为相对点向左移动，以保持始终均分
        int halfLen = (la + lb + 1) >> 1;
        //二分查找

        //情况一: A 数组为空，中位数在 B 数组
        //情况二: A 数组较短
        //  1) A 数组元素都较小，中位数在B数组
        //  2) A 数组元素都较大，中位数在B数组
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        //情况三: A、B 等长
        //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
        //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        while (aMin <= aMax) {
            //A数组中分割点
            int aIndex = (aMin + aMax) / 2;
            //B数组中分割点
            int bIndex = halfLen - aIndex;

            //数组 A 分割点相邻左边那个元素比数组 B 分割点相邻右边那个元素大，则应该将数组 A 分割点向右移，数组 B 分割点向左移
            //数组 A 分割点有向左移趋势，需检查左边界
            if (aIndex > aMin && A[aIndex - 1] > B[bIndex]) {
                aMax = aIndex - 1;
                //数组 A 分割点相邻右边那个元素比数组 B 分割点相邻左边那个元素大，则应该将数组 A 分割点向左移，数组 B 分割点向右移
                //数组 A 分割点有向右移趋势，需检查右边界
            } else if (aIndex < aMax && B[bIndex - 1] > A[aIndex]) {
                aMin = aIndex + 1;
                //得出结果
            } else {

                int leftPart = 0;
                //情况一,情况二2，情况三2
                if (aIndex == 0) {
                    leftPart = B[bIndex - 1];
                    //情况三1
                } else if (bIndex == 0) {
                    leftPart = A[la - 1];
                    //情况二1,情况二3,情况三3
                } else {
                    leftPart = Math.max(A[aIndex - 1], B[bIndex - 1]);
                }

                //元素个数总和为奇数
                if ((la + lb) % 2 == 1) {
                    return leftPart;
                }

                //情况一: A 数组为空，中位数在 B 数组
                //情况二: A 数组较短
                //  1) A 数组元素都较小，中位数在B数组
                //  2) A 数组元素都较大，中位数在B数组
                //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
                //情况三: A、B 等长
                //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
                //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
                //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半

                //元素个数总和为偶数
                int rightPart = 0;
                //情况一,情况二1
                if (aIndex == la) {
                    rightPart = B[bIndex];
                    //情况三2
                } else if (bIndex == lb) {
                    rightPart = A[0];
                    //情况二2、3，情况三1、3
                } else {
                    rightPart = Math.min(A[aIndex], B[bIndex]);
                }
                return (leftPart + rightPart) / 2.0;
            }

        }
        return 0;
    }
}
