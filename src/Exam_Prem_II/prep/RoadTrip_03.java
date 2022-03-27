package Exam_Prem_II.prep;

import java.util.Arrays;
import java.util.Scanner;

public class RoadTrip_03 {

    public static int[] values;
    public static int[] spaceAmount;
    public static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        values = readArray(scanner.nextLine());
        spaceAmount = readArray(scanner.nextLine());
        int maxCapacity = Integer.parseInt(scanner.nextLine());

         dp = new int[values.length + 1][maxCapacity + 1];

        for (int row = 1; row <= values.length; row++) {
            for (int col = 1; col <= maxCapacity; col++) {
                if (spaceAmount[row - 1] <= col) {
                    dp[row][col] = Math.max(values[row - 1] + dp[row - 1][col - spaceAmount[row - 1]],
                            dp[row - 1][col]);
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }
         
        System.out.println("Maximum value: " + dp[values.length][maxCapacity]);
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
