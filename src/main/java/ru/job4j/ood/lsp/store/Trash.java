package ru.job4j.ood.lsp.store;

public class Trash  extends AbstractStore {

    public Trash(String storeType) {
        super(percent -> (percent < 25), storeType);
    }

}
