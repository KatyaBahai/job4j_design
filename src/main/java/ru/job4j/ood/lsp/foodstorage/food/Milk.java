package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;

public class Milk extends Food {
    public Milk(String name, LocalDate manufactureDate, LocalDate expirationDate, double price) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }
}
