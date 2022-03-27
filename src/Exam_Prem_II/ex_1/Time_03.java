package Exam_Prem_II.ex_1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Time_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstTimeline = scanner.nextLine().split("\\s+");
        String[] secondTimeline = scanner.nextLine().split("\\s+");

        int[][] dp = new int[firstTimeline.length + 1][secondTimeline.length + 1];

        for (int row = 1; row <= firstTimeline.length; row++) {
            for (int col = 1; col <= secondTimeline.length; col++) {
                if (firstTimeline[row - 1].equals(secondTimeline[col - 1])) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        int row = firstTimeline.length;
        int col = secondTimeline.length;

        Deque<String> correctTimeline = new ArrayDeque<>();

        while (row > 0 && col > 0) {
            if (firstTimeline[row - 1].equals(secondTimeline[col - 1])) {
                correctTimeline.push(firstTimeline[row - 1]);
                row--;
                col--;
            } else if (dp[row - 1][col] > dp[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }

        StringBuilder out = new StringBuilder();

        while (!correctTimeline.isEmpty()) {
            out.append(correctTimeline.pop()).append(" ");
        }
        out.append(System.lineSeparator()).append(dp[firstTimeline.length][secondTimeline.length]);
        System.out.println(out);
    }
}
