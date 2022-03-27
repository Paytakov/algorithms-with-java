package Exam_Prem_II.ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DataTransfer_02 {

    public static int[][] graph;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[] sourceAndDest = readArray(reader.readLine());

        int source = sourceAndDest[0];
        int destination = sourceAndDest[1];

        graph = new int[nodes][nodes];
        parents = new int[graph.length];
        Arrays.fill(parents, -1);

        for (int i = 0; i < edges; i++) {
            int[] tokens = readArray(reader.readLine());

            graph[tokens[0]][tokens[1]] = tokens[2];
        }

        int maxFlow = 0;

        while (bfs(source, destination)) {
            int node = destination;
            int flow = Integer.MAX_VALUE;
            while (node != source) {
                flow = Math.min(flow, graph[parents[node]][node]);
                node = parents[node];
            }

            maxFlow += flow;

            node = destination;
            while (node != source) {
                graph[parents[node]][node] -= flow;
                graph[node][parents[node]] += flow;
                node = parents[node];
            }
        }

        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int dest) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(parents, -1);

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] > 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    parents[i] = node;
                }
            }
        }

        return visited[dest];
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
