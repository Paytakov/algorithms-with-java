package Recursion_and_Combinatorial_Exercise;

import java.util.*;

public class SchoolTeams_09 {
    public static String[] girls;
    public static String[] boys;

    public static int girlsCount = 3;
    public static int boysCount = 2;

    public static String[] girlsCombination = new String[girlsCount];
    public static String[] boysCombination = new String[boysCount];

    public static List<String> allGirls = new ArrayList<>();
    public static List<String> allBoys = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        girls = scanner.nextLine().split(", ");
        boys = scanner.nextLine().split(", ");

        combGirls(0, 0);
        combBoys(0, 0);

        for (String girls : allGirls) {
            for (String boys : allBoys) {
                System.out.println(girls + ", " + boys);
            }
        }

    }

    private static void combBoys(int index, int start) {
        if (index == boysCombination.length) {
            allBoys.add(String.join(", ", boysCombination));
        } else {
            for (int i = start; i < boys.length; i++) {
                boysCombination[index] = boys[i];
                combBoys(index + 1, i + 1);
            }
        }
    }

    private static void combGirls(int index, int start) {
        if (index == girlsCombination.length) {
            allGirls.add(String.join(", ", girlsCombination));
        } else {
            for (int i = start; i < girls.length; i++) {
                girlsCombination[index] = girls[i];
                combGirls(index + 1, i + 1);
            }
        }
    }
}

