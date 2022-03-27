package Recursion_and_Backtracking;

import java.util.Scanner;

public class RecursiveFactorial_04 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int n = Integer.parseInt(scanner.nextLine());

        long fact = getFactorial(n);
        System.out.println(fact);
    }

    private static long getFactorial(int n) {
        if (n == 1) {
            return 1;
        }

        return n * getFactorial(n - 1);
    }
}
