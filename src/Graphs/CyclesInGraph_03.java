package Graphs;

import java.util.*;

public class CyclesInGraph_03 {

    public static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String source = null;

        String line;
        while (!"End".equals(line = scanner.nextLine())) {
            String[] relations = line.split("-");

            if (source == null) {
                source = relations[0];
            }

            graph.putIfAbsent(relations[0], new ArrayList<>());
            graph.get(relations[0]).add(relations[1]);
        }

        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        String output = "";

        try {
            for (String node : graph.keySet()) {
                if (!visited.contains(node)) {
                    dfs(node, visited, cycles);
                   output = "Acyclic: Yes";
                }
            }
        } catch (IllegalStateException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    private static void dfs(String source, Set<String> visited, Set<String> cycles) {
        if (cycles.contains(source)) {
            throw new IllegalStateException("Acyclic: No");
        }

        if (visited.contains(source)) {
            return;
        }

        visited.add(source);
        cycles.add(source);

        List<String> children = graph.get(source);
        if (children == null) {
            return;
        }

        for (String child : children) {
            dfs(child, visited, cycles);
        }

        cycles.remove(source);
    }
}
