package ru.job4j;

import ru.job4j.tree.PrisonModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prison implements PrisonModel {
    public Integer startTest() {
        List<Integer> boxes = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        Collections.shuffle(boxes);

        int successCount = 0;
        for (int inmate = 0; inmate < 100; inmate++) {
            int currentBox = inmate;
            for (int box = 0; box < 50; box++) {
                if (boxes.get(currentBox).equals(inmate)) {
                    successCount++;
                    break;
                } else {
                    currentBox = boxes.get(currentBox);
                }
            }
        }
        System.out.println(successCount);
        return successCount;
    }

}
