package DP.lab;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DavisStaircase {
    public static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int staircases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < staircases; i++) {
            int n = scanner.nextInt();
            System.out.println(stepPerms(n));
        }
    }

    private static int stepPerms(int n) {
       if (n < 0) {
           return 0;
       }

       if (n == 0) {
           return 1;
       }

       if (!map.containsKey(n)) {
           int count = stepPerms(n - 1) + stepPerms(n - 2) + stepPerms(n - 3);
           map.put(n, count);
       }

       return map.get(n);
    }

}

/*
 int[] array = new int[n];
        if (n == 1) {
            return 1;
        }
        else if(n == 2) {
            return 2;
        }
        else if(n == 3) {
            return 4;
        }
        array[0] = 1;
        array[1] = 2;
        array[2] = 4;
        for (int i = 3; i < n; i++) {
            array[i] = array[i-1] + array[i-2] + array[i-3];
        }
        return array[array.length-1];
    }
 */