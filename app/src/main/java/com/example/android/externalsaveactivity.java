package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class externalsaveactivity extends AppCompatActivity{
//declaring variables using class variables and data types like String.
    EditText editTextone;
    Button btnsavetxt;
    String filename, filepath, filecontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalsaveactivity);

        //initialising the variables.
        btnsavetxt = findViewById(R.id.save_button);
        editTextone = (EditText)findViewById(R.id.edit_txt);

        //initialising the two variables of data type string to store the file name and file path.
        filename = "messageFile.txt";
        filepath =  "MyFileDir";

        //checking if at all the external storage like a memory card is available on the user's device for read and write.
        // using the method below.
        if (!isExternalStorageAvailableForRW()){
            btnsavetxt.setEnabled(false);
    }
        //attaching an onClickListener method to the save button.
        btnsavetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the input from the edit text.
                filecontent = editTextone.getText().toString().trim();
                if (!filecontent.equals("")){
                    // creating an object of the File class to get the file path and name.
                    File fileExternal = new File(getExternalFilesDir(filepath),filename);
                    // creating an object of the fileOutputStream and initialising it to null in order to write
                    // files into the messageFile.txt file.
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(fileExternal);
                             // writing into the file.
                        fileOutputStream.write(filecontent.getBytes());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editTextone.setText("");
                    Toast.makeText(externalsaveactivity.this, "Text saved on your memory card successfully ", Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    Toast.makeText(externalsaveactivity.this, " text field cannot be empty", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }
    //method definition.
    private boolean isExternalStorageAvailableForRW(){
        //checking the state of the external storage on the phone.
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            return true;

        }
        return false;
    }

}
