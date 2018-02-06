package com.example.secondapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by emily_000 on 2/5/2018.
 */

public class Recipe {
    // instance variables or fields (should make it private, but for this case, we'll make it public
    public String title;
    public String imageURL;
    public String instructionURL;
    public String description;
    public String label;
    public int servings;

    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe
    // static method that loads our recipes.json using the helper method
        // this method will return an array list of recipes constrcuted from the JSON file
    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        // try to read from the JSON file
        try {
            String jsonString = loadJsonFromAsset("recipes.json", context);
            JSONObject json = new JSONObject(jsonString);
            // get information by using the tags
            JSONArray recipes = json.getJSONArray("recipes");
            // for loop to go through each recipe in your recipes array
            for(int i = 0; i < recipes.length();i++){
                // construct a Recipe object for each recipe in JSON
                Recipe recipe = new Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.imageURL = recipes.getJSONObject(i).getString("image");
                recipe.instructionURL = recipes.getJSONObject(i).getString("url");
                recipe.label = recipes.getJSONObject(i).getString("dietLabel");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");

                // add the object to arrayList
                recipeList.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
    // helper method that loads from any Json file and returns the json string
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
