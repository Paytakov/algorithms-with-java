package Recursion_and_Combinatorial_Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectedAreasInMatrix_06 {
    public static char[][] matrix;
    public static List<int[]> areas;
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int row = Integer.parseInt(scanner.nextLine());
         int col = Integer.parseInt(scanner.nextLine());

         matrix = new char[row][col];
         readMatrix(scanner, row);

         areas = new ArrayList<>();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == '-') {
                    areas.add(new int[] {r, c, 0});
                    findAreas(r, c);
                }
            }
        }

        System.out.println("Total areas found: " + areas.size());

        AtomicInteger counter = new AtomicInteger(1);

        areas.stream()
                .sorted((f, s) -> Integer.compare(s[2], f[2]))
                .forEach(a -> System.out.printf("Area #%d at (%d, %d), size: %d%n",
                        counter.getAndIncrement(), a[0], a[1], a[2]));
    }

    private static void findAreas(int row, int col) {
        if (isOutOfBounds(row, col) || isNotTraversal(row, col)) {
            return;
        }

        matrix[row][col] = 'V';

        areas.get(areas.size() - 1)[2]++;

        findAreas(row, col + 1);
        findAreas(row, col - 1);
        findAreas(row - 1, col);
        findAreas(row + 1, col);
    }

    private static boolean isNotTraversal(int row, int col) {
        return matrix[row][col] == 'V' || matrix[row][col] == '*';
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private static void readMatrix(Scanner scanner, int r) {
        for (int i = 0; i < r; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }
    }
}
