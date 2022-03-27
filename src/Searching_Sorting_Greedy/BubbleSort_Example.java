package Searching_Sorting_Greedy;

public class BubbleSort_Example {
    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 2, 5, 6};

        sort(arr);

        for (int el : arr) {
            System.out.print(el + " ");
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
