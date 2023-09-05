package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    private String storeType;
    private List<Food> abstractList = new ArrayList<>();
    private final Predicate<Double> predicate;


    public AbstractStore(Predicate<Double> predicate, String storeType) {
        this.predicate = predicate;
        this.storeType = storeType;
    }

    public String getStoreType() {
        return this.storeType;
    }

    public Predicate<Double> getPredicate() {
        return predicate;
    }

    @Override
    public void add(Food food) {
        abstractList.add(food);
    }

    @Override
    public void remove(Food food) {
        abstractList.remove(food);
    }

    @Override
    public void printProducts() {
        abstractList.forEach(System.out::println);
    }

    private double calculateShelfLifePercent(Food food) {
        LocalDate expDate = food.getExpirationDate();
        return (double) ChronoUnit.DAYS.between(LocalDate.now(), expDate) / (double) ChronoUnit.DAYS.between(food.getManufactureDate(), expDate) * 100.0;
    }

    public boolean isWithinDateRange(Food food, Predicate<Double> predicate) {
        return predicate.test(calculateShelfLifePercent(food));
    }

    public List<Food> getList() {
        return abstractList;
    }
}
