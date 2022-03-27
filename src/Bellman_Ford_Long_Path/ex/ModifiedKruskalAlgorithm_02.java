package Bellman_Ford_Long_Path.ex;

import java.util.*;

public class ModifiedKruskalAlgorithm_02 {

    public static class Edge implements Comparable<Edge>{
        private int from;
        private int to;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.from = startNode;
            this.to = endNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
         int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            edgeList.add(new Edge(from, to, weight));
        }

        Collections.sort(edgeList);

        int[] parents = new int[nodes];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int weightCost = 0;

        for (Edge edge : edgeList) {
            int source = edge.from;
            int dest = edge.to;

            int firstRoot = findRoot(source, parents);
            int secondRoot = findRoot(dest, parents);

            if (firstRoot != secondRoot) {
                parents[firstRoot] = secondRoot;
                weightCost += edge.weight;
            }
        }

        System.out.println("Minimum spanning forest weight: " + weightCost);

    }

    private static int findRoot(int node, int[] parents) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}
