package Bellman_Ford_Long_Path.lab;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class LongestPath_02 {

    public static int[][] graph;
    public static int[] distances;
    public static boolean[] visited;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = readArray(scanner.nextLine());

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph[from][to] = weight;
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        distances = new int[graph.length];
        Arrays.fill(distances, Integer.MIN_VALUE);
        distances[source] = 0;

        visited = new boolean[graph.length];

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        findLongestPath(sorted);

        System.out.println(distances[destination]);

    }

    private static void findLongestPath(ArrayDeque<Integer> sorted) {
        while (!sorted.isEmpty()) {
            int node = sorted.pop();

            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node][i];
                if (weight != 0) {
                    int newValue = distances[node] + weight;
                    if (newValue > distances[i]) {
                        distances[i] = newValue;
                    }
                }
            }
        }
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int i = 1; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }

        sorted.push(node);
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
