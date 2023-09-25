package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.SimpleMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    Scanner scanner = new Scanner(System.in);

    public void init(Menu menu, List<UserAction> userActionList) {
        boolean run = true;
        while (run) {
            showActions(userActionList);
            System.out.println("Choose the action and enter the number accordingly");
            int select = 0;
            try {
              select = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("Please, make sure you enter a number, not a word.\n");
            }

            if (select < 0 || select >= userActionList.size()) {
                System.out.println("Wrong input, you can select: 0 .. \n" + (userActionList.size() - 1));
                continue;
            }
            run = userActionList.get(select).execute(menu);
        }
    }

    private void showActions(List<UserAction> userActionList) {
        for (int i = 0; i < userActionList.size(); i++) {
            System.out.printf("%s. %s\n", i, userActionList.get(i).name());
        }
    }

    public static void main(String[] args) {
        List<UserAction> actions = Arrays.asList(
                new ShowMenu(),
                new AddElementToRoot(),
                new AddElementToParentElement(),
                new InitiateElementAction(),
                new ExitProgram()
        );
        Menu menu = new SimpleMenu();
        new ToDoApp().init(menu, actions);
    }


}
