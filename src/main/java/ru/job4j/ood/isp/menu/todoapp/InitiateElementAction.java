package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.Menu;

import java.util.Optional;
import java.util.Scanner;

public class InitiateElementAction implements UserAction {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String name() {
        return "Do the action of the selected element";
    }

    @Override
    public boolean execute(Menu menu) {
        System.out.println("Name the element whose action you would like to execute");
        String element = scanner.nextLine();
        Optional<Menu.MenuItemInfo> menuItemInfoOpt = menu.select(element);
        if (menuItemInfoOpt.isEmpty()) {
            System.out.println("There's no such an element in the list of elements. Please, try again.");
        } else {
            Menu.MenuItemInfo menuItemInfo = menuItemInfoOpt.get();
            menuItemInfo.getActionDelegate().delegate();
        }
        return true;
    }
}