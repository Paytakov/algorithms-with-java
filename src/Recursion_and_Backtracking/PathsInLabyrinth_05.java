package Recursion_and_Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathsInLabyrinth_05 {
    public static List<Character> path = new ArrayList<>();
    public static char[][] labyrinth;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        labyrinth = new char[rows][cols];
        readMatrix(scanner);

        findPath(0, 0, ' ');
       // printMatrix();

    }


    private static void findPath(int row, int col, char direction) {
        if (!isInBounds(row, col)) {
            return;
        }

        path.add(direction);

        if (isExit(row, col)) {
            printPath();
        } else if (!isVisited(row, col) && isFree(row, col)) {
            mark(row, col);
            findPath(row, col + 1, 'R');
            findPath(row, col - 1, 'L');
            findPath(row - 1, col, 'U');
            findPath(row + 1, col, 'D');
            unMark(row, col);
        }

        path.remove(path.size() - 1);
    }

    private static void unMark(int row, int col) {
        labyrinth[row][col] = '-';
    }

    private static void mark(int row, int col) {
        labyrinth[row][col] = 'V';
    }

    private static boolean isFree(int row, int col) {
        return labyrinth[row][col] == '-';
    }

    private static boolean isVisited(int row, int col) {
        return labyrinth[row][col] == 'V';
    }

    private static boolean isExit(int row, int col) {
        return labyrinth[row][col] == 'e';
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < labyrinth.length && col >= 0 && col < labyrinth[row].length;
    }


    private static void readMatrix(Scanner scanner) {
        for (int row = 0; row < labyrinth.length; row++) {
            labyrinth[row] = scanner.nextLine().toCharArray();
        }
    }

    private static void printPath() {
        for (int i = 1; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        System.out.println();
    }

    private static void printMatrix() {
        for (char[] chars : labyrinth) {
            for (char symbol : chars) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

}
