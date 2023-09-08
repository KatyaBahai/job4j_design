package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }


    public void putIntoStore(List<Food> foodList, LocalDate now) {
        for (Food food : foodList) {
            for (Store store : storeList) {
                if (store.isWithinDateRange(food, store.getPredicate(), now)) {
                    store.add(food);
                    System.out.printf("%s was put into %s\n", food.getName(), store.getName());
                }
            }
        }

    }
}
