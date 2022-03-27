package Recursion_and_Combinatorial_Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cinema_07 {
    public static List<String> names;
    public static String[] permutes;
    public static boolean[] used;
    public static String[] combinations;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        names = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        permutes = new String[names.size()];

        String nameAndPlace;
        while (!"generate".equals(nameAndPlace = scanner.nextLine())) {
            String name = nameAndPlace.split(" - ")[0];
            int place = Integer.parseInt(nameAndPlace.split(" - ")[1]) - 1;

            permutes[place] = name;
            names.remove(name);
            // used[place] = true;
        }
        used = new boolean[names.size()];
        combinations = new String[names.size()];

        permute(0);
    }

    private static void permute(int index) {
        if (index == combinations.length) {
            print();
        } else {
            for (int i = 0; i < names.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    combinations[index] = names.get(i);
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        int index = 0;
        String[] out = new String[permutes.length];
        for (int i = 0; i < out.length; i++) {
            if (permutes[i] != null) {
                out[i] = permutes[i];
            } else {
                out[i] = combinations[index++];
            }
        }
        System.out.println(String.join(" ", out));
    }
}
