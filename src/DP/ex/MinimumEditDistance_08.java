package DP.ex;

import java.util.Scanner;

public class MinimumEditDistance_08 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int replaceCost = Integer.parseInt(scanner.nextLine());
         int insertCost = Integer.parseInt(scanner.nextLine());
         int deleteCost = Integer.parseInt(scanner.nextLine());

         char[] first = scanner.nextLine().toCharArray();
         char[] second = scanner.nextLine().toCharArray();

         int[][] dp = new int[first.length + 1][second.length + 1];

        for (int i = 1; i <= second.length; i++) {
            dp[0][i] = dp[0][i - 1] + insertCost;
        }

        for (int i = 1; i <= first.length; i++) {
            dp[i][0] = dp[i - 1][0] + deleteCost;
        }

        for (int row = 1; row <= first.length; row++) {
            for (int col = 1; col <= second.length; col++) {
                if (first[row - 1] == second[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int delete = dp[row - 1][col] + deleteCost;
                    int insert = dp[row][col - 1] + insertCost;
                    int replace = dp[row - 1][col - 1] + replaceCost;

                    dp[row][col] = Math.min(delete, Math.min(insert, replace));
                }
            }
        }

        System.out.println("Minimum edit distance: " + dp[first.length][second.length]);
    }
}
