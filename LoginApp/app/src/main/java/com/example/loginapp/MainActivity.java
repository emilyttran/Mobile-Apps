package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private Context mContext;
    private Button mLoginButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get this context
        mContext = this;

        username = findViewById(R.id.username);
        mLoginButton = findViewById(R.id.loginButton);
        resultTextView = findViewById(R.id.resultTextView);

        // how do I start second activity when the login button is clicked?
        mLoginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                launchActivity();
            }
        });

    }

    private void launchActivity(){
        // package intent and start activity

        //intent with from and to
        Intent intent = new Intent(mContext, userDetailActivity.class);

        // add data to the intent
        intent.putExtra("username",username.getText().toString());
        // start activity with the intent

        // because you want to get results from an activity instead of just display, you can't use startActivity(intent);
        // instead,
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){  // if second activity is sending data
                boolean yesBox = data.getBooleanExtra("yes", false);
                boolean noBox = data.getBooleanExtra("no",false);

                // check to see which box has been selected
                // then display different strings in the text view

                if(yesBox && !noBox){
                    resultTextView.setText("GOOD, you like dogs!");
                }
                else if(!yesBox && noBox){
                    resultTextView.setText("Why don't you like dogs? :(");
                }
                else if(yesBox && noBox){
                    resultTextView.setText("You can't choose both!");
                }
                else
                    resultTextView.setText("You have to select at least one");
            }
        }
    }
}
