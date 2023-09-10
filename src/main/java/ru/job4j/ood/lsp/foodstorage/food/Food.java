package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {
    protected String name;
    protected LocalDate manufactureDate;
    protected LocalDate expirationDate;
    protected double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", manufactureDate=" + manufactureDate
                + ", expirationDate=" + expirationDate
                + ", price=" + price
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && name.equals(food.name) && Objects.equals(manufactureDate, food.manufactureDate) && Objects.equals(expirationDate, food.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufactureDate, expirationDate, price);
    }
}
