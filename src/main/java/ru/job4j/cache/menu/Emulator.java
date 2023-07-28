package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;
import java.util.Scanner;

public class Emulator {
    private static final String DIRECTORY = "C:\\projects\\job4j_design\\data\\cacheTask\\";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractCache<String, String> cache = new DirFileCache(DIRECTORY);
        start(scanner, cache);
    }

    private static void start(Scanner scanner, AbstractCache<String, String> cache) {
        boolean run = true;
        while (run) {
            System.out.printf("Menu: %n"
                    + "1) Press 1 if you would like to load the contents of a file into a cache.%n"
                    + "2) Press 2 if you would like to get the contents of a file from a cache.%n"
                    + "3) Press any other number to exit the program.%n%n");
            int choice = Integer.parseInt(scanner.nextLine());
            if (1 == choice) {
                cache.get(chooseDirectory(scanner));
                System.out.println("The contents of the file are in the cache.");
            } else if (2 == choice) {
                System.out.println(cache.get(chooseDirectory(scanner)));
            } else {
                run = false;
                System.out.println("The program is terminated.");
            }
    }
    }

    private static String chooseDirectory(Scanner scanner) {
        System.out.println("Please, enter the desired cache directory.");
        return scanner.nextLine();
    }
}
