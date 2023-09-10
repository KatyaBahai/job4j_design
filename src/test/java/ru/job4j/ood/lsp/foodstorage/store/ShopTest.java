package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Eggs;
import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.food.Milk;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    void ifShelfLifeFineThenIsWithinTheDateRange() {
        LocalDate now = LocalDate.of(2023, 9, 9);
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 30), 100);
        Store shop = new Shop("Shop");
        boolean result = shop.isWithinDateRange(eggs, now);
        assertThat(result).isTrue();
    }

    @Test
    public void whenShelfLife60PercentThenResult60Percent() {
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 30), 100);
        LocalDate now = LocalDate.of(2023, 9, 9);
        Store shop = new Shop("Shop");
        double percent = shop.calculateShelfLifePercent(eggs, now);
        double expected = 33.0;
        assertThat(percent).isEqualTo(expected, offset(1d));
    }

    @Test
    void ifAddFoodToShopThenItIsInShop() {
        List<Food> foodList = new ArrayList<>();
        Food eggs = new Eggs("eggs",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 9, 30), 100);
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 10, 5), 150);
        foodList.add(eggs);
        foodList.add(milk);
        Store shop = new Shop("Shop");
        shop.add(eggs);
        shop.add(milk);
        assertThat(shop.getList()).isEqualTo(foodList);
    }

    @Test
    public void ifRemoveThenRemovedFromShop() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 7, 31),
                LocalDate.of(2023, 10, 5), 150);
        Store shop = new Shop("Shop");
        shop.add(milk);
        assertThat(shop.getList()).contains(milk);
        shop.remove(milk);
        assertThat(shop.getList()).isEmpty();
    }
}