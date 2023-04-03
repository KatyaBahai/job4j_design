package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Recipe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe {
    @XmlAttribute
    private String cookBook;

    public Recipe() {
    }

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
