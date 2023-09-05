package ru.job4j.ood.lsp.store;

public class Shop extends AbstractStore {

    public Shop(String storeType) {
        super(percent -> (percent <= 75 && percent >= 25), storeType);
    }

}
