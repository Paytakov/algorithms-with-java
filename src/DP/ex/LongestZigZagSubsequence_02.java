package DP.ex;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class LongestZigZagSubsequence_02 {

    public static int[][] dp;
    public static int[][] prev;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = readArray(scanner.nextLine());

        dp = new int[numbers.length + 1][2];
        prev = new int[numbers.length + 1][2];

        // greater
        dp[0][0] = 1;
        // lesser
        dp[0][1] = 1;

        prev[0][0] = -1;
        prev[0][1] = -1;

        int maxLength = 0;
        int[] best = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            int currentItem = numbers[i];
            for (int j = i - 1; j >= 0; j--) {
                int prevItem = numbers[j];
                if (currentItem > prevItem && dp[i][0] <= dp[j][1] + 1) {
                    dp[i][0] = dp[j][1] + 1;
                    prev[i][0] = j;
                } else if (currentItem < prevItem && dp[i][1] <= dp[j][0] + 1) {
                    dp[i][1] = dp[j][0] + 1;
                    prev[i][1] = j;
                }
            }

            if (maxLength < dp[i][0]) {
                maxLength = dp[i][0];
                best[0] = i;
                best[1] = 0;
            } else if (maxLength < dp[i][1]) {
                maxLength = dp[i][1];
                best[0] = i;
                best[1] = 1;
            }
        }

        Deque<Integer> zigZagSeq = new ArrayDeque<>();

        int beginRow = best[0];
        while (beginRow >= 0) {
            zigZagSeq.push(numbers[beginRow]);
            beginRow = prev[beginRow][best[1]];
            best[1] = best[1] == 0 ? 1 : 0;
        }

        while (!zigZagSeq.isEmpty()) {
            System.out.print(zigZagSeq.pop() + " ");
        }

    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
