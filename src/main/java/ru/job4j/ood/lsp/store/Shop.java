package ru.job4j.ood.lsp.store;

public class Shop extends AbstractStore {

    public Shop(String storeType) {
        super(percent -> (percent <= UPPER_BOUND && percent >= LOWER_BOUND), storeType);
    }

}
