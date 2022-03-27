package Recursion_and_Combinatorial_Exercise;

import java.util.Scanner;

public class CombWithoutRep_05 {
    public static int[] combinations;
    public static int n;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());
        int slots = Integer.parseInt(scanner.nextLine());

        combinations = new int[slots];
        comb(0, 1);
    }

    private static void comb(int index, int start) {
        if (index == combinations.length) {
            print();
        } else {
            for (int i = start; i <= n; i++) {
                combinations[index] = i;
                comb(index + 1, i + 1);
            }
        }
    }

    private static void print() {
        for (int i : combinations) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
