package Exam_Prep.Exam_I;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Molecules_03 {
    
    public static int[][] graph;
    
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

         int nodes = Integer.parseInt(scanner.nextLine());
         int edges = Integer.parseInt(scanner.nextLine());
         
         graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] relations = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[relations[0]][relations[1]] = 1;
        }

        int source = Integer.parseInt(scanner.nextLine());

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[nodes + 1];

        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    queue.offer(i);
                }
            }
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
