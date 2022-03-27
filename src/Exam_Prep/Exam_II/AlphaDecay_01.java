package Exam_Prep.Exam_II;

import java.util.Arrays;
import java.util.Scanner;

public class AlphaDecay_01 {

    public static int[] nucleus;
    public static int[] variations;
    public static boolean[] used;
    public static int k;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         nucleus = readArray(scanner.nextLine());
         k = Integer.parseInt(scanner.nextLine());

         variations = new int[k];
         used = new boolean[nucleus.length];

         observe(0);
    }

    private static void observe(int index) {
        if (index == k) {
            printSolution();
            return;
        }

        for (int i = 0; i < nucleus.length; i++) {
            if (!used[i]) {
                used[i] = true;
                variations[index] = nucleus[i];
                observe(index + 1);
                used[i] = false;
            }
        }

    }

    private static void printSolution() {
        for (int el : variations) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
