package DP_Advanced.lab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestCommonSubsequence_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        int[][] lcs = new int[first.length() + 1][second.length() + 1];

        for (int row = 1; row <= first.length(); row++) {
            for (int col = 1; col <= second.length(); col++) {
                if (first.charAt(row - 1) == second.charAt(col - 1)) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                } else {
                    lcs[row][col] = Math.max(lcs[row - 1][col], lcs[row][col - 1]);
                }
            }
        }

        int row = first.length();
        int col = second.length();

        System.out.println(lcs[first.length()][second.length()]);

        Deque<Character> lcsLetters = new ArrayDeque<>();

        while (row > 0 && col > 0) {
            if (first.charAt(row - 1) == second.charAt(col - 1)
                    && lcs[row][col] - 1 == lcs[row - 1][col - 1]) {
                lcsLetters.push(first.charAt(row - 1));
                row--;
                col--;
            } else if (lcs[row - 1][col] == lcs[row][col]) {
                row--;
            } else {
                col--;
            }
        }

        System.out.println(lcsLetters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("")));
    }
}
