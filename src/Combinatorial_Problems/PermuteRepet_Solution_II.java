package Combinatorial_Problems;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PermuteRepet_Solution_II {
    public static int[] elements;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(elements);

        permute(elements, 0, elements.length - 1);
    }

    private static void permute(int[] elements, int start, int end) {
        print(elements);
        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (elements[left] != elements[right]) {
                    swap(elements, left, right);
                    permute(elements, left + 1, end);
                }
            }
                int firstElement = elements[left];
                for (int i = left; i <= end - 1; i++) {
                    elements[i] = elements[i + 1];
                }
                elements[end] = firstElement;
        }
    }

    private static void swap(int[] elements, int first, int second) {
        int temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }

    private static void print(int[] elements) {
        System.out.println(Arrays.stream(elements)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
