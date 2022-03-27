package DP.ex;

import java.util.Arrays;
import java.util.Scanner;

public class DividingPresents_03 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int[] presents = readArray(scanner.nextLine());

        int totalSum = Arrays.stream(presents).sum();

        int[] sums = new int[totalSum + 1];

        Arrays.fill(sums, -1);
        sums[0] = 0;

        for (int presIdx = 0; presIdx < presents.length; presIdx++) {
            for (int prevSumIdx = totalSum - presents[presIdx]; prevSumIdx >=0; prevSumIdx--) {
                int presentValue = presents[presIdx];

                if (sums[prevSumIdx] != -1 && sums[prevSumIdx + presentValue] == -1) {
                    sums[prevSumIdx + presentValue] = presIdx;
                }
            }
        }

        int half = totalSum / 2;

        for (int i = half; i >= 0; i--) {
            if (sums[i] == -1) {
                continue;
            }

            System.out.println("Difference: " + (totalSum - i - i));
            System.out.printf("Alan:%d Bob:%d%n", i, totalSum - i);
            System.out.print("Alan takes: ");

            while (i != 0) {
                System.out.print(presents[sums[i]] + " ");
                i -= presents[sums[i]];
            }

            System.out.println();
            System.out.println("Bob takes the rest.");
        }
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
