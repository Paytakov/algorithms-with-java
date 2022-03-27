package Combinatorial_Problems;

import java.util.Scanner;

public class VariationsWithRep_04 {
    public static String[] elements;
    public static String[] variations;
    public static int slots;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split(" ");
        slots = Integer.parseInt(scanner.nextLine());

        variations = new String[slots];

        variations(0);
    }

    private static void variations(int index) {
        if (index == slots) {
            print(variations);
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            variations[index] = elements[i];
            variations(index + 1);
        }
    }

    private static void print(String[] variations) {
        System.out.println(String.join(" ", variations));
    }
}
