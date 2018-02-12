package com.example.secondapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // data to display
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json",this);

        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
        mListView = findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);

        // 1. each row should be clickable
        // when the row is clicked, the intent is created and send

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe selectedRecipe = recipeList.get(i);

                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                // explicit
                // from -> to
                Intent detailIntent = new Intent(mContext, RecipeDetailActivity.class);

                // put title
                detailIntent.putExtra("title", selectedRecipe.title);
                detailIntent.putExtra("url", selectedRecipe.instructionURL);

                startActivity(detailIntent);
            }
        });
    }
}
