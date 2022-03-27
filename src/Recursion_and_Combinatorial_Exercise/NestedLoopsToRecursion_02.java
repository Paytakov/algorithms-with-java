package Recursion_and_Combinatorial_Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopsToRecursion_02 {
    public static int limit;
    public static int[] array;
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        limit = Integer.parseInt(reader.readLine());

        array = new int[limit];
        permute(0);

    }

    private static void permute(int index) {
        if (index == array.length) {
            print();
        } else {
            for (int i = 1; i <= limit; i++) {
                array[index] = i;
                permute(index + 1);
            }
        }
    }

    private static void print() {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
