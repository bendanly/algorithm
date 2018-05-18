package com.algorithm.study;

import java.util.Scanner;

/**
 * 猴子摘桃，只能摘下棵树比当前树多的桃。不许回头
 */
public class TEST3{

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int trees = scn.nextInt();
        int[] peaches = new int[trees];

        for (int i = 0; i < trees; i++) {
            peaches[i] = scn.nextInt();
        }

        int[] dp = new int[trees];

        for (int i = 0; i < trees; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                /**
                 * 表示，第j个位置上的树可以拿，并且拿了桃子的话，总大小能够超过第i个位置
                 */
                if (peaches[j] <= peaches[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 1;
        for (int i : dp)
        {max = max > i ? max : i;
        System.out.println(dp[i]);}

        System.out.println(max);
    }

}