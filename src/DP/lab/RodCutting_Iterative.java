package DP.lab;

import java.util.Arrays;
import java.util.Scanner;

public class RodCutting_Iterative {

    public static int[] prices;
    public static int[] bestPrices;
    public static int[] prevIndex;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        prices = readArray(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());

        bestPrices = new int[length + 1];
        prevIndex = new int[length + 1];

        int maxProfit = cutRode(length);

        System.out.println(maxProfit);

        while (length - prevIndex[length] != 0) {
            System.out.print(prevIndex[length] + " ");
            length = length - prevIndex[length];
        }

        System.out.println(prevIndex[length]);

    }

    private static int cutRode(int length) {
        for (int i = 1; i <= length; i++) {
            int currentBest;
            for (int j = 1; j <= i; j++) {
                currentBest =
                Math.max(bestPrices[i], prices[j] + bestPrices[i - j]);
                if (currentBest > bestPrices[i]) {
                    bestPrices[i] = currentBest;
                    prevIndex[i] = j;
                }
            }
        }
        return bestPrices[length];
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
