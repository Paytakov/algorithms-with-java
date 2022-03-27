package Graphs;

import java.util.Arrays;
import java.util.Scanner;

public class TheMatrix_07 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int[] dimensions = readArray(scanner.nextLine());

         int rows = dimensions[0];
         int cols = dimensions[1];

         char[][] matrix = readMatrix(rows, cols, scanner);

         char fillChar = scanner.nextLine().charAt(0);

         int[] coordinates = readArray(scanner.nextLine());
         int rowIndex = coordinates[0];
         int colIndex = coordinates[1];

         char charToReplace = matrix[rowIndex][colIndex];
         replaceCharacters(fillChar,charToReplace, rowIndex, colIndex, matrix);

         printMatrix(matrix);
    }

    private static void replaceCharacters(char fillChar, char charToReplace, int row, int col, char[][] matrix) {
        if (isOutOfBounds(row, col, matrix) || matrix[row][col] != charToReplace) {
            return;
        }

        matrix[row][col] = fillChar;

        replaceCharacters(fillChar, charToReplace, row, col + 1, matrix);
        replaceCharacters(fillChar, charToReplace, row, col - 1, matrix);
        replaceCharacters(fillChar, charToReplace, row + 1, col, matrix);
        replaceCharacters(fillChar, charToReplace, row - 1, col, matrix);
    }

    private static boolean isOutOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char el : chars) {
                System.out.print(el + "");
            }
            System.out.println();
        }
    }

    private static char[][] readMatrix(int rows, int cols, Scanner scanner) {
        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = line[j].charAt(0);
            }
        }

        return matrix;
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
