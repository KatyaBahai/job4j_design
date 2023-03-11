package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> linesList = new ArrayList<>();
            reader.lines().forEach(linesList::add);
            for (String line : linesList) {
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }
                String[] temp = line.split("=", 2);
                if (line.contains("=") && !temp[0].isEmpty() && !temp[1].isEmpty()) {
                    values.put(temp[0], temp[1]);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String value = null;
        if (!values.isEmpty()) {
            value = values.getOrDefault(key, "No such key found");
        }
        return value;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}