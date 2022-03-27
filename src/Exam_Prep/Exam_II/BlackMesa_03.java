package Exam_Prep.Exam_II;

import java.util.*;

public class BlackMesa_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            graph[scanner.nextInt()][scanner.nextInt()] = 1;
        }

        int source = scanner.nextInt();
        int destination = scanner.nextInt();

        boolean[] visited = new boolean[n + 1];

        ArrayList<Integer> path = new ArrayList<>();

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        boolean isNotFound = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            if (isNotFound) {
                path.add(node);
                isNotFound = node != destination;
            }
            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }

        StringBuilder out = new StringBuilder();

        for (int node : path) {
            out.append(node).append(" ");
        }

        out.append(System.lineSeparator());

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                out.append(i).append(" ");
            }
        }

        System.out.println(out.toString().trim());
    }
}
