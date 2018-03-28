package com.example.sensorexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
        // 2 ways to access camera
        // 1) use existing camera app in the phone to take photos
        //      using intent to use other application
        //          email, google map
        // 2) write your own in-app camera -> snapchat, instagram

    Button cameraBtn, sensorBtn;
    ImageView imageView;

    // when requesting to use camera, the code is 1888
    // when reqesting to use gallery, the code is 1889

    public static final int CAMERA_REQUEST = 1888;
    public static final int GALLERY_REQUEST = 1889;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraBtn = findViewById(R.id.cameraBtn);
        sensorBtn = findViewById(R.id.sensorBtn);
        imageView = findViewById(R.id.imageView);

        // when you click the "take a photo" button
        // send an intent to request to use the build in camera application

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send an intent and start activity
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // you need to check in MediaStore for the list of codes
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // if request code is camera request
        // get the captured photo and display in the image view

        if(requestCode == CAMERA_REQUEST){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    public void openProximity(View view){
        startActivity(new Intent (MainActivity.this, ProximityActivity.class));
    }
}
