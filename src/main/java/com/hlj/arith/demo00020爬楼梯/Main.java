package com.hlj.arith.demo00020爬楼梯;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strs = scanner.nextLine().trim().split(",");
        String a = strs[0], b = strs[1], c = strs[2];
        int aLen = strs[0].length(), bLen = strs[1].length(), cLen = strs[2].length();
        if (aLen + bLen != cLen) {
            System.out.println("false");
            return;
        }

        boolean[][] dp = new boolean[aLen + 1][bLen + 1];
        dp[0][0] = true;
        for (int i = 0; i < aLen + 1; i++) {
            for (int j = 0; j < bLen + 1; j++) {
                if (i - 1 >= 0 && a.charAt(i - 1) == c.charAt(i + j - 1) && dp[i - 1][j] == true) {

                    dp[i][j] = true;

                } else if (j - 1 >= 0 && b.charAt(j - 1) == c.charAt(i + j - 1) && dp[i][j - 1] == true) {
                    dp[i][j] = true;
                } else {
                    if (i > 0 || j > 0) {
                        dp[i][j] = false;
                    }
                }
            }
        }
        System.out.println(dp[aLen][bLen]);
    }

}
