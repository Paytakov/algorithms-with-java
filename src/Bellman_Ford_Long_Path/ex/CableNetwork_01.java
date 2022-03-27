package Bellman_Ford_Long_Path.ex;

import java.util.*;
import java.util.stream.Collectors;

public class CableNetwork_01 {

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
    public static int cost = 0;

    public static class Edge implements Comparable<Edge> {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        boolean[] visited = new boolean[nodes];

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);

            if (tokens.length == 4) {
                visited[from] = visited[to] = true;
            }
        }

        prim(visited, budget);
            System.out.println("Budget used: " + cost);
    }

    private static boolean prim(boolean[] visited, int budget) {
        PriorityQueue<Edge> edges = graph.values().stream()
                .flatMap(List::stream)
                .filter(e -> (visited[e.startNode] && !visited[e.endNode]) ||
                        (!visited[e.startNode] && visited[e.endNode]))
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();

            int sourceNode = minEdge.startNode;
            int destNode = minEdge.endNode;
            int weight = minEdge.weight;

            int value = -1;

            if (visited[sourceNode] && !visited[destNode]) {
                visited[destNode] = true;
                value = weight;
            } else if (!visited[sourceNode] && visited[destNode]) {
                visited[sourceNode] = true;
                value = weight;
            }

            edges.addAll(graph.values().stream()
                    .flatMap(List::stream)
                    .filter(e -> (visited[e.startNode] && !visited[e.endNode]) ||
                            (!visited[e.startNode] && visited[e.endNode]))
                    .collect(Collectors.toCollection(PriorityQueue::new)));

            if (value != -1 && budget - value > 0) {
                budget -= value;
                cost += value;
            } else if (budget - value < 0){
                return false;
            }
        }
        return true;
    }
}
