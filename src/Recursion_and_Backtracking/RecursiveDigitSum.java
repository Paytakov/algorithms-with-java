package Recursion_and_Backtracking;

import java.util.Scanner;

class RecursiveDigitSum {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         String[] input = scanner.nextLine().split(" ");
         String n = input[0];
         int count = Integer.parseInt(input[1]);

        System.out.println(superDigit(n, count));
    }

    private static int superDigit(String number, int count) {
        if (number.length() == 1) {
            return Character.getNumericValue(number.charAt(0));
        } else {
            int sum = 0;
            for (int i = 0; i < number.length(); i++) {
                sum += Character.getNumericValue(number.charAt(i));
            }
            return superDigit(String.valueOf(sum * count), 1);
        }
    }
}
