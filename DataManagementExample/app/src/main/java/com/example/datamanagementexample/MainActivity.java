package com.example.datamanagementexample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textmsg;
    TextView textContent;
    Button externalSave,externalRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textmsg = findViewById(R.id.editText);
        textContent = findViewById(R.id.textView);
        externalSave = findViewById(R.id.external_write);
        externalRead = findViewById(R.id.external_read);

        // save externally
        externalSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {
                    // first parameter -> path name
                    // second parameter -> file name
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(getExternalFilesDir(null), "external_file.txt"));

                    fileOutputStream.write(textmsg.getText().toString().getBytes());   // when it writes, it takes in bytes?
                    fileOutputStream.close();

                    Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_LONG).show();
                    textContent.setText("");

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    // storing data externally is a little bit more complicated. Need to give user permission in manifest
    // everytime you want to access external storage
    // you need to make sure that the external storage is available

    private static boolean isExternalStorageAvailable(){
        // check the status of external storage
        // it it is true, return true

        String externalStorageStatus = Environment.getExternalStorageState(); // tells you the state of external storage based on availability
        if(Environment.MEDIA_MOUNTED.equals(externalStorageStatus))
            return true;
        else
            return false;
    }

    // check to see if external storage is read only cause then we can't write (don't have permission to write a read-only file)
    public static boolean isExternalStorageReadOnly(){
        String externalStorageStatus = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageStatus))
            return true;
        else
            return false;
    }


    public void WriteInternalButton(View view){           // create these methods if you add onclick from the XML
        // add the text into a file
        // add the text into a txt file and store it internally
        try {
            FileOutputStream fileout = openFileOutput("internal_text.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            // toast pop up saying sile is sucessfully saved
            Toast.makeText(getBaseContext(), "File is saved!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadInternalButton(View view){
        // file input stream to open the file
        // for each character in the file
        // add it to a String
        // display
        try {
            FileInputStream fileIn = openFileInput("internal_text.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn);

            // input buffer -> character array
            // string -> result that you want to display

            char[] inputBuffer = new char[1000];
            String content = "";
            int charRead;

            while((charRead  = inputStreamReader.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                content += readString;
            }
            inputStreamReader.close();
            textContent.setText(content);
            Toast.makeText(getBaseContext(), "File read successfully!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
