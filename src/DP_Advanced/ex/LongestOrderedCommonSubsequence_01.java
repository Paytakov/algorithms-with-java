package DP_Advanced.ex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestOrderedCommonSubsequence_01 {

    public static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstStr = scanner.nextLine();
        String secondStr = scanner.nextLine();

        dp = new int[firstStr.length()][secondStr.length()];

        int bestLength = -1;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < firstStr.length(); row++) {
            for (int col = 0; col < secondStr.length(); col++) {
                if (firstStr.charAt(row) == secondStr.charAt(col)) {
                    dp[row][col] = getPrevBestValue(row, col) + 1;
                }

                if (dp[row][col] > bestLength) {
                    bestLength = dp[row][col];
                    bestRow = row;
                    bestCol = col;
                }
            }
        }

        List<Character> locsLetters = new ArrayList<>();

        while (bestRow >= 0 && bestCol >= 0 && dp[bestRow][bestCol] != 0) {
            locsLetters.add(firstStr.charAt(bestRow));
            bestRow--;
            bestCol--;
        }

        Collections.reverse(locsLetters);

        System.out.println(locsLetters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("")));
    }

    private static int getPrevBestValue(int row, int col) {
        if (row - 1 < 0 || col - 1 < 0) {
            return 0;
        }

        return dp[row - 1][col - 1];
    }
}
