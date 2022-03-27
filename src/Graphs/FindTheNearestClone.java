package Graphs;

import java.util.*;

public class FindTheNearestClone {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static Map<Integer, Integer> nodeAndClrId = new HashMap<>();
    public static boolean[] visited;
    public static int[] prev;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] tokens = readArray(scanner.nextLine());

        int nodes = tokens[0];
        int edges = tokens[1];

        for (int i = 0; i < edges; i++) {
            int[] relations = readArray(scanner.nextLine());

            graph.putIfAbsent(relations[0], new ArrayList<>());
            graph.get(relations[0]).add(relations[1]);

            graph.putIfAbsent(relations[1], new ArrayList<>());
            graph.get(relations[1]).add(relations[0]);
        }

        String[] colorIds = scanner.nextLine().split("\\s+");

        for (int i = 0; i < colorIds.length; i++) {
            nodeAndClrId.put(i + 1, Integer.parseInt(colorIds[i]));
        }

        int value = Integer.parseInt(scanner.nextLine());

        visited = new boolean[nodes + 1];
        prev = new int[nodes + 1];
        Arrays.fill(prev, -1);


        for (int node : nodeAndClrId.keySet()) {
             if (nodeAndClrId.get(node) == value) {
                 for (Integer child : graph.get(node)) {
                     if (nodeAndClrId.get(child) == value) {
                         System.out.println(1);
                         return;
                     }
                 }


            }
        }


    }

    private static void dfs(int node, int val) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int child : graph.get(node)) {
            if (nodeAndClrId.get(child) == val) {
                prev[child] = node;
                System.out.println(1);
                return;
            }
        }
    }

    private static int[] readArray(String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
