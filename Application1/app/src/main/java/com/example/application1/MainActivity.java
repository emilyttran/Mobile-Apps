package com.example.application1;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // variables
    // hold information retrieved from the activities

    private Context mContext; // keeps all the context of the activity
    private Activity mActivity; // keep activity information
    private ConstraintLayout mConstraintLayout; // layout of our main activity
    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. get the app context
        mContext = getApplicationContext();

        // 2. get the activity
        mActivity = MainActivity.this;

        // 3. get the widgets reference from your XML layout
        mConstraintLayout = findViewById(R.id.cl);
        mButton = findViewById(R.id.button);
        mEditText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView);

        // set a click listener for the button
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override                   // when there is a override, usually there is a method that can be called from the parent class
            public void onClick(View view){
                // change textview
                mTextView.setText(mEditText.getText() + ", welcome back!");

                // pop up window
                // initialize a new instance of layoutinflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // inflate the layout - popup_layout
                View customView = inflater.inflate(R.layout.popup_layout, null);

                // create a popup window
                // params: View, width, height
                mPopupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                // set an evaluation value for popup calls because it requires API 21
                if(Build.VERSION.SDK_INT >= 21){ // check API level
                    mPopupWindow.setElevation(0.5f);
                }
                // get a reference for the close button
                ImageButton closeButton = customView.findViewById(R.id.imageButton);

                // on click listener for the close button
                closeButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        // close the view
                        mPopupWindow.dismiss();
                    }
                });

                // find a location to display the pop up window
                mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER, 0,0); // center of the screen
            }
        });
    }
}
