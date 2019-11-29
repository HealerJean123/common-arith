package com.hlj.arith.demo00016大数相减;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author HealerJean
 * @ClassName D16Main
 * @date 2019/11/28  11:11.
 * @Description
 */
public class D16Main {
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] lineArray = line.split("-");

            Integer a[] = Arrays.stream(lineArray[0].split("")).map(Integer::valueOf).toArray(Integer[]::new);
            Integer b[] = Arrays.stream(lineArray[1].split("")).map(Integer::valueOf).toArray(Integer[]::new);
            Integer f[] = new Integer[a.length];
            int n = 0;
            int aIndex = a.length - 1;
            int bIndex = b.length - 1;

            while (bIndex > -1) {
                //如果a当前索引位 大于等于 b，则直接相减 否则就要借位了哦
                if (a[aIndex] >= b[bIndex]) {
                    f[n] = (a[aIndex] - b[bIndex]);
                } else {
                    //因为肯定是要借位的，所以在a索引位直接借位 +10  在减去b索引位为，然后，开始借位重新计算a数组的值
                    f[n] = a[aIndex] + 10 - b[bIndex];
                    jiewei(a, aIndex);
                }
                aIndex--;
                bIndex--;
                n++;
            }

            //a肯定会有剩余的，所以需要讲a也放到最终的数组中去
            while (aIndex > -1){
                f[n] = a[aIndex];
                aIndex--;
                n++;
            }

            //开始倒叙输出，因为有null的值，需要清理
            StringBuilder sb = new StringBuilder();
            int s = a.length - 1;
            for (; s > -1; s--) {
                if (f[s] != null) {
                    sb.append(f[s]);
                }
            }
            System.out.println(sb);
        }
    }



    /**
     * 借位 比如输入 [1000] i =3  => 0990
     */
    public static void jiewei(Integer fox[], int index) {
        if (index < 0) {
            throw new RuntimeException("不可借位");
        }
        if (fox[index - 1] > 0) {
            fox[index - 1] = fox[index - 1] - 1;
        } else {
            fox[index - 1] = 9;
            jiewei(fox, index - 1);
        }
    }

}
