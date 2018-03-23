package com.example.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/14/2018.
 */

public class SearchActivity extends AppCompatActivity {

    Spinner dietDropdown, servingDropdown, prepDropdown;
    ArrayList<String> dietList;
    ArrayList<Recipe> recipes;
    String diet, prep;
    int servings;
    int[] servingsCategories;
    Button searchBtn;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = this;
        dietDropdown = findViewById(R.id.diet_restriction_dropdown);
        servingDropdown = findViewById(R.id.serving_restriction_dropdown);
        prepDropdown = findViewById(R.id.prep_time_dropdown);
        searchBtn = findViewById(R.id.search_btn);

        recipes = Recipe.getRecipesFromFile("recipes.JSON",this);

        dietList = new ArrayList<>();
        String[] servingList = {"Less than 4", "4-6", "7-9", "more than 10"};
        String[] prepList = {"30 minutes or less", "less than 1 hour", "more than 1 hour"};

        servingsCategories = new int[4];

        for(int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            diet = recipe.diet;
            servings = recipe.servings;
            prep = recipe.prep;
            if(!dietList.contains(diet))
                dietList.add(diet);
        }

        ArrayAdapter<String> adapterDiet = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,dietList);
        ArrayAdapter<String> adapterServings = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,servingList);
        ArrayAdapter<String> adapterPrep = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,prepList);

        dietDropdown.setAdapter(adapterDiet);
        servingDropdown.setAdapter(adapterServings);
        prepDropdown.setAdapter(adapterPrep);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResults = new Intent(mContext, ResultActivity.class);
                startActivity(intentResults);
                // get values from spinner and put in intents to send
            }
        });
    }


}
