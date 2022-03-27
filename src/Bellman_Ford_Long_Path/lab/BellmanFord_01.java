package Bellman_Ford_Long_Path.lab;

import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord_01 {

    public static int[][] graph;
    public static int[] distances;
    public static int[] prev;

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

        try {
            solution(source);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int node = prev[destination];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        Collections.reverse(path);

        System.out.println(path.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.println(distances[destination]);
    }

    private static void solution(int sourceNode) {
        prev = new int[graph.length];
        Arrays.fill(prev, -1);
        distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[sourceNode] = 0;

        for (int i = 1; i < graph.length - 1; i++) {
            for (int row = 1; row < graph.length; row++) {
                for (int col = 1; col < graph[row].length; col++) {
                    int weight = graph[row][col];
                    if (weight != 0) {
                        int source = row;
                        int dest = col;
                        if (distances[source] != Integer.MAX_VALUE) {
                            int newValue = distances[source] + weight;
                            if (newValue < distances[dest]) {
                                distances[dest] = newValue;
                                prev[dest] = source;
                            }
                        }
                    }
                }
            }
        }

        for (int row = 1; row < graph.length; row++) {
            for (int col = 1; col < graph[row].length; col++) {
                int weight = graph[row][col];
                if (weight != 0) {
                    int source = row;
                    int dest = col;
                    if (distances[source] != Integer.MAX_VALUE) {
                        int newValue = distances[source] + weight;
                        if (newValue < distances[dest]) {
                            throw new IllegalStateException("Negative Cycle Detected");
                        }
                    }
                }
            }
        }
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
