package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalFileSave extends AppCompatActivity {
private static final String fileName = "FilesSavedInternally.txt";
EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_file_save);

        editText = findViewById(R.id.edtv);
    }
    public void writeText(View view){
        String m = editText.getText().toString();
        FileOutputStream fileOutputStream= null;
        // storing the entered text such that only this application can access the file document.
        try{
            fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            // writing the text into the file created using and object of the file output stream.
            fileOutputStream.write(m.getBytes());

            //emptying(setting the edit text empty) the edit text after information has been saved.
            editText.setText("");

            //a message that will be displayed to show where the file has been stored using the file path and file name.
            Toast.makeText(this,"File stored in" + getFilesDir() + "/" + fileName, Toast.LENGTH_SHORT)
                    .show();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //closing the file.
        finally {
            if (fileOutputStream!= null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // method definition for reading what was stored in the file.
    public void readText(View view){
        FileInputStream fileInputStream = null;
        try {
         fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String y;
            while ((y = bufferedReader.readLine()) != null){
                stringBuilder.append(y).append("\n");
            }
            editText.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
