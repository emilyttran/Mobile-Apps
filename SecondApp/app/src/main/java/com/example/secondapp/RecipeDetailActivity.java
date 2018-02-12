package com.example.secondapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by emily_000 on 2/7/2018.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    private WebView myWebView;

    // override
    protected void onCreate(Bundle savedInstanceState) {  // everytime you override, you need to call the super
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // This is the receiver of the Intent
        // Needs title and instruction URL

        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        // set the title on the action bar
        setTitle(title);

        // create the webview and load the url
        myWebView = findViewById(R.id.detail_web_view);
        myWebView.loadUrl(url);
    }
}