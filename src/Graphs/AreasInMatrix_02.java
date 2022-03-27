package Graphs;

import java.util.*;

public class AreasInMatrix_02 {

    public static class Edge {
        int[] source;
        int[] dest;

        Edge(int sRow, int sCol) {
            this.source = new int[]{sRow, sCol};
        }

        public void setDest(int row, int col) {
            this.dest = new int[] {row, col};
        }
    }

    public static List<Edge> graph = new ArrayList<>();

    public static char[][] matrix;
    public static boolean[][] visited;
    public static boolean[] visitedNode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][];
        visited = new boolean[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            visited[i] = new boolean[matrix[i].length];
        }


        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]) {
                    dfs(row, col, matrix[row][col]);
                }
            }
        }

        visitedNode = new boolean[graph.size()];

        Map<Character, Integer> areas = new TreeMap<>();

        for (int startNode = 0; startNode < graph.size(); startNode++) {
            if (!visitedNode[startNode]) {
                Edge edge = graph.get(startNode);
                char key = AreasInMatrix_02.matrix[edge.source[0]][edge.source[1]];
                areas.putIfAbsent(key, 0);
                areas.put(key, areas.get(key) + 1);
                bfs(startNode);
            }
        }

        int areasCount = areas.values().stream()
                .mapToInt(e -> e)
                .sum();

        System.out.println("Areas: " + areasCount);

        areas.entrySet()
                .forEach((entry -> System.out.println("Letter '" + entry.getKey() + "' -> " + entry.getValue())));
    }

    private static void bfs(int source) {
        Deque<Integer> queue = new ArrayDeque<>();

        visitedNode[source] = true;
        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            Edge edge = graph.get(node);
            if (edge.dest != null) {
                visitedNode[node + 1] = true;
                queue.offer(node + 1);
            }
        }
    }

    private static void dfs(int row, int col, char areaSymbol) {
        visited[row][col] = true;

        Edge edge = new Edge(row, col);
        graph.add(edge);

        if (isInBounds(row, col + 1) && !visited[row][col + 1] && matrix[row][col + 1] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row, col + 1);
            dfs(row, col + 1, areaSymbol);
        }
        if (isInBounds(row, col - 1) && !visited[row][col - 1] && matrix[row][col - 1] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row, col - 1);
            dfs(row, col - 1, areaSymbol);
        }
        if (isInBounds(row + 1, col) && !visited[row + 1][col] && matrix[row + 1][col] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row + 1, col);
            dfs(row + 1, col, areaSymbol);
        }
        if (isInBounds(row - 1, col) && !visited[row - 1][col] && matrix[row - 1][col] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row - 1, col);
            dfs(row - 1, col, areaSymbol);
        }
    }

    private static boolean isInBounds(int row, int col) {
        return !isOutOfBounds(row, col);
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private static void readMatrix(int rows, String line) {
        for (int i = 0; i < rows; i++) {
            matrix[i] = line.toCharArray();
        }
    }
}
