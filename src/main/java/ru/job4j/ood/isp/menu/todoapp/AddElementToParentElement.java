package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;

import java.util.Scanner;

public class AddElementToParentElement implements UserAction {
    Scanner scanner = new Scanner(System.in);
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    @Override
    public String name() {
        return "Add an element to a parent element";
    }

    @Override
    public boolean execute(Menu menu) {
        System.out.println("Type the name of the parent element you wish to add your element into.");
        String parent = scanner.nextLine();
        System.out.printf("Type the name of the element you wish to add to the parent element %s.\n", parent);
        String name = scanner.nextLine();
        menu.add(parent, name, DEFAULT_ACTION);
        return true;
    }
}