package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.food.Eggs;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.Milk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void ifShelfLifeFineThenIsWithinTheDateRange() {
        LocalDate now = LocalDate.of(2023, 9, 10);
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 15), 100);
        Store trash = new Trash("Trash");
        boolean result = trash.isWithinDateRange(eggs, now);
        assertThat(result).isTrue();
    }

    @Test
    public void whenShelfLife20PercentThenResult20Percent() {
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 15), 100);
        LocalDate now = LocalDate.of(2023, 9, 10);
        Store trash = new Trash("Trash");
        double percent = trash.calculateShelfLifePercent(eggs, now);
        double expected = 10.0;
        assertThat(percent).isEqualTo(expected, offset(1d));
    }

    @Test
    void ifAddFoodToTrashThenItIsInTrash() {
        List<Food> foodList = new ArrayList<>();
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 15), 100);
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 9, 5), 150);
        foodList.add(eggs);
        foodList.add(milk);
        Store trash = new Trash("Trash");
        trash.add(eggs);
        trash.add(milk);
        assertThat(trash.getList()).isEqualTo(foodList);
    }

    @Test
    public void ifRemoveThenRemovedFromTrash() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 9, 5), 150);
        Store trash = new Trash("Trash");
        trash.add(milk);
        assertThat(trash.getList()).contains(milk);
        trash.remove(milk);
        assertThat(trash.getList()).isEmpty();
    }
}