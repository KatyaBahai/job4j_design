package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Milk extends Food {
    public Milk(String name, LocalDate manufactureDate, LocalDate expirationDate, double price) {
        super(name, manufactureDate, expirationDate, price);
    }
}
