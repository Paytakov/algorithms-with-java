package Recursion_and_Backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum_01 {
    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);

        int[] array = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = getRecursiveSum(array ,array.length - 1);
        System.out.println(sum);

    }

    private static int getRecursiveSum(int[] arr, int index) {
        if (index < 0) {
            return 0;
        }

        return arr[index] + getRecursiveSum(arr, index - 1);
    }
}
