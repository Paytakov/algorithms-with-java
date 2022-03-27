package Recursion_and_Backtracking;

import java.util.HashSet;

public class QueensPuzzle_06 {
    static final int size = 8;
    static char[][] board = new char[size][size];
    static HashSet<Integer> attackedRows = new HashSet<>();
    static HashSet<Integer> attackedCols = new HashSet<>();

    public static void main(String[] args) {

        solve(0);

    }

    private static void solve(int row) {
        if (row == size) {
            printSolution();
            return;
        } else {
            for (int col = 0; col < size; col++) {
                if (canPlaceQueen(row, col)) {
                    markAttackedFields(row, col);
                    solve(row + 1);
                    unMarkAttackedFields(row, col);
                }
            }
        }
    }

    private static void printSolution() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == '1') {
                    System.out.print('*' + " ");
                } else {
                    System.out.print('-' + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void unMarkAttackedFields(int row, int col) {
        board[row][col] = '0';
        attackedRows.remove(row);
        attackedCols.remove(col);
    }

    private static void markAttackedFields(int row, int col) {
        board[row][col] = '1';
        attackedRows.add(row);
        attackedCols.add(col);
    }

    private static boolean canPlaceQueen(int row, int col) {
        if (attackedRows.contains(row)) {
            return false;
        }

        if (attackedCols.contains(col)) {
            return false;
        }

        // left-up diagonal
        for (int i = 1; i < size; i++) {
            int currRow = row - i;
            int currCol = col - i;

            if (!isInBounds(currRow, currCol)) {
                break;
            }
            // queen here
            if (board[currRow][currCol] == '1') {
                return false;
            }
        }
        // right-up diagonal
        for (int i = 1; i < size; i++) {
            int currRow = row - i;
            int currCol = col + i;

            if (!isInBounds(currRow, currCol)) {
                break;
            }
            // queen here
            if (board[currRow][currCol] == '1') {
                return false;
            }
        }

        // left-down diagonal
        for (int i = 1; i < size; i++) {
            int currRow = row + i;
            int currCol = col - i;

            if (!isInBounds(currRow, currCol)) {
                break;
            }
            // queen here
            if (board[currRow][currCol] == '1') {
                return false;
            }
        }

        // right-down diagonal
        for (int i = 1; i < size; i++) {
            int currRow = row + i;
            int currCol = col + i;

            if (!isInBounds(currRow, currCol)) {
                break;
            }
            // queen here
            if (board[currRow][currCol] == '1') {
                return false;
            }
        }

        return true;
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
