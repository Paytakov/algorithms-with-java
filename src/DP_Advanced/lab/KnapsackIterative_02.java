package DP_Advanced.lab;

import java.util.*;
import java.util.stream.Collectors;

public class KnapsackIterative_02 {

    public static class Item implements Comparable<Item> {
        public String name;
        public int weight;
        public int price;

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Item o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());

        List<Item> items = new ArrayList<>();

        String line = scanner.nextLine();
        while (!line.equals("end")) {
            String[] tokens = line.split("\\s+");

            String name = tokens[0];
            int weight = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            items.add(new Item(name, weight, value));

            line = scanner.nextLine();
        }

        int[][] dp = new int[items.size() + 1][capacity + 1];
        boolean[][] takenItems = new boolean[items.size() + 1][capacity + 1];

        for (int row = 1; row <= items.size(); row++) {
            Item item = items.get(row - 1);

            for (int col = 0; col <= capacity; col++) {
                int excluded = dp[row - 1][col];
                if (col - item.weight < 0) {
                    dp[row][col] = excluded;
                } else {
                    int included = dp[row - 1][col - item.weight] + item.price;
                    if (excluded > included) {
                        dp[row][col] = excluded;
                    } else {
                        dp[row][col] = included;
                        takenItems[row][col] = true;
                    }
                }
            }
        }


        int bestValue = dp[items.size()][capacity];
        int weight = capacity;

        while (dp[items.size()][weight - 1] == bestValue) {
            weight--;
        }

        Set<Item> result = new TreeSet<>();

        int lastItem = items.size();

        while (lastItem > 0) {
            if (takenItems[lastItem][capacity]) {
                Item item = items.get(lastItem - 1);
                result.add(item);
                capacity -= item.weight;
            }
            lastItem--;
        }

        System.out.println("Total Weight: " + weight);
        System.out.println("Total Value: " + bestValue);

        System.out.println(result.stream()
                .map(item -> item.name)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
