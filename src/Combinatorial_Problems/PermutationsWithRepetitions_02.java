package Combinatorial_Problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PermutationsWithRepetitions_02 {
    public static String[] elements;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         elements = scanner.nextLine().split(" ");

         permute(0);

    }

    private static void permute(int index) {
        if (index == elements.length) {
            print(elements);
        } else {
            permute(index + 1);
            Set<String> swapped = new HashSet<>();
            swapped.add(elements[index]);
            for (int i = index + 1; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(elements, index, i);
                    permute(index + 1);
                    swap(elements, index, i);
                    swapped.add(elements[i]);
                }
            }
        }
    }

    private static void swap(String[] elements, int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }

    private static void print(String[] elements) {
        System.out.println(String.join(" ", elements));
    }
}
