package Combinatorial_Problems;

import java.util.Scanner;

public class PermutationsWithoutRepetition_01 {
    public static String[] elements;
//    public static boolean[] used;
//    public static String[] permutes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
//         used = new boolean[elements.length];
//         permutes = new String[elements.length];


        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
        } else {
            permute(index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                swap(elements, index, i);
                permute(index + 1);
                swap(elements, index, i);
            }
        }

//            for (int i = 0; i < elements.length; i++) {
//                if (!used[i]) {
//                    used[i] = true;
//                    perm[index] = elements[i];
//                    permute(index + 1);
//                    used[i] = false;
//                }
//
//            }
    }

    private static void swap(String[] elements, int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }

    private static void print() {
        System.out.println(String.join(" ", elements));
    }
}
