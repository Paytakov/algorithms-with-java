package Bellman_Ford_Long_Path.ex;

import java.util.*;

public class BigTrip_06 {

    public static int[][] graph;
    public static int[] distances;
    public static boolean[] visited;
    public static int[] prev;
    public static int totalTripCost = 0;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int nodes = Integer.parseInt(scanner.nextLine());
         int edges = Integer.parseInt(scanner.nextLine());

         graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            int from = tokens[0];
            int to = tokens[1];
            int cost = tokens[2];

            graph[from][to] = cost;
        }
        
        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());
        
        distances = new int[graph.length];
        Arrays.fill(distances, Integer.MIN_VALUE);
        distances[source] = 0;

        prev = new int[graph.length];
        Arrays.fill(prev, -1);
        
        visited = new boolean[graph.length];

        Deque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        findBiggestTrip(sorted);

        List<Integer> path = new ArrayList<>();
        path.add(destination);

        int node = prev[destination];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        System.out.println(distances[destination]);

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static void findBiggestTrip(Deque<Integer> sorted) {
        while (!sorted.isEmpty()) {
            int node = sorted.pop();

            for (int i = 1; i < graph[node].length; i++) {
                int cost = graph[node][i];
                if (cost != 0) {
                    int newValue = distances[node] + cost;
                    if (newValue > distances[i]) {
                        distances[i] = newValue;
                        prev[i] = node;
                    }
                }
            }
        }
    }

    private static void topologicalSort(int node, Deque<Integer> sorted) {
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

}
