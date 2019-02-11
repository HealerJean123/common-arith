package com.hlj.arith.sort;

import org.junit.Test;

/**
 * @Description
 * @Author HealerJean
 * @Date 2018/4/23  下午3:03.
 */
public class 直接插入排序 {

    /**
     * 1、直接插入排序 ：个人理解，就是往后移动，依次把小的放到前面来
     */
    @Test
    public  void insertionSort() {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        System.out.println("----------插入排序开始：---------");
        print(a);
        for (int i = 1; i < a.length; i++) {//从i等于1开始表示a[1] 也即是从第二个数字开始进行比较，进行n-1趟排序
            // 待插入元素
            int temp = a[i];
            int j ;
            for (j = i; j >  0; j--)
            {
                // 将大于temp的往后移动一位,其实就是和temp进行比较移动，已经排序的二舅不会移动了
                if (a[j-1] > temp)
                {
                    a[j] = a[j-1]; //执行完这个 j之后还要 继续执行下一个  j-- 最后代表的就是 实际 带待插入元素的位置
                }
                else
                {
                    break;
                }
            }

            a[j] = temp; //，如果不变则原封不动给它(主要原因)，如果变了则将它赋值给j  进行归为，此时的j就是我们上面排序之后找到的j的位置

            System.out.printf("第"+i+"趟排序结果,");
            print(a);
        }

        System.out.print("最终插入排序结果： ");
        print(a);
        System.out.println("--------------------");
    }



    /**
     *
     打印的结果
     */
    private static void print(int []a) {
        for (int i : a){
            System.out.print(i + " ");
        }
        System.out.println();
    }




}
