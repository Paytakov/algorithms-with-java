package Graphs_Strongly_Connected_Components_Max_Flow_Exercise;

import java.util.*;

public class MaximumTasksAssignment_01 {

    public static boolean[][] graph;
    public static int[] parents;
    public static int source;
    public static int sink;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int people = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
         int tasks = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

         int nodes = people + tasks + 2;
         source = 0;
         sink = nodes - 1;

         parents = new int[nodes];

         graph = new boolean[nodes][nodes];

        for (int i = 0; i < people; i++) {
            graph[0][i + 1] = true;
        }

        for (int i = 0; i < tasks; i++) {
            graph[i + people + 1][sink] = true;
        }

        for (int person = 0; person < people; person++) {
            String line = scanner.nextLine();
            for (int task = 0; task < tasks; task++) {
                graph[person + 1][people + task + 1] = line.charAt(task) == 'Y';
            }
        }

        Arrays.fill(parents, -1);

        while (bfs()) {
            int currentNode = sink;

            while (currentNode != source) {
                graph[parents[currentNode]][currentNode] = false;
                graph[currentNode][parents[currentNode]] = true;
                currentNode = parents[currentNode];
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[graph.length];

        Set<String> result = new TreeSet<>();

        visited[sink] = true;
        queue.offer(sink);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] && !visited[child]) {
                    visited[child] = true;
                    queue.offer(child);

                    if (node != sink && child != sink
                    && node != source && child != source) {
                        result.add((char) (child - 1 + 'A') + "-" + (node - people));
                    }
                }
            }
        }

        System.out.println(String.join(System.lineSeparator(), result));
    }

    private static boolean bfs() {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
            }
        }

        return visited[sink];
    }
}
