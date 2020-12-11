
package com.example.android;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayMusic extends AppCompatActivity implements View.OnClickListener {
//Creating/ defining variables using class names.
    private Intent musicservice;
    Button btnnstart;
    Button btnnstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        // finding views for example button start and stop from the xml file using their id's.
        btnnstart = findViewById(R.id.btnstart);
        btnnstop = findViewById(R.id.btnstop);

        //implementation/ activating the buttons to listen to something or try out any activity
        //when clicked on by the user.
        btnnstart.setOnClickListener(this);
        btnnstop.setOnClickListener(this);

        //Initialising the musicservice variable to be able to get the content available in the ServiceMusic class.
        musicservice = new Intent(getApplicationContext(), ServiceMusic.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnstart:
                startService(new Intent(getApplicationContext(),ServiceMusic.class));
                break;
            case R.id.btnstop:
                stopService(new Intent(getApplicationContext(), ServiceMusic.class));
                break;
        }

    }
}
