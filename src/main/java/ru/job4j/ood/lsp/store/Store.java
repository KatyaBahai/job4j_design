package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);

    void remove(Food food);

    void printProducts();

    Predicate<Double> getPredicate();

    List<Food> getList();

    String getStoreType();

    boolean isWithinDateRange(Food food, Predicate<Double> predicate);
}
