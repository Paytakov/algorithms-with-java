package Exam_Prem_II.ex_2;

import java.util.*;

public class VampireLabyrinth_01 {

    public static int[][] graph;
    public static int[] distances;
    public static int[] prevNodes;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        int[] sourceAndDest = readArray(scanner.nextLine());

        int start = sourceAndDest[0];
        int end = sourceAndDest[1];

        graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] relations = readArray(scanner.nextLine());

            int from = relations[0];
            int to = relations[1];
            int count = relations[2];

            graph[from][to] = count;
            graph[to][from] = count;
        }

        distances = new int[graph.length];
        prevNodes = new int[graph.length];
        visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prevNodes, -1);

        distances[start] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparing(node -> distances[node])
        );

        queue.offer(start);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;

            int[] children = graph[minNode];
            for (int i = 0; i < children.length; i++) {
                if (children[i] != 0 && !visited[i]) {
                    queue.offer(i);

                    int newCount = graph[minNode][i] + distances[minNode];
                    if (newCount < distances[i]) {
                        distances[i] = newCount;
                        prevNodes[i] = minNode;
                    }
                }
            }
        }

        Deque<Integer> path = new ArrayDeque<>();
        path.push(end);

        int prev = prevNodes[end];
        while (prev != -1) {
            path.push(prev);
            prev = prevNodes[prev];
        }

        StringBuilder output = new StringBuilder();
        for (int el : path) {
            output.append(el).append(" ");
        }
        output.append(System.lineSeparator());
        output.append(distances[end]);

        System.out.println(output);

    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
