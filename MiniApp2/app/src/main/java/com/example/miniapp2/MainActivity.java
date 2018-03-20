package com.example.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        ArrayList<Recipe> recipes = Recipe.getRecipesFromFile("recipes.JSON",this);

        startBtn = findViewById(R.id.start_button);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent startIntent = new Intent(mContext, SearchActivity.class);
                startActivity(startIntent);
            }
        });

    }
}
