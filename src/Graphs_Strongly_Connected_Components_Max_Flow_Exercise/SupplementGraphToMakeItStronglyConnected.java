package Graphs_Strongly_Connected_Components_Max_Flow_Exercise;

import java.util.*;

public class SupplementGraphToMakeItStronglyConnected {

    public static List<List<Integer>> components = new ArrayList<>();
    public static boolean[] visited;
    public static Deque<Integer> stack = new ArrayDeque<>();

    public static boolean[][] graph;
    public static boolean[][] reversedGraph;

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        graph = new boolean[nodes][nodes];
        reversedGraph = new boolean[nodes][nodes];
        visited = new boolean[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split(" -> "))
                    .mapToInt(Integer::parseInt).toArray();

            int source = tokens[0];
            int destination = tokens[1];

            graph[source][destination] = true;
            reversedGraph[destination][source] = true;
        }

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                components.add(new ArrayList<>());
                reversedDfs(node);
            }
        }

        boolean[][] DAG = new boolean[components.size()][components.size()];

        for (int i = 0; i < components.size(); i++) {
            List<Integer> subGraph = components.get(i);
            for (int element : subGraph) {
                for (int j = 0; j < nodes; j++) {
                    if (graph[element][j] && j != element) {
                        for (int inner = 0; inner < components.size(); inner++) {
                            if (components.get(inner).contains(j) && i != inner) {
                                DAG[i][inner] = true;
                            }
                        }
                    }
                }
            }
        }


        // No incoming edges

        int zeroIncomingDegree = 0;
        for (int col = 0; col < DAG.length; col++) {
            boolean hasEdge = false;
            for (int row = 0; row < DAG[col].length; row++) {
                hasEdge = DAG[row][col];
                if (hasEdge) {
                    break;
                }
            }
            if (!hasEdge) {
                zeroIncomingDegree++;
            }
        }

        // No outgoing edges

        int zeroOutgoingDegree = 0;
        for (int row = 0; row < DAG.length; row++) {
            boolean hasEdge = false;
            for (int col = 0; col < DAG[row].length; col++) {
                hasEdge = DAG[row][col];
                if (hasEdge) {
                    break;
                }
            }
            if (!hasEdge) {
                zeroOutgoingDegree++;
            }
        }

        int neededEdges =  Math.max(zeroIncomingDegree, zeroOutgoingDegree);

        System.out.println("New edges needed: " + neededEdges);
    }

    private static void reversedDfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            components.get(components.size() - 1).add(node);

            for (int i = 0; i < reversedGraph[node].length; i++) {
                if (reversedGraph[node][i]) {
                    reversedDfs(i);
                }
            }
        }
    }

    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i]) {
                    dfs(i);
                }
            }

            stack.push(node);
        }
    }
}
