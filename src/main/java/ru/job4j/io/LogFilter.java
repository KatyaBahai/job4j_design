package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        ArrayList<String> finalList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<String> list = new ArrayList<>();
            reader.lines().forEach(list::add);
            for (String line : list) {
                String[] strings = line.split(" ");
                if ("404".equals(strings[strings.length - 2])) {
                    finalList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String line : log) {
            System.out.println(line);
        }
    }
}
