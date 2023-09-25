package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.Menu;

public class ExitProgram implements UserAction {
    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Menu menu) {
        System.out.println("=== Exiting Program ===");
        return false;
    }
}
