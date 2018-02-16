package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by emily_000 on 2/14/2018.
 */


public class userDetailActivity extends AppCompatActivity {

    private Context mContext;
    private ImageButton goBackButton;
    private TextView welcomeText;
    private boolean yesChecked;
    private boolean noChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        mContext = this;
        goBackButton = findViewById(R.id.go_back_button);
        welcomeText = findViewById(R.id.welcome_view);


        goBackButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                // construct intent and send back to main activity
                Intent checkBoxIntent = new Intent(); // no need to define destination since it's already been defined in MainActivity

                checkBoxIntent.putExtra("yes",yesChecked);
                checkBoxIntent.putExtra("no",noChecked);

                // tells android I want to send back this activity as the result
                setResult(RESULT_OK, checkBoxIntent);
                // finish this activity and go back to the previous activity
                finish();
            }
        });

        // change welcomeText to display: username + welcome back!

        String username = this.getIntent().getExtras().getString("username");
        welcomeText.setText(username + ", welcome back!");
    }

    public void yesChecked(View view){
        yesChecked = ((CheckBox) view).isChecked(); // checks if checkbox is checked
    }

    public void noChecked(View view){
        noChecked = ((CheckBox) view).isChecked();
    }
}
