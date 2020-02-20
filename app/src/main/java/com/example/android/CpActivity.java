package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class CpActivity extends AppCompatActivity {
    TextView textone;
    Button btnone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp);
        textone = findViewById(R.id.codeone);
        btnone=findViewById(R.id.codetwo);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="";
                try {
                    InputStream input = getAssets().open("cprogram.c");
                    int size = input.available();
                    byte[] buffer=new byte[size];
                    input.read(buffer);
                    input.close();
                    text=new String(buffer);

                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
                textone.setText((CharSequence)text);
            }
        });


    }
}