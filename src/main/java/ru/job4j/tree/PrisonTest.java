package ru.job4j.tree;

import ru.job4j.Prison;
import ru.job4j.PrisonTwo;

public class PrisonTest {
    public static void main(String[] args) {
        int numberOfTries = 1000;
        Prison prison1 = new Prison();
      //  execute(prison1, numberOfTries);
        System.out.println("---------------------");
        PrisonTwo prison2 = new PrisonTwo();
        execute(prison2, numberOfTries);
    }

    public static void execute(PrisonModel prison, int numberOfTries) {
        int successExp = 0;
        for (int i = 0; i < numberOfTries; i++) {
            Integer succ = prison.startTest();
            if (succ == 100) {
                successExp++;
            }
        }
        System.out.println(successExp * 100.0 / numberOfTries + "%");
    }
}
