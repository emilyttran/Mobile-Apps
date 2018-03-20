package com.example.miniapp2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

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

                list.add(recipe);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return list;
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
