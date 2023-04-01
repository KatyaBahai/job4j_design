package ru.job4j.serialization.json;


public class Recipe {
    private String cookBook;

    public Recipe(String cookBook) {
        this.cookBook = cookBook;
    }

    public String getCookBook() {
        return cookBook;
    }

    @Override
    public String toString() {
        return "Recipe{"
                + "cookBook='" + cookBook + '\''
                + '}';
    }
}
