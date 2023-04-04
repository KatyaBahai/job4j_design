package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "Borscht")
@XmlAccessorType(XmlAccessType.FIELD)
public class Borscht {
    private Recipe recipe;

    @XmlAttribute
    private String cook;

    @XmlAttribute
    private int cookingTime;

    @XmlElementWrapper
    @XmlElement(name = "ingredient")
    private String[] ingredients;
    private boolean spicy;

    public Borscht() { }

    public Borscht(Recipe recipe, String cook, int cookingTime,
                   String[] ingredients, boolean spicy) {
        this.recipe = recipe;
        this.cook = cook;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.spicy = spicy;
    }

    public String getCook() {
        return cook;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public Recipe getRecipe() {
        return recipe;
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
