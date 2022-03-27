package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CyclesInGraph_Class {

    public static class Edge {
        int source;
        int dest;

        Edge(int source, int dest) {
            this.source = source;
            this.dest = dest;
        }
    }

    public static class Graph {
        List<List<Integer>> adjList;

        Graph(List<Edge> edges, int n) {
            adjList = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                adjList.add(i, new ArrayList<>());
            }

            for (Edge edge : edges) {
                int src = edge.source;
                int dest = edge.dest;

                adjList.get(src).add(dest);
                adjList.get(dest).add(src);
            }
        }
    }

    public static boolean[] visited;
    public static void main(String[] args) {

        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 6), new Edge(0, 7),
                new Edge(1, 2), new Edge(1, 5), new Edge(2, 3),
                new Edge(2, 4), new Edge(7, 8), new Edge(7, 11),
                new Edge(8, 9), new Edge(8, 10), new Edge(10, 11)
                // edge (10, 11) introduces a cycle in the graph
        );

        int nodes = 12;

        Graph graph = new Graph(edges, nodes);

        visited = new boolean[nodes];

        if (dfs(graph, 0, -1)) {
            System.out.println("The graph contains a cycle");
        } else {
            System.out.println("The graph doesn't contain any cycle");
        }

    }

    private static boolean dfs(Graph graph, int source, int dest) {
        visited[source] = true;

        for (int child : graph.adjList.get(source)) {
            if (!visited[child]) {
                if (dfs(graph, child, source)) {
                    return true;
                }
            } else if (child != dest) {
                return true;
            }
        }

        return false;
        
    }
}
