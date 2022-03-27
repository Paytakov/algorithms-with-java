package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Salaries_04 {

    public static List<List<Integer>> graph = new ArrayList<>();
    public static long[] salaries;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employees = Integer.parseInt(scanner.nextLine());

        salaries = new long[employees];
        visited = new boolean[employees];

        int[] managersCount = new int[employees];

        for (int i = 0; i < employees; i++) {
            graph.add(new ArrayList<>());
            String line = scanner.nextLine();

            for (int emp = 0; emp < line.length(); emp++) {
                char currSymbol = line.charAt(emp);
                if (currSymbol == 'Y') {
                    managersCount[emp]++;
                    graph.get(i).add(emp);
                }
            }
        }

        List<Integer> sources = new ArrayList<>();

        for (int emp = 0; emp < managersCount.length; emp++) {
            if (managersCount[emp] == 0) {
                sources.add(emp);
            }
        }

        for (int source : sources) {
            dfs(source);
        }

        long sum = Arrays.stream(salaries)
                .sum();

        System.out.println(sum);
    }

    private static void dfs(int source) {
        if (visited[source]) {
            return;
        }

        visited[source] = true;

        for (Integer child : graph.get(source)) {
            if (!visited[child]) {
                dfs(child);
                long salary = graph.get(child).stream()
                        .mapToLong(c -> salaries[c])
                        .sum();

                salaries[child] = salary == 0 ? 1 : salary;
            }
        }

        long salary = graph.get(source).stream()
                .mapToLong(n -> salaries[n])
                .sum();

        salaries[source] = salary == 0 ? 1 : salary;
    }
}
