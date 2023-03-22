package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
                for (Path path : sources) {
                    zip.putNextEntry((new ZipEntry(path.toString())));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(path)))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

  /*  public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target)))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */

    private static void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Root folder must have 3 parameters. Use ROOT_FOLDER");
        }
        ArgsName argsName = ArgsName.of(args);
        Path path;
        try {
            path = Paths.get(argsName.get("d"));
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(argsName.get("d"), "The 1 parameter in Root Folder is not a path.");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("The source path doesn't exist.");
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("The source path is not a directory.");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The target path is not a zip.");
        }
        if (argsName.get("e").length() < 2 || argsName.get("e").charAt(0) != '.') {
            throw new IllegalArgumentException("The 'Exclude' parameter should be a file extension");
        }
    }

    public static List<Path> filter(ArgsName argsName) throws IOException {
        return Search.search(Path.of(argsName.get("d")),
                p -> p.toFile().getName().endsWith(argsName.get("e")));
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.packFiles(filter(argsName), Path.of(argsName.get("o")));

    }
}