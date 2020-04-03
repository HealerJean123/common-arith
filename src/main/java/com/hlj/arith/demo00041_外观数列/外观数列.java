package com.hlj.arith.demo00041_外观数列;

/**
 * @author HealerJean
 * @ClassName 外观数列
 * @date 2020/4/3  15:55.
 * @Description
 */

import org.junit.Test;

/**
作者：HealerJean
题目：外观数列
 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 6.     312211
 解题思路：
*/
public class 外观数列 {

    @Test
    public void test(){
        System.out.println(countAndSay(7));
    }

    public String countAndSay(int n) {
        //定义一个整数数列，从1开始类型为string
        String str = "1";
        //第2项为初始值，循环代表当前是第几项
        for (int i = 2; i <= n; i++) {
            //用于拼接字符串，不会创建新的临时对象
            StringBuilder builder = new StringBuilder();
            //至少是1个1,1个2,1个n，至少有一个数，如果有多个相同数字，之后就进行count++
            int count = 1;
            //返回当前整数数列str索引0处的字符，赋值给pre
            char pre = str.charAt(0);
            //遍历str字符串，注意循环条件
            for (int j = 1; j < str.length(); j++) {
                //从索引1处开始取
                char c = str.charAt(j);
                //判断前pre和后c的值是否相等
                if (pre == c) {
                    //相等的话，count++
                    count++;
                } else {
                    //不相等则直接拼接，几个几  count个pre
                    builder.append(count).append(pre);
                    //c是pre的下一个（这个时候c赋值给pre，再次开始遍历字符串）
                    pre = c;
                    //pre不等于c，则重置count的值
                    count = 1;
                }
            }

            //上面遍历字符串之后， 最后的pre是没有被加到builder中
            // 【重 点 几个几  count个pre】
            builder.append(count).append(pre);
            //返回当前 n 的结果
            str = builder.toString();
        }
        return str;
    }

}
