package DP.lab;

import java.util.Arrays;
import java.util.Scanner;

public class RodCutting_03 {

    public static int[] prices;
    public static int[] bestPrices;
    public static int[] prevIndex;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         prices = readArray(scanner.nextLine());
         int length = Integer.parseInt(scanner.nextLine());

         bestPrices = new int[length + 1];
         prevIndex = new int[length + 1];

         int maxPrice = cutRode(length);

        System.out.println(maxPrice);

        reconstructSolution(length);
    }

    private static void reconstructSolution(int length) {
        while (length - prevIndex[length] != 0) {
            System.out.print(prevIndex[length] + " ");
            length = length - prevIndex[length];
        }

        System.out.println(prevIndex[length]);
    }

    private static int cutRode(int length) {
        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currentBest = bestPrices[length];

        for (int i = 1; i <= length; i++) {
            currentBest = Math.max(currentBest, prices[i] + cutRode(length - i));
            if (currentBest > bestPrices[length]) {
                bestPrices[length] = currentBest;
                prevIndex[length] = i;
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
