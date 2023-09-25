package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;

import java.util.Scanner;

public class AddElementToRoot implements UserAction {
    Scanner scanner = new Scanner(System.in);
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    @Override
    public String name() {
        return "Add element to the root elements";
    }

    @Override
    public boolean execute(Menu menu) {
        System.out.println("Type the name of the element you wish to add to the root elements.");
        String name = scanner.nextLine();
        menu.add(Menu.ROOT, name, DEFAULT_ACTION);
        return true;
    }
}
