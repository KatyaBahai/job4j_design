package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream file = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int character;
            while ((character = file.read()) != -1) {
                text.append((char) character);
            }
           String[] strings = text.toString().split(System.lineSeparator());
            for (String number: strings) {
                if (Integer.parseInt(number) % 2 == 0) {
                    System.out.println(number);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
