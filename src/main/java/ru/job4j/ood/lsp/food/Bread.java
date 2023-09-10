package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, LocalDate manufactureDate, LocalDate expirationDate, double price) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }
}
