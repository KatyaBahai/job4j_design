package ru.job4j.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String[]> rowsList = new ArrayList<>();
        Path path = Paths.get(argsName.get("path")).toAbsolutePath();
        try (Scanner scanner = new Scanner(path).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] row = line.split(argsName.get("delimiter"));
                rowsList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> columnNumbers = findColumns(argsName, rowsList.get(0));
        StringJoiner finalJoiner = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        for (String[] row : rowsList) {
            StringJoiner stringJoiner = new StringJoiner(argsName.get("delimiter"));
            for (Integer index : columnNumbers) {
                    stringJoiner.add(row[index]);
            }
            finalJoiner.add(stringJoiner.toString());
        }
        String out = argsName.get("out");
        if (out.equals("stdout")) {
            System.out.println(finalJoiner);
        } else {
            try (PrintWriter writer = new PrintWriter(out)) {
                writer.print(finalJoiner);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Integer> findColumns(ArgsName argsName, String[] paramRow) {
        String[] columnFilter = argsName.get("filter").split(",");
        List<Integer> filterColumnNumber = new ArrayList<>();
        for (String s : columnFilter) {
            for (int j = 0; j < paramRow.length; j++) {
                if (s.equals(paramRow[j])) {
                    filterColumnNumber.add(j);
                }
            }
        }
        return filterColumnNumber;
    }

    public static void main(String[] args) throws IOException {
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
       if (!argsName.get("path").endsWith(".csv")) {
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
