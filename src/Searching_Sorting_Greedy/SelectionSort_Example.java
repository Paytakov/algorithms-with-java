package Searching_Sorting_Greedy;

public class SelectionSort_Example {
    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 1};

        sort(arr);

        for (int el : arr) {
            System.out.print(el + " ");
        }
    }

    private static void sort(int[] arr) {
        for (int index = 0; index < arr.length; index++) {
            int min = index;
            for (int curr = index + 1; curr < arr.length; curr++) {
                if (arr[curr] < arr[min]) {
                    min = curr;
                }
            }
            swap(arr, index, min);
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
