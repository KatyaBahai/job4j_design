package ru.job4j.ood.lsp.controlquality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.food.Milk;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void ifShellLifeLongThenPutIntoWarehouse() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 9, 30), 110);

        Store wareHouse = new Warehouse("Warehouse");
        Store shop = new Shop("Shop");
        Store trash = new Trash("Trash");
        List<Store> storeList = new ArrayList<>();
        storeList.add(shop);
        storeList.add(trash);
        storeList.add(wareHouse);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.putIntoStore(milk);
        assertThat(wareHouse.getList()).contains(milk);
        assertThat(shop.getList()).isEmpty();
        assertThat(trash.getList()).isEmpty();

    }

    @Test
    void ifExpiredThenPutIntoTrash() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 9, 3), 110);

        Store wareHouse = new Warehouse("Warehouse");
        Store shop = new Shop("Shop");
        Store trash = new Trash("Trash");
        List<Store> storeList = new ArrayList<>();
        storeList.add(shop);
        storeList.add(trash);
        storeList.add(wareHouse);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.putIntoStore(milk);
        assertThat(trash.getList()).contains(milk);
        assertThat(shop.getList()).isEmpty();
        assertThat(wareHouse.getList()).isEmpty();

    }

    @Test
    void ifShellLife50PercentThenPutIntoShop() {
        Food milk = new Milk("milk",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 9, 12), 110);

        Store wareHouse = new Warehouse("Warehouse");
        Store shop = new Shop("Shop");
        Store trash = new Trash("Trash");
        List<Store> storeList = new ArrayList<>();
        storeList.add(shop);
        storeList.add(trash);
        storeList.add(wareHouse);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.putIntoStore(milk);
        assertThat(shop.getList()).contains(milk);
        assertThat(trash.getList()).isEmpty();
        assertThat(wareHouse.getList()).isEmpty();

    }
}