package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BorschtTransformation {

    public static void main(String[] args) {
        String[] ingredients = new String[] {"cabbage", "beetroots", "meat", "sourcream", "dill"};
        Borscht ukrBorscht = new Borscht(new Recipe("Grandma's recipes"),
                "Ivan", 2, ingredients, true);
        final Gson gson = new GsonBuilder().create();
        String borschtGson = gson.toJson(ukrBorscht);
        System.out.println(borschtGson);
        Borscht borschtFromGson = gson.fromJson(borschtGson, Borscht.class);
        System.out.println(borschtFromGson);
    }
}
