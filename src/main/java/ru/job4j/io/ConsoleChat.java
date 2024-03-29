package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static List<String> log = new ArrayList<>();
    private Random random = new Random();
    private final String path;
    private final String botAnswers;


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Speak to me, and I will reply.");
        Scanner scanner = new Scanner(System.in);
        String newMessage = scanner.nextLine();
        log.add(newMessage);
        List<String> answers = readPhrases();
        while (!OUT.equals(newMessage)) {
            if (STOP.equals(newMessage)) {
                while (!CONTINUE.equals(newMessage)) {
                    newMessage = scanner.nextLine();
                    log.add(newMessage);
                }
            }
            int randomNumber = random.nextInt(answers.size());
            String botAnswer = answers.get(randomNumber);
            System.out.println(botAnswer);
            log.add(botAnswer);
            newMessage = scanner.nextLine();
            log.add(newMessage);
            }
        saveLog(log);
        }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
       try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
           reader.lines().forEach(list::add);
       } catch (IOException e) {
           e.printStackTrace();
       }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/botLog.txt", "data/botAnswers.txt");
        cc.run();
    }
}
