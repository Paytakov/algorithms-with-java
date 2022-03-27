package Searching_Sorting_Greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort_Example {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(15, 3, 1, 3, 42, 96, 69, 13, 42));

        int[] counts = new int[101];

        for (int number : numbers) {
            counts[number]++;
        }

        for (int number = 0; number <= 100; number++) {
            int count = counts[number];
            for (int i = 0; i < count; i++) {
                System.out.print(number + " ");
            }
        }
    }
}
