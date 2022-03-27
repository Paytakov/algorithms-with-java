package Graphs;

import java.util.*;

public class DistanceBetweenVertices_01 {
    public static int[][] graph;
    public static Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int pairs = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][];


        for (int i = 1; i <= nodes; i++) {
            String[] edges = scanner.nextLine().split(":");
            indexMapper.put(Integer.parseInt(edges[0]), i);
            if (edges.length == 1) {
                graph[i] = new int[0];
            } else {
                graph[i] = readArray(edges[1], "\\s+");
            }
        }

        while (pairs-- > 0) {
            int[] relations = readArray(scanner.nextLine(), "-");
            int source = relations[0];
            int dest = relations[1];

            int[] prev = new int[graph.length];
            Arrays.fill(prev, -1);

            System.out.printf("{%d, %d} -> ", source, dest);

            bfs(indexMapper.get(source), indexMapper.get(dest), prev, graph);

            List<Integer> path = new ArrayList<>();

            int parent = prev[indexMapper.get(dest)];
            while (parent != -1) {
                path.add(parent);
                parent = prev[parent];
            }

            int size = path.isEmpty() ? -1 : path.size();

            System.out.println(size);

        }

        System.out.println();
    }

    private static void bfs(int source, int dest, int[] prev, int[][] graph) {
        boolean[] visited = new boolean[graph.length + 1];

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        visited[source] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (node == dest) {
                return;
            }
            for (int i = 0; i < graph[node].length; i++) {
                int child = indexMapper.get(graph[node][i]);
                if (!visited[child]) {
                    prev[child] = node;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
        prev[source] = -1;
    }

    private static int[] readArray(String line, String pattern) {
        return Arrays.stream(line.split(pattern))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
