package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);

    void remove(Food food);

    void printProducts();

    boolean isWithinDateRange(Food food, LocalDate now);

    double calculateShelfLifePercent(Food food,  LocalDate now);

    Predicate<Double> getPredicate();

    List<Food> getList();

    String getName();
}
