package Exam_Prem_II.ex_2;

import java.util.Arrays;
import java.util.Scanner;

public class BattlePoints_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] requiredEnergy = readArray(scanner.nextLine());
        int[] points = readArray(scanner.nextLine());
        int energy = Integer.parseInt(scanner.nextLine());

        int[][] dp = new int[points.length + 1][energy + 1];

        for (int row = 1; row <= points.length; row++) {
            for (int col = 1; col <= energy; col++) {
                if (requiredEnergy[row - 1] <= col) {
                    dp[row][col] = Math.max(dp[row - 1][col - requiredEnergy[row - 1]] + points[row - 1],
                            dp[row - 1][col]);
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }

        System.out.println(dp[points.length][energy]);

    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
