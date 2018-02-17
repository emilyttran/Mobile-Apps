package com.example.miniappstarwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by emily_000 on 2/16/2018.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private int selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        // grab info from intent
        String titleString = this.getIntent().getExtras().getString("title");
        String descriptionString = this.getIntent().getExtras().getString("description");
        String posterString = this.getIntent().getExtras().getString("poster");
        final int position = this.getIntent().getExtras().getInt("position");

        // grab views
        TextView titleView = findViewById(R.id.titleView2);
        ImageView posterView = findViewById(R.id.poster2);
        TextView descriptionView = findViewById(R.id.descriptionView2);
        Button submitButton = findViewById(R.id.submitButton);

        // set info onto the views
        titleView.setText(titleString);
        Picasso.with(mContext).load(posterString).into(posterView);
        descriptionView.setText(descriptionString);
        setTitle(titleString);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent radioIntent = new Intent();

                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                selectedRadio = radioGroup.getCheckedRadioButtonId();

                // get selected radio
                RadioButton radioView = findViewById(selectedRadio);
                String radioText = (String) radioView.getText();

                radioIntent.putExtra("position",position);
                radioIntent.putExtra("radioString",radioText);
                setResult(RESULT_OK, radioIntent);
                finish();
            }
        });
    }

}
