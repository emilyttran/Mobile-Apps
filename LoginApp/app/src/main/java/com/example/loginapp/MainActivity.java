package com.example.loginapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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

        // build notification for this launch
        // March 23, you onl need to pass the context as parameter
        // now, you need to pass context and channel id -> "default"
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        // set icons
        builder.setSmallIcon(android.R.drawable.star_big_off);

        // if you want to use anything from this context, you can add it to the intent
        // then get the data from intent
        // msg: "username, you have logged in"

        //intent with from and to
        Intent intent = new Intent(mContext, userDetailActivity.class);

        // add data to the intent
        intent.putExtra("username",username.getText().toString());
        // start activity with the intent

        // create a pending intent for the notification with the intent I created
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        builder.setContentIntent(pendingIntent);

        // set the title and content of the notification
        builder.setContentTitle("Successfully logged in");
        builder.setContentText("Hi " + intent.getExtras().getString("username"));

        // get the system service to display this notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        // notify and build the notification
        notificationManager.notify(1, builder.build());

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
