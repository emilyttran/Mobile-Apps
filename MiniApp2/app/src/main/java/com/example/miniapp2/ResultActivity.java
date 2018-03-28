package com.example.miniapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/22/2018.
 */

public class ResultActivity extends AppCompatActivity {
    public String selectedServing, selectedDiet, selectedPrep;
    ListView listView;
    TextView resultTV;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        selectedServing = this.getIntent().getExtras().getString("selectedServing");
        selectedDiet = this.getIntent().getExtras().getString("selectedDiet");
        selectedPrep = this.getIntent().getExtras().getString("selectedPrep");

        ArrayList<Recipe> foundRecipes = searchRecipes(selectedServing, selectedDiet, selectedPrep);
        resultTV = findViewById(R.id.resultTV);
        resultTV.setText("Results found: " + Integer.toString(foundRecipes.size()));
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

            if(selectedServing.equals(""))
                servings = "";
            if(selectedDiet.equals(""))
                diet = "";
            if(selectedPrep.equals(""))
                prep = "";

            if(selectedServing.equals(servings) && selectedDiet.equals(diet) && selectedPrep.equals(prep))
                recipeList.add(tempRecipe);

            if(selectedPrep.equals("less than 1 hour") && prep.equals("30 minutes or less")){
                String temp = "30 minutes or less";
                if(selectedServing.equals(servings) && selectedDiet.equals(diet) && temp.equals(prep))
                    recipeList.add(tempRecipe);
            }


        }

        return recipeList;
    }
}
