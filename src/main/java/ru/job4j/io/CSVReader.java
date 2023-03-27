package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String[]> rowsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(argsName.get("path")).useDelimiter(" ")) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] row = line.split(";");
                rowsList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> columnNumbers = findColumns(argsName, rowsList.get(0));
        StringJoiner stringJoiner = new StringJoiner(";");
        for (String[] row : rowsList) {
            for (int i = 0; i < row.length; i++) {
                if (columnNumbers.contains(i)) {
                    stringJoiner.add(row[i]);
                }
            }
            stringJoiner.add("," + System.lineSeparator());
        }
        System.out.println(stringJoiner);
    }

    private static List<Integer> findColumns(ArgsName argsName, String[] paramRow) {
        List<String> columnFilter = Arrays.stream(argsName.get("filter").split(";")).toList();
        List<Integer> filterColumnNumber = new ArrayList<>();
        for (int i = 0; i < paramRow.length; i++) {
            if (columnFilter.contains(paramRow[i])) {
                filterColumnNumber.add(i);
            }
        }
        return filterColumnNumber;
    }

    public static void main(String[] args) {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    private static void validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder must have 3 parameters. Use ROOT_FOLDER");
        }
        ArgsName argsName = ArgsName.of(args);
        Path path;
        try {
            path = Paths.get(argsName.get("path"));
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(argsName.get("path"), "The path parameter in Root Folder is not a path.");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("The source path doesn't exist.");
        }
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("The path should have a .csv extension.");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("The filter for columns is empty.");
        }
        String out = argsName.get("out");
        if (!out.equals("stdout")) {
            try {
                Paths.get(argsName.get(out));
            } catch (InvalidPathException ex) {
                throw new InvalidPathException(out, "The target parameter in Root Folder is not a path or a console.");
            }
        }
    }
}
