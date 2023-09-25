package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int dotCount = 0;
            String number = menuItemInfo.getNumber();
            for (int i = 1; i < number.length(); i++) {
                if ('.' == number.charAt(i)) {
                    dotCount++;
                }
            }
            String dots = "---".repeat(dotCount);
            System.out.println(dots + menuItemInfo.getNumber() + menuItemInfo.getName());
        }
    }
}
