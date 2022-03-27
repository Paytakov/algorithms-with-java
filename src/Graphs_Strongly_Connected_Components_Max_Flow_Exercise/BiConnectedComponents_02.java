package Graphs_Strongly_Connected_Components_Max_Flow_Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BiConnectedComponents_02 {

    public static int[][] graph;
    public static int[] parents;
    public static int[] depths;
    public static int[] lowPoints;
    public static int[] reachableCount;
    public static List<Integer> points;

    public static List<List<Integer>> subGraph = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        graph = new int[nodes][nodes];
        parents = new int[nodes];
        depths = new int[nodes];
        lowPoints = new int[nodes];
        reachableCount = new int[nodes];

        Arrays.fill(parents, -1);

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            int source = tokens[0];
            int destination = tokens[1];

            graph[source][reachableCount[source]++] = destination;
            graph[destination][reachableCount[destination]++] = source;
        }

        discoverArticulationPoints(0, 0, new ArrayList<>());

        System.out.println("Number of bi-connected components: " + subGraph.size());
    }

    private static void discoverArticulationPoints(int node, int depth, List<Integer> subSet) {
        depths[node] = depth;
        lowPoints[node] = depth;

        for (int i = 0; i < reachableCount[node]; i++) {
            int child = graph[node][i];
            if (depths[child] == 0) {
                parents[child] = node;
                List<Integer> components = new ArrayList<>();
                discoverArticulationPoints(child, depth + 1, components);
                if (lowPoints[child] >= depths[node] || parents[child] == -1) {
                    components.add(node);
                    subGraph.add(components);
                } else {
                    subSet.addAll(components);
                }
                lowPoints[node] = Math.min(lowPoints[node], lowPoints[child]);
            } else if (parents[node] != child) {
                lowPoints[node] = Math.min(lowPoints[node], depths[child]);
            }
        }

        subSet.add(node);
    }
}
