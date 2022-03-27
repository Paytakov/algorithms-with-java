package DP.ex;

import java.util.Scanner;

public class WordDifferences_06 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         char[] first = scanner.nextLine().toCharArray();
         char[] second = scanner.nextLine().toCharArray();

         int[][] dp = new int[first.length + 1][second.length + 1];

        for (int row = 0; row <= first.length; row++) {
            for (int col = 0; col <= second.length; col++) {
                if (row == 0) {
                    dp[row][col] = col;
                } else if (col == 0) {
                    dp[row][col] = row;
                } else if (first[row - 1] == second[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - 1]) + 1;
                }
            }
        }

        System.out.println("Deletions and Insertions: " + dp[first.length][second.length]);
    }
}
