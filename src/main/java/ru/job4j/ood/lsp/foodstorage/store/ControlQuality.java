package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.food.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }


    public void putIntoStore(List<Food> foodList, LocalDate now) {
        for (Food food : foodList) {
            for (Store store : storeList) {
                if (store.isWithinDateRange(food, now)) {
                    store.add(food);
                    System.out.printf("%s was put into %s\n", food.getName(), store.getName());
                }
            }
        }

    }

    public void resort(LocalDate now) {
        List<Food> foodList = new ArrayList<>();
        for (Store store : storeList) {
            foodList.addAll(store.getList());
            store.getList().clear();
        }
        putIntoStore(foodList, now);
    }
}
