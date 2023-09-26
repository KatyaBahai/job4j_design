package ru.job4j.ood.dip.dipviolation;

public class Business {
    private DBService dbService;


    public Business(DBService dbService) {
        this.dbService = dbService;
    }
}
