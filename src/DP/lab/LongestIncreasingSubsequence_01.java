package DP.lab;

import java.util.*;

public class LongestIncreasingSubsequence_01 {

    public static int[] sequence;
    public static int[] dpLen;
    public static int[] prev;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sequence = readArray(scanner.nextLine());

        dpLen = new int[sequence.length];
        prev = new int[sequence.length];

        Arrays.fill(prev, -1);

        int maxLength = 0;
        int maxIndex = -1;

        for (int i = 0; i < sequence.length; i++) {
            int bestLength = 1;
            int bestIndex = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (sequence[j] < sequence[i] && dpLen[j] + 1 >= bestLength) {
                    bestLength = dpLen[j] + 1;
                    bestIndex = j;
                }
            }

            prev[i] = bestIndex;
            dpLen[i] = bestLength;

            if (maxLength < bestLength) {
                maxLength = bestLength;
                maxIndex = i;
            }
        }

        List<Integer> LIS = getSequence(maxIndex);

        for (int i = LIS.size() - 1; i >= 0; i--) {
            System.out.print(LIS.get(i) + " ");
        }

    }

    private static List<Integer> getSequence(int maxIndex) {
        List<Integer> path = new ArrayList<>();
        int index = maxIndex;

        while (index != -1) {
            path.add(sequence[index]);
            index = prev[index];
        }

        return path;
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
