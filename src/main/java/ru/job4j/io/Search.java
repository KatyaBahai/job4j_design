package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        paramValidate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void paramValidate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder must 2 parameters. Use ROOT_FOLDER");
        }
        try {
            Paths.get(args[0]);
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(args[0], "The 1 parameter in Root Folder is not a path.");
        }
        if (args[1].charAt(0) != '.') {
            throw new IllegalArgumentException("The 2 parameter should be a file extension. Use ROOT-FOLDER");
        }
    }
}