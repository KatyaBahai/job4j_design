package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.SimpleMenuPrinter;

public class ShowMenu implements UserAction {
    @Override
    public String name() {
        return "Show the menu with the elements";
    }

    @Override
    public boolean execute(Menu menu) {
        MenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        return true;
    }
}