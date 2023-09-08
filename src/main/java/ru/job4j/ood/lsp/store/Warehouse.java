package ru.job4j.ood.lsp.store;

public class Warehouse  extends AbstractStore {

    public Warehouse(String storeType) {
        super(percent -> (percent > UPPER_BOUND), storeType);
    }
}

