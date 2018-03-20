package com.example.miniapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/14/2018.
 */

public class SearchActivity extends AppCompatActivity {

    Spinner dietDropdown, servingDropdown, prepDropdown;
    String[] dietList, servingList, prepList;
    ArrayList<Recipe> recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dietDropdown = findViewById(R.id.diet_restriction_dropdown);
        servingDropdown = findViewById(R.id.serving_restriction_dropdown);
        prepDropdown = findViewById(R.id.prep_time_dropdown);



    }

}
