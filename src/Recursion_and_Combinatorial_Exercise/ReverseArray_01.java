package Recursion_and_Combinatorial_Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray_01 {
    public static int[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        array = readArray(scanner.nextLine());
        reverse(array.length - 1);
    }

    private static void reverse(int index) {
        if (index < 0) {
            return;
        }

        System.out.print(array[index] + " ");
        reverse(index - 1);
    }


    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
