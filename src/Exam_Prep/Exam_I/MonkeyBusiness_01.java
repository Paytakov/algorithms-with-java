package Exam_Prep.Exam_I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MonkeyBusiness_01 {

    public static int n;
    public static int solutions = 0;
    public static int[] elements;
    public static int[] combinations;
    public static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var reader =  new BufferedReader(new InputStreamReader(System.in));

         n = Integer.parseInt(reader.readLine());
         elements = new int[n];

        for (int i = 0; i < n; i++) {
            elements[i] = i + 1;
        }

        combinations = new int[elements.length];

        comb(0);

        out.append("Total Solutions: ").append(solutions);
        System.out.println(out.toString());
    }

    private static void comb(int index) {
        if (index == n) {
            printSolution();
        } else {
            combinations[index] = elements[index];
            comb(index + 1);
            combinations[index] = -elements[index];
            comb(index + 1);
        }
    }

    private static void printSolution() {
        int sum = Arrays.stream(combinations).sum();
        if (sum == 0) {
            solutions++;
            for (int value : combinations) {
                String numberAsStr = value > 0 ? "+" + value : String.valueOf(value);
                out.append(numberAsStr).append(" ");
            }
            out.append(System.lineSeparator());
        }
    }
}
