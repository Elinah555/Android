package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayMusic extends AppCompatActivity {

    MediaPlayer music;
    Button  btnnstart;

    Button btnnstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        btnnstart=(Button)findViewById(R.id.btnstart);
       btnnstart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               music=MediaPlayer.create(getApplicationContext(),R.raw.song);
               music.start();

           }
       });
       btnnstop=(Button)findViewById(R.id.btnstop);
       btnnstop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (music !=null && music.isPlaying()){
                   music.stop();
               }
           }
       });

    }
}
