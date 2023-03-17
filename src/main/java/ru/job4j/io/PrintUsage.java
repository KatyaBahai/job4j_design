package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintUsage {

    public static void main(String[] args) throws IOException {

        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
             PrintWriter writer = new PrintWriter("data/trem.txt")) {
            stream.println("Из PrintStream в FileOutputStream");
            writer.println("Новое сообщение");
            writer.write("tra-la-la");
            File file = new File("data/trem.txt");
            System.out.println(file.createNewFile());
            System.out.println(file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printAttributes("Attributes.txt");

    }

    public static void printAttributes(String address) throws IOException {
        Path file = Path.of(address);
        Files.createFile(file);
        BasicFileAttributeView attrView = Files.getFileAttributeView(file, BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();
        System.out.println(attributes.creationTime());
        System.out.println(attrView.readAttributes().size());
        System.out.println(Files.size(file));


    }

}