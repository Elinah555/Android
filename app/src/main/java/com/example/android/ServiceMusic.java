package com.example.android;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceMusic extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Toast.makeText(this, "music service is playing", Toast.LENGTH_SHORT).
                show();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
        mediaPlayer.setLooping(false);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "your song or music started playing", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return START_STICKY;
    }

    public void onDestroy() {
        Toast.makeText(this, "Your song has stopped playing", Toast.LENGTH_SHORT).show();
mediaPlayer.stop();
    }
}

