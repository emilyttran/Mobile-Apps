package com.example.secondapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to display
        ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json",this);

        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
        mListView = findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);
    }
}
