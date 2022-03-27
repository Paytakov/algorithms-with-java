package Searching_Sorting_Greedy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SumOfCoins_04 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));


        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        for (Map.Entry<Integer, Integer> usedCoin : usedCoins.entrySet()) {
            System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Map<Integer, Integer> usedCoins = new LinkedHashMap<>();

        int maxCoinIdx = coins.length - 1;
        while (targetSum != 0) {
            int coinsToTake = targetSum / coins[maxCoinIdx];
            if (coinsToTake != 0) {
                usedCoins.put(coins[maxCoinIdx], coinsToTake);
            }
            targetSum %= coins[maxCoinIdx];
            maxCoinIdx--;
        }

        return usedCoins;
    }
}