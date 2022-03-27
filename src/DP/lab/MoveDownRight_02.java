package DP.lab;

import java.util.*;

public class MoveDownRight_02 {

    public static int[][] matrix;
    public static int[][] tableDP;
    public static List<String> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = readArray(scanner.nextLine());
        }

        tableDP = new int[rows][cols];
        tableDP[0][0] = matrix[0][0];

        for (int col = 1; col < cols; col++) {
            tableDP[0][col] = tableDP[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < rows; row++) {
            tableDP[row][0] = tableDP[row - 1][0] + matrix[row][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                tableDP[row][col] = Math.max(matrix[row][col] + tableDP[row - 1][col],
                        matrix[row][col] + tableDP[row][col - 1]);
            }
        }

        int row = rows - 1;
        int col = cols - 1;

        path.add(formatOutput(row, col));

        while (row > 0 || col > 0) {
            int topElement = -1;

            if (row > 0) {
                topElement = tableDP[row - 1][col];
            }

            int leftElement = -1;

            if (col > 0) {
                leftElement = tableDP[row][col - 1];
            }

            if (topElement > leftElement) {
                row--;
            } else {
                col--;
            }

            path.add(formatOutput(row, col));

        }

        Collections.reverse(path);

        System.out.println(String.join(" ", path));

    }

    private static String formatOutput(int row, int col) {
        return "[" + row + ", " + col + "]";
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void printMatrix() {
        for (int[] ints : tableDP) {
            for (int el : ints) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
