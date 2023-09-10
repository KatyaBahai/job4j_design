package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    protected static final int UPPER_BOUND = 75;
    protected static final int LOWER_BOUND = 25;
    protected String storeType;
    protected List<Food> abstractList = new ArrayList<>();
    protected final Predicate<Double> predicate;


    @Override
    public Predicate<Double> getPredicate() {
        return predicate;
    };

    public AbstractStore(Predicate<Double> predicate, String storeType) {
        this.predicate = predicate;
        this.storeType = storeType;
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

    @Override
    public double calculateShelfLifePercent(Food food,  LocalDate now) {
        LocalDate expDate = food.getExpirationDate();
        return (double) ChronoUnit.DAYS.between(now, expDate) / (double) ChronoUnit.DAYS.between(food.getManufactureDate(), expDate) * 100.0;
    }

    @Override
    public boolean isWithinDateRange(Food food, LocalDate now) {
        return predicate.test(calculateShelfLifePercent(food, now));
    }

    @Override
    public List<Food> getList() {
        return abstractList;
    }

    @Override
    public String getName() {
        return storeType;
    }
}
