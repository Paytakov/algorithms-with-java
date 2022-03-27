package Combinatorial_Problems;

import java.util.Scanner;

public class CombWithoutRep_05 {
    public static String[] elements;
    public static String[] combinations;
    public static int slots;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split(" ");
        slots = Integer.parseInt(scanner.nextLine());

        combinations = new String[slots];

        comb(0, 0);
    }

    private static void comb(int index, int start) {
        if (index == combinations.length) {
            print(combinations);
        } else {
            for (int i = start; i < elements.length; i++) {
                combinations[index] = elements[i];
                // without repetition
               // comb(index + 1, i + 1);

                // with repetition
                comb(index + 1, i);
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
