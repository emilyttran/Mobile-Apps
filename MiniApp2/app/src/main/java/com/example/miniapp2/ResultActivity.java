package com.example.miniapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/22/2018.
 */

public class ResultActivity extends AppCompatActivity {
    public String selectedServing, selectedDiet, selectedPrep;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        selectedServing = this.getIntent().getExtras().getString("selectedServing");
        selectedDiet = this.getIntent().getExtras().getString("selectedDiet");
        selectedPrep = this.getIntent().getExtras().getString("selectedPrep");

        ArrayList<Recipe> foundRecipes = searchRecipes(selectedServing, selectedDiet, selectedPrep);
        listView = findViewById(R.id.recipeLV);
        RecipeAdapter adapter = new RecipeAdapter(this, foundRecipes);
        listView.setAdapter(adapter);


    }

    // search function to find recipes
    public ArrayList<Recipe> searchRecipes(String selectedServing, String selectedDiet, String selectedPrep){
        ArrayList<Recipe> recipeList = new ArrayList<>();
        String diet, prep, servings;
        Recipe tempRecipe;
        ArrayList<Recipe> allRecipes = Recipe.getRecipesFromFile("recipes.JSON", this);


        for(int i = 0; i < allRecipes.size(); i++){
            tempRecipe = allRecipes.get(i);
            servings = tempRecipe.servingLabel;
            diet = tempRecipe.diet;
            prep = tempRecipe.prepLabel;

            if(selectedServing.equals(servings) && selectedDiet.equals(diet) && selectedPrep.equals(prep))
                recipeList.add(tempRecipe);
        }

        return recipeList;
    }
}
