package DP.ex;

import java.util.Arrays;
import java.util.Scanner;

public class SumWithUnlimitedCoins_04 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int[] coins = readArray(scanner.nextLine());
        
        int targetSum = Integer.parseInt(scanner.nextLine());
        
        int[] dp = new int[targetSum + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= targetSum; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println(dp[targetSum]);
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
