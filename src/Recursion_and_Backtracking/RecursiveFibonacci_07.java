package Recursion_and_Backtracking;


import java.util.Scanner;

public class RecursiveFibonacci_07 {
    public static long[] fibArray;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        fibArray = new long[n + 1];
        System.out.println(fib(n));


    }

    private static long fib(int n) {
        if (n <= 2) {
            return 1;
        }

        if (fibArray[n] != 0) {
            return fibArray[n];
        }

        return fibArray[n] = fib(n - 1) + fib(n - 2);
    }
}
