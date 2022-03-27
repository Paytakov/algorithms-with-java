package Searching_Sorting_Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Quicksort_03 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int[] arr = readArray(scanner.nextLine());

        sort(arr);

        for (int number : arr) {
            System.out.print(number + " ");
        }
    }

    private static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int pi = partition(arr, begin, end);

            quickSort(arr, begin, pi - 1);
            quickSort(arr, pi + 1, end);
        }
    }

    /* This method takes last element as pivot,
    places the pivot element at its correct
    position in sorted array, and places all
    smaller (smaller than pivot) to left of
    pivot and all greater elements to right
    of pivot */
    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1; // index of smaller element
        for (int j = begin; j < end; j++) {
            // if curr element is smaller than
            // or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);

        return i + 1;
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
