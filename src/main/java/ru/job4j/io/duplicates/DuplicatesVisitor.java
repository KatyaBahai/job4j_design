package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<String>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
            if (map.containsKey(fileProperty)) {
                map.get(fileProperty).add(file.toAbsolutePath().toString());
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(file.toAbsolutePath().toString());
                map.put(fileProperty, list);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("%s - %d%n", entry.getKey().getName(), entry.getKey().getSize());
                entry.getValue().forEach(System.out::println);
            }
        }
    }
}