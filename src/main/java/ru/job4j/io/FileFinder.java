package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFinder {
    public static void main(String[] args) throws IOException {
        ArgsName argsNames = ArgsName.of(args);
        validateArgs(args, argsNames);
        List<Path> files = Search.search(Paths.get(argsNames.get("d")), f -> compareName(argsNames, f));
        files.forEach(f -> writeToFile(argsNames.get("o"), f.toString()));
    }

    private static boolean compareName(ArgsName argsNames, Path fileName) {
        boolean matches;
        Path shortFileName = fileName.getFileName();
        String modelName = argsNames.get("n");
        String type = argsNames.get("t");
        if ("regex".equals(type)) {
            Pattern pattern = Pattern.compile(modelName);
            Matcher matcher = pattern.matcher(shortFileName.toString());
            matches = matcher.matches();
       } else {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + modelName);
            matches = pathMatcher.matches(shortFileName);
        }
        return matches;
    }

    private static void writeToFile(String finalDest, String pathToWrite) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(finalDest, true))) {
            writer.println(pathToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validateArgs(String[] args, ArgsName argsNames) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder must have 4 parameters.\n "
                    + "d: root directory, n: name pattern to search for, "
                    + "t: 'name', 'mask' or 'regex', o: destination file");
        }
        Path path;
        try {
            path = Path.of(argsNames.get("d"));
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(argsNames.get("d"), "The d parameter is not a path");
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("The d parameter is not a directory");
        }
        if (argsNames.get("n").isEmpty()) {
            throw new IllegalArgumentException("The n parameter can't be empty");
        }
        String type = argsNames.get("t");
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("The type of search has to be 'mask','name' or 'regex'");
        }
        String pathString = argsNames.get("o");
        try {
            Path.of(pathString);
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(argsNames.get("o"), "The o parameter is not a path");
        }
        if (!pathString.endsWith(".txt") && pathString.charAt(0) != '.') {
            throw new IllegalArgumentException("The o parameter must be a valid file");
        }
    }


}
