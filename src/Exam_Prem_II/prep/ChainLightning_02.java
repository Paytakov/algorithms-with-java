package Exam_Prem_II.prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightning_02 {

    public static class Edge implements Comparable<Edge>{
        public int from;
        public int to;
        public int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static List<Edge>[] graph;
    public static boolean[] visited;
    public static int[] hits;
    public static int mostDamaged = Integer.MIN_VALUE;

    public static Map<Integer, List<Integer>> forest = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int lightningsCount = Integer.parseInt(reader.readLine());

        graph = new ArrayList[nodes];
        visited = new boolean[nodes];
        hits = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] relations = readArray(reader.readLine());

            int from = relations[0];
            int to = relations[1];
            int distance = relations[2];

            Edge edge = new Edge(from, to, distance);

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }

            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }

            graph[from].add(edge);
            graph[to].add(edge);
        }

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                msf(i);
            }
        }

        for (int i = 0; i < lightningsCount; i++) {
            int[] tokens = readArray(reader.readLine());

            int parent = tokens[0];
            int damage = tokens[1];

            damageNodes(parent, parent, damage);
        }

        System.out.println(mostDamaged);
    }

    private static void damageNodes(int parent, int next, int damage) {
        if (damage < 1) {
            return;
        }
        hits[parent] += damage;
        if (mostDamaged < hits[parent]) {
            mostDamaged = hits[parent];
        }

        if (forest.get(parent) != null) {
            for (int child : forest.get(parent)) {
                if (child != next) {
                    damageNodes(child, parent, damage / 2);
                }
            }
        }
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void msf(int node) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        visitNodes(node, queue);

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();

            int sourceNode = minEdge.from;
            int destNode = minEdge.to;

            if (visited[sourceNode] && visited[destNode]) {
                continue;
            }

            forest.putIfAbsent(sourceNode, new ArrayList<>());
            forest.putIfAbsent(destNode, new ArrayList<>());

            forest.get(sourceNode).add(destNode);
            forest.get(destNode).add(sourceNode);

            if (!visited[sourceNode]) {
                visitNodes(sourceNode, queue);
            } else {
                visitNodes(destNode, queue);
            }
        }
    }

    private static void visitNodes(int node, PriorityQueue<Edge> queue) {
        visited[node] = true;

        if (graph[node] != null) {
            for (Edge edge : graph[node]) {
                int nextNode = node == edge.from ? edge.to : edge.from;
                if (!visited[nextNode]) {
                    queue.offer(edge);
                }
            }
        }
    }
}
