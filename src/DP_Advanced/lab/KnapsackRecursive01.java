package DP_Advanced.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackRecursive01 {

    public static List<Integer> prices = new ArrayList<>();
    public static List<Integer> weights = new ArrayList<>();

    public static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());

        String line = scanner.nextLine();
        while (!line.equals("end")) {
            String[] tokens = line.split("\\s+");

            int weight = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            weights.add(weight);
            prices.add(value);

            line = scanner.nextLine();
        }

        dp = new int[prices.size() + 1][capacity + 1];

        for (int[] elements : dp) {
            Arrays.fill(elements, -1);
        }

        int result = recurrence(0, 0, capacity);

        System.out.println("Total Value: " + result);

    }

    private static int recurrence(int valueIndex, int weightIndex, int capacity) {
        if (valueIndex >= prices.size() || weightIndex >= weights.size() || weights.get(weightIndex) > capacity) {
            return 0;
        }

        if (dp[valueIndex][capacity] != -1) {
            return dp[valueIndex][capacity];
        }

        return dp[valueIndex][capacity] = Math.max(recurrence(valueIndex + 1, weightIndex + 1, capacity),
                recurrence(valueIndex + 1, weightIndex + 1, capacity - weights.get(weightIndex)) +
                        prices.get(valueIndex));
    }
}
