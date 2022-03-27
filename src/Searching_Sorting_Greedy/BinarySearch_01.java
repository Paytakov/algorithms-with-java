package Searching_Sorting_Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = readArray(scanner.nextLine());

        int key = Integer.parseInt(scanner.nextLine());

        System.out.println(indexOf(arr, key));
    }

    private static int indexOf(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (end >= start) {
            int mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
