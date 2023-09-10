package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.food.Eggs;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.Milk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    void ifShelfLifeFineThenIsWithinTheDateRange() {
        LocalDate now = LocalDate.of(2023, 9, 9);
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 9, 8),
                LocalDate.of(2023, 9, 30), 100);
        Store warehouse = new Warehouse("warehouse");
        boolean result = warehouse.isWithinDateRange(eggs, now);
        assertThat(result).isTrue();
    }

    @Test
    public void whenShelfLife60PercentThenResult60Percent() {
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 9, 8),
                LocalDate.of(2023, 9, 30), 100);
        LocalDate now = LocalDate.of(2023, 9, 10);
        Store warehouse = new Warehouse("warehouse");
        double percent = warehouse.calculateShelfLifePercent(eggs, now);
        double expected = 90;
        assertThat(percent).isEqualTo(expected, offset(1d));
    }

    @Test
    void ifAddFoodToWarehouseThenItIsInWarehouse() {
        List<Food> foodList = new ArrayList<>();
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 9, 8),
                LocalDate.of(2023, 9, 30), 100);
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 11, 5), 150);
        foodList.add(eggs);
        foodList.add(milk);
        Store warehouse = new Warehouse("warehouse");
        warehouse.add(eggs);
        warehouse.add(milk);
        assertThat(warehouse.getList()).isEqualTo(foodList);
    }

    @Test
    public void ifRemoveThenRemovedFromWarehouse() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 11, 5), 150);
        Store warehouse = new Warehouse("warehouse");
        warehouse.add(milk);
        assertThat(warehouse.getList()).contains(milk);
        warehouse.remove(milk);
        assertThat(warehouse.getList()).isEmpty();
    }
}