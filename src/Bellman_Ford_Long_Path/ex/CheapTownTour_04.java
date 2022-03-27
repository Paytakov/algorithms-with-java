package Bellman_Ford_Long_Path.ex;

import java.util.*;

public class CheapTownTour_04 {

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    public static class Edge implements Comparable<Edge>{
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int nodes = Integer.parseInt(scanner.nextLine());
         int edgesCount = Integer.parseInt(scanner.nextLine());

         List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = readArray(scanner.nextLine());

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to, weight);
            edges.add(edge);
        }

        Collections.sort(edges);

        int[] parents = new int[nodes];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int cost = 0;

        for (Edge edge : edges) {
            int source = edge.from;
            int dest = edge.to;
            int weight = edge.weight;

            int firstRoot = findRoot(source, parents);
            int secondRoot = findRoot(dest, parents);

            if (firstRoot != secondRoot) {
                parents[secondRoot] = firstRoot;
                cost += weight;
            }
        }

        System.out.println("Total cost: " + cost);

    }

    private static int findRoot(int node, int[] parents) {
        while (parents[node] != node) {
            node = parents[node];
        }

        return node;
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split(" - "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
