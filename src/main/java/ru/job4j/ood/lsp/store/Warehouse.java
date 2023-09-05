package ru.job4j.ood.lsp.store;

public class Warehouse  extends AbstractStore {

    public Warehouse(String storeType) {
        super(percent -> (percent > 75), storeType);
    }
}

