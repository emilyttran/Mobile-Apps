package com.example.miniapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/14/2018.
 */

public class SearchActivity extends AppCompatActivity {

    Spinner dietDropdown, servingDropdown, prepDropdown;
    ArrayList<String> dietList, prepList;
    ArrayList<Integer> servingList;
    ArrayList<Recipe> recipes;
    String diet, prep;
    int servings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dietDropdown = findViewById(R.id.diet_restriction_dropdown);
        servingDropdown = findViewById(R.id.serving_restriction_dropdown);
        prepDropdown = findViewById(R.id.prep_time_dropdown);

        recipes = Recipe.getRecipesFromFile("recipes.JSON",this);

        dietList = new ArrayList<>();
        prepList = new ArrayList<>();
        servingList = new ArrayList<>();
        
        for(int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            diet = recipe.diet;
            servings = recipe.servings;
            prep = recipe.prep;
            if(dietList.contains(diet) == false)
                dietList.add(diet);
            if(servingList.contains(servings) == false)
                servingList.add(servings);
            if(prepList.contains(prep) == false)
                prepList.add(recipe.prep);
        }

        ArrayAdapter<String> adapterDiet = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,dietList);
        ArrayAdapter<Integer> adapterServings = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,servingList);
        ArrayAdapter<String> adapterPrep = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,prepList);

        dietDropdown.setAdapter(adapterDiet);
        servingDropdown.setAdapter(adapterServings);
        prepDropdown.setAdapter(adapterPrep);


    }

}
