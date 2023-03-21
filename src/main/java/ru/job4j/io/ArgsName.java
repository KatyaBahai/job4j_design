package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String string : args) {
            String[] temp = string.split("=", 2);
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validateArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String string : args) {
            if (!string.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + string + "' does not start with a '-' character");
            }
            if (!string.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + string + "' does not contain an equal sign");
            }
            if (string.charAt(1) == '=') {
                throw new IllegalArgumentException("Error: This argument '" + string + "' does not contain a key");
            }
            String[] temp = string.split("=", 2);
            if (temp[1].isEmpty()) {
                throw new IllegalArgumentException(("Error: This argument '" + string + "' does not contain a value"));
            }
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

    }
}