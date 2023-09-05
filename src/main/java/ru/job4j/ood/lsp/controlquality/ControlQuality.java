package ru.job4j.ood.lsp.controlquality;

import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.List;

public class ControlQuality {
    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }


    public void putIntoStore(Food food) {
        for (Store store : storeList) {
            if (store.isWithinDateRange(food, store.getPredicate())) {
                store.add(food);
                System.out.printf("%s was put into %s", food.getName(), store.getStoreType());
            }
        }
    }
}
