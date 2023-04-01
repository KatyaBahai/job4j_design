package ru.job4j.serialization.json;

import java.net.NoRouteToHostException;
import java.util.Arrays;

public class Borscht {
    private Recipe recipe;
    private String cook;
    private int cookingTime;
    private String[] ingredients;
    private boolean spicy;

    public Borscht(Recipe recipe, String cook, int cookingTime,
                   String[] ingredients, boolean spicy) {
        this.recipe = recipe;
        this.cook = cook;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.spicy = spicy;
    }

   @Override
    public String toString() {
        return "Borscht{"
                + "recipe=" + recipe
                + ", cook=" + cook
                + ", cookingTime=" + cookingTime
                + ", ingredients=" + Arrays.toString(ingredients)
                + ", spicy=" + spicy
                + '}';
    }
}
