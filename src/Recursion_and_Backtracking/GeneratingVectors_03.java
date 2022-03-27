package Recursion_and_Backtracking;

import java.util.Scanner;

public class GeneratingVectors_03 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int n = Integer.parseInt(scanner.nextLine());

         Integer[] memory = new Integer[n];

         generateVector(memory, 0);
    }

    private static void generateVector(Integer[] arr, int index) {
        if (index >= arr.length) {
            print(arr);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            arr[index] = i;
            generateVector(arr, index + 1);
        }

    }

    private static void print(Integer[] arr) {
        for (Integer el : arr) {
            System.out.print(el);
        }
        System.out.println();
    }
}
