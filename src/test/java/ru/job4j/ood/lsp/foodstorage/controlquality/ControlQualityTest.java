package ru.job4j.ood.lsp.foodstorage.controlquality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.food.Bread;
import ru.job4j.ood.lsp.foodstorage.food.Eggs;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.Milk;
import ru.job4j.ood.lsp.foodstorage.store.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void ifExpiredThenPutIntoTrash() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 9, 3), 110);
        Food bread = new Bread("bread",
                LocalDate.of(2023, 9, 3),
                LocalDate.of(2023, 9, 30), 80);
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 30), 100);
        List<Food> foodList = new ArrayList<>();
        foodList.add(milk);
        foodList.add(bread);
        foodList.add(eggs);

        Store warehouse = new Warehouse("Warehouse");
        Store shop = new Shop("Shop");
        Store trash = new Trash("Trash");
        List<Store> storeList = new ArrayList<>();
        storeList.add(shop);
        storeList.add(trash);
        storeList.add(warehouse);

        ControlQuality controlQuality = new ControlQuality(storeList);
        LocalDate now = LocalDate.of(2023, 9, 9);
        controlQuality.putIntoStore(foodList, now);
        assertThat(trash.getList()).contains(milk);
        assertThat(shop.getList()).contains(eggs);
        assertThat(warehouse.getList()).contains(bread);

    }

    @Test
    void ifResortThenResorted() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 9, 3), 110);
        Food bread = new Bread("bread",
                LocalDate.of(2023, 9, 3),
                LocalDate.of(2023, 9, 30), 80);
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 30), 100);
        List<Food> foodList = new ArrayList<>();
        foodList.add(milk);
        foodList.add(bread);
        foodList.add(eggs);

        Store warehouse = new Warehouse("Warehouse");
        Store shop = new Shop("Shop");
        Store trash = new Trash("Trash");
        List<Store> storeList = new ArrayList<>();
        storeList.add(shop);
        storeList.add(trash);
        storeList.add(warehouse);

        ControlQuality controlQuality = new ControlQuality(storeList);
        LocalDate was = LocalDate.of(2023, 9, 9);
        controlQuality.putIntoStore(foodList, was);
        LocalDate now = LocalDate.of(2023, 9, 27);
        controlQuality.resort(now);
        assertThat(trash.getList()).contains(milk, eggs, bread);
    }
}