package Bellman_Ford_Long_Path.ex;

import java.util.*;
import java.util.stream.Collectors;

public class Undefined_05 {

    public static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.from = startNode;
            this.to = endNode;
            this.weight = weight;
        }
    }

    public static int[] distances;
    public static int[] prev;
    public static List<Edge> edgesList = new ArrayList<>();
    public static int cost = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = parseInput(scanner);
        int edges = parseInput(scanner);

        processInput(scanner, edges);

        int source = parseInput(scanner);
        int destination = parseInput(scanner);

        prev = new int[nodes + 1];
        Arrays.fill(prev, -1);
        distances = new int[nodes + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[source] = 0;

        boolean hasNegativeCycle = detectCycle(nodes);

        if (hasNegativeCycle) {
            System.out.println("Undefined");
        } else {
            List<Integer> path = getPath(destination);

            Collections.reverse(path);

            System.out.println(path.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));

            System.out.println(distances[destination]);
        }

    }

    private static List<Integer> getPath(int destination) {
        List<Integer> temp = new ArrayList<>();

        temp.add(destination);
        int node = prev[destination];

        while (node != -1) {
            temp.add(node);
            node = prev[node];
        }

        return temp;
    }

    private static boolean detectCycle(int nodes) {
        for (int i = 0; i < nodes; i++) {
            for (Edge edge : edgesList) {
                int from = edge.from;
                int to = edge.to;
                int weight = edge.weight;

                int newValue = distances[from] + weight;

                if (distances[from] != Integer.MAX_VALUE && distances[to] > newValue) {
                    distances[to] = newValue;
                    prev[to] = from;
                }
            }
        }

        for (Edge edge : edgesList) {
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;

            int newValue = distances[from] + weight;

            if (distances[from] != Integer.MAX_VALUE && distances[to] > newValue) {
                return true;
            }
        }

        return false;
    }

    private static void processInput(Scanner scanner, int edges) {
        for (int i = 0; i < edges; i++) {
            int[] tokens = readArray(scanner.nextLine());

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            edgesList.add(new Edge(from, to, weight));
        }
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int parseInput(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }
}
