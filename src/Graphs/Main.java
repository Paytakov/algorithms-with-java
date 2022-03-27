package Graphs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Character> list = new ArrayList<>();
        list.add('A');
        list.add('B');
        list.add('C');

        for (int i = 0; i < list.size(); i++) {
            char curr = list.get(i);
            list.remove(curr);
            list.add(curr);

            int g = 5;
        }
    }
}
