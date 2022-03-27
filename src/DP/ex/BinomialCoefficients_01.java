package DP.ex;

import java.util.Scanner;

public class BinomialCoefficients_01 {

    public static long[][] dp;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int n = Integer.parseInt(scanner.nextLine());
         int k = Integer.parseInt(scanner.nextLine());

         dp = new long[n * k][n * k];

         long binom = calcBinom(n, k);

        System.out.println(binom);
    }

    private static long calcBinom(int n, int k) {
        if (n == k || k == 0) {
            return 1;
        }

        if (dp[n][k] != 0) {
            return dp[n][k];
        }

        return dp[n][k] = calcBinom(n - 1, k) + calcBinom(n - 1, k - 1);
    }
}
