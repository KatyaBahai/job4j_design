package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.Menu;

public interface UserAction {
    String name();

    boolean execute(Menu menu);
}
