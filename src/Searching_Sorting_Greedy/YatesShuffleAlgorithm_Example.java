package Searching_Sorting_Greedy;


import java.util.Arrays;
import java.util.Random;

public class YatesShuffleAlgorithm_Example {
    public static void main(String[] args) {

        int[] arr = {13, 15, 12, 24, 59};

        Arrays.sort(arr);

        int[] random = getAsRand(arr);
        for (int el : random) {
            System.out.print(el + " ");
        }

    }

    private static int[] getAsRand(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int r = i + random.nextInt(arr.length - i);
            swap(arr, i, r);
        }

        return arr;
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
