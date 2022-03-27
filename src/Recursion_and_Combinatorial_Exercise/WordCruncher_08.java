package Recursion_and_Combinatorial_Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher_08 {
    public static List<String> words;
    public static String target;
    public static List<String> combined = new ArrayList<>();

    public static Map<Integer, List<String>> idxAndSubstr = new HashMap<>();
    public static Map<String, Integer> occurrences = new HashMap<>();

    public static Set<String> out = new TreeSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        words = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());
        target = scanner.nextLine();


        words.removeIf(w -> !target.contains(w));

        for (String substr : words) {
            occurrences.putIfAbsent(substr, 0);
            occurrences.put(substr, occurrences.get(substr) + 1);
            int index = target.indexOf(substr);
            while (index != -1) {
                idxAndSubstr.putIfAbsent(index, new ArrayList<>());
                idxAndSubstr.get(index).add(substr);
                index = target.indexOf(substr, index + 1);
            }
        }

        permute(0);
        out.forEach(System.out::println);

    }

    private static void permute(int index) {
        if (index == target.length()) {
            print();
        } else if (idxAndSubstr.containsKey(index)) {
            List<String> strings = idxAndSubstr.get(index);
            for (String str : strings) {
                if (occurrences.get(str) > 0) {
                    occurrences.put(str, occurrences.get(str) - 1);
                    combined.add(str);
                    permute(index + str.length());
                    combined.remove(combined.size() - 1);
                    occurrences.put(str, occurrences.get(str) + 1);
                }
            }
        }
    }

    private static void print() {
        String actual = String.join("", combined);
        if (actual.contains(target)) {
            out.add(String.join(" ", combined));
        }
    }
}
