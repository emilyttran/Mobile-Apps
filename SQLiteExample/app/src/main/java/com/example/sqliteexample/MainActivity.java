package com.example.sqliteexample;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editFirst, editLast, editGrade, editId;
    Button addBtn, updateBtn, deleteBtn, viewAllBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editFirst = findViewById(R.id.firstname_edit_text);
        editLast = findViewById(R.id.lastname_edit_text);
        editGrade = findViewById(R.id.grade_edit_text);
        editId = findViewById(R.id.id_edit_text);
        addBtn = findViewById(R.id.add_button);
        updateBtn = findViewById(R.id.update_button);
        deleteBtn = findViewById(R.id.delete_button);
        viewAllBtn = findViewById(R.id.view_button);

        // insert data
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(editFirst.getText().toString(),
                        editLast.getText().toString(),
                        editGrade.getText().toString());

                if(isInserted == true){
                    // I inserted correctly
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // view all data
        viewAllBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cursor res = myDB.getAllData();

                // if there is nothing in res aka no data in table
                if(res.getCount() == 0) {
                    showMessage("No data found.");
                    return;
                }
                // build a String to display
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){     // similiar to Scanner's .hasNext()
                    // get the column name + data of that row
                    // add it to the string buffer
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Firstname " + res.getString(1) + "\n");
                    buffer.append("Lastname " + res.getString(2) + "\n");
                    buffer.append("Grade " + res.getString(3) + "\n");
                }

                // display the string buffer
                showMessage(buffer.toString());
            }
        });
    }

    // write a method that takes a message and display it with an alert Dialog
    public void showMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);

    }


    // SQLite
    // Basic operations
        // Create table, insert row, update row, delete row

    /*

    How databases look like:

    ------------ Student Record Table --------------
    id | firstName | lastName | grade
    1  | Mark      | Blah     | 100

    each database -> primary key (unique key to each row that you use to refer to that row), foreign key (don't worry about it in this class)
     */
}
