package ru.job4j.ood.lsp.foodstorage.store;

public class Warehouse  extends AbstractStore {

    public Warehouse(String storeType) {
        super(percent -> (percent > UPPER_BOUND), storeType);
    }
}

