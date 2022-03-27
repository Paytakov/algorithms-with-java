package Recursion_and_Backtracking;

import java.util.Scanner;

public class RecursiveDrawing_02 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int n = Integer.parseInt(scanner.nextLine());
         printFigure(n);
    }

    private static void printFigure(int n) {
        if (n == 0) {
            return;
        }

        action(n, "*");
        printFigure(n - 1);
        action(n, "#");
    }

    private static void action(int n, String symbol) {
        for (int i = 0; i < n; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }
}
