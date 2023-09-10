package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Eggs extends Food {
    public Eggs(String name, LocalDate manufactureDate, LocalDate expirationDate, double price) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }
}
