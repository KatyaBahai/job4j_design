package ru.job4j.serialization.json;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonJavaBorscht {

    public static void main(String[] args) {
        JSONObject jsonRecipe = new JSONObject("{\"cookBook\" : \"Grandma's recipes\"}");

        String[] ingredients = new String[] {"cabbage", "beetroots", "meat", "sour cream", "dill"};
        JSONArray jsonIngredients = new JSONArray(ingredients);

        Borscht ukrBorscht = new Borscht(new Recipe("Grandma's recipes"),
                "Ivan", 2, new String[] {"parsley", "carrots", "vinegar"}, true);
        JSONObject jsonBorscht = new JSONObject();
        jsonBorscht.put("recipe", jsonRecipe);
        jsonBorscht.put("cook", ukrBorscht.getCook());
        jsonBorscht.put("cookingTime", ukrBorscht.getCookingTime());
        jsonBorscht.put("ingredients", jsonIngredients);
        jsonBorscht.put("isSpicy", ukrBorscht.isSpicy());
        System.out.println(jsonBorscht);

        JSONObject jsonBorschtTakeTwo = new JSONObject(ukrBorscht);
        System.out.println(jsonBorschtTakeTwo);



    }
}
