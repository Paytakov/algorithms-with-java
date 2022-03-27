package Bellman_Ford_Long_Path.ex;

import java.util.*;
import java.util.stream.Collectors;

public class MostReliablePath_03 {

    public static int[][] graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        String[] path = scanner.nextLine().split("\\s+");
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        int sourceNode = Integer.parseInt(path[1]);
        int destinationNode = Integer.parseInt(path[3]);

        graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] relations = readAArray(scanner.nextLine());

            int from = relations[0];
            int to = relations[1];
            int percentage = relations[2];

            graph[from][to] = percentage;
            graph[to][from] = percentage;
        }

        double[] reliabilities = new double[nodes];
        boolean[] visited = new boolean[nodes];

        int[] prevNodes = new int[nodes];


        //Arrays.fill(reliabilities, Integer.MIN_VALUE);
        Arrays.fill(prevNodes, -1);

        reliabilities[sourceNode] = 100.00;

        PriorityQueue<Integer> queue = new PriorityQueue<>((f, s) -> Double.compare(reliabilities[s], reliabilities[f]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int mostReliableNode = queue.poll();

            visited[mostReliableNode] = true;

            for (int i = 0; i < graph[mostReliableNode].length; i++) {
                int reliability = graph[mostReliableNode][i];

                if (reliability != 0 && !visited[i]) {
                    double value = reliabilities[mostReliableNode] * reliability / 100;

                    if (value > reliabilities[i]) {
                        reliabilities[i] = value;
                        prevNodes[i] = mostReliableNode;
                    }
                    queue.offer(i);
                }
            }
        }

        System.out.printf("Most reliable path reliability: %.2f%%%n", reliabilities[destinationNode]);

        Deque<Integer> mostReliablePath = new ArrayDeque<>();

        mostReliablePath.push(destinationNode);

        int node = prevNodes[destinationNode];

        while (node != -1) {
            mostReliablePath.push(node);
            node = prevNodes[node];
        }

        System.out.println(mostReliablePath.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" -> ")));

    }

    private static int[] readAArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
