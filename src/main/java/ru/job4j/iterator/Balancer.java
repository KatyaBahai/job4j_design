package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        int size = nodes.size();
        while (source.hasNext()) {
            if (index >= size) {
                index = 0;
            }
            nodes.get(index++).add(source.next());
        }
    }
}
