package Exam_Prem_II.ex_1;

import java.util.*;

public class FoodProgramme_01 {

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
            int[] tokens = readArray(scanner.nextLine());

            int source = tokens[0];
            int dest = tokens[1];
            int time = tokens[2];

            graph[source][dest] = time;
            graph[dest][source] = time;
        }

        prevNodes = new int[graph.length];
        distances = new int[graph.length];
        visited = new boolean[graph.length];

        Arrays.fill(prevNodes, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[start] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> distances[node]));

        queue.offer(start);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();


            visited[minNode] = true;

            int[] children = graph[minNode];

            for (int i = 0; i < children.length; i++) {
                if (children[i] != 0 && !visited[i]) {
                    queue.offer(i);

                    int newTime = distances[minNode] + graph[minNode][i];
                    if (newTime < distances[i]) {
                        distances[i] = newTime;
                        prevNodes[i] = minNode;
                    }
                }
            }
        }

        Deque<Integer> path = new ArrayDeque<>();

        int prev = prevNodes[end];
        while (prev != -1) {
            path.push(prev);
            prev = prevNodes[prev];
        }

        StringBuilder out = new StringBuilder();

        for (int el : path) {
            out.append(el).append(" ");
        }

        out.append(end);
        out.append(System.lineSeparator());
        out.append(distances[end]);

        System.out.println(out.toString());
    }


    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
