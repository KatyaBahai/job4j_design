package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String start = null;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] strings = line.split(" ");
                boolean error = "400".equals(strings[0]) || "500".equals(strings[0]);
                if (start == null && error) {
                    start = strings[1];
                    writer.printf("%s; ", strings[1]);
                } else if (start != null && !error) {
                    writer.printf("%s;%n", strings[1]);
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}