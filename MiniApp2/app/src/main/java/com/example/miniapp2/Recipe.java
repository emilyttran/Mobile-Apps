package com.example.miniapp2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by emily_000 on 3/20/2018.
 */

public class Recipe {
    public String title;
    public String image;
    public String url;
    public String description;
    public int servings;
    public String prep;
    public String diet;
    public String servingLabel;
    public String prepLabel;

    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> list = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset("recipes.JSON", context);
            JSONObject json = new JSONObject(jsonString);
            // get information by using the tags
            JSONArray recipes = json.getJSONArray("recipes");

            // for loop to go through each recipe in your recipes array
            for(int i = 0; i < recipes.length(); i++){
                Recipe recipe = new Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.image = recipes.getJSONObject(i).getString("image");
                recipe.url = recipes.getJSONObject(i).getString("url");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");
                recipe.prep = recipes.getJSONObject(i).getString("prepTime");
                recipe.diet = recipes.getJSONObject(i).getString("dietLabel");

                defineServingLabels(recipe);
                definePrepLabels(recipe);

                list.add(recipe);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void defineServingLabels(Recipe recipe){
        if(recipe.servings < 4)
            recipe.servingLabel = "Less than 4";
        else if(recipe.servings >= 4 && recipe.servings < 7)
            recipe.servingLabel = "4-6";
        else if(recipe.servings >= 7 && recipe.servings < 10)
            recipe.servingLabel = "7-9";
        else if(recipe.servings >= 10)
            recipe.servingLabel = "More than 10";
    }

    private static void definePrepLabels(Recipe recipe){
        int number;
        String value;
        List<String> timeArr = Arrays.asList((recipe.prep).split(" "));
        value = timeArr.get(1);
        number = Integer.parseInt(timeArr.get(0));

        if(value.equals("hour") || value.equals("hours"))
            recipe.prepLabel = "more than 1 hour";
        else if(value.equals("minutes") && number <= 30)
            recipe.prepLabel = "30 minutes or less";
        else
            recipe.prepLabel = "less than 1 hour";
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
