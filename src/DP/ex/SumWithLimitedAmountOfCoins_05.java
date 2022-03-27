package DP.ex;

import java.util.Arrays;
import java.util.Scanner;

public class SumWithLimitedAmountOfCoins_05 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int[] coins = readArray(scanner.nextLine());

        int targetSum = Integer.parseInt(scanner.nextLine());

        int size = coins.length;


        
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
