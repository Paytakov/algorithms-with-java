package Bellman_Ford_Long_Path.ex;

import java.util.*;

public class CableNetwork_II {

    public static class Edge implements Comparable<Edge>{
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
    public static Set<Integer> spanningTree = new HashSet<>();
    public static int cost = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(edge);

            if (tokens.length == 4) {
                spanningTree.add(edge.startNode);
                spanningTree.add(edge.endNode);
            }
        }

        prim(budget);
        System.out.println("Budget used: " + cost);
    }

    private static void prim(int budget) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int node : spanningTree) {
            List<Edge> children = graph.get(node);
            edges.addAll(children);
        }

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();

            int nonTreeNode = -1;

            if (spanningTree.contains(minEdge.startNode) && !spanningTree.contains(minEdge.endNode)) {
                nonTreeNode = minEdge.endNode;
            }

            if (!spanningTree.contains(minEdge.startNode) && spanningTree.contains(minEdge.endNode)) {
                nonTreeNode = minEdge.startNode;
            }

            if (nonTreeNode == -1) {
                continue;
            }

            if (budget >= minEdge.weight) {
                budget -= minEdge.weight;
                cost += minEdge.weight;
            } else {
                break;
            }

            spanningTree.add(nonTreeNode);
            edges.addAll(graph.get(nonTreeNode));
        }
    }
}
