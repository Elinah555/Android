package com.example.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.Gravity;
import android.widget.Toast;

public class MyReceiverBroad extends BroadcastReceiver {
    // TODO: This method is called when the BroadcastReceiver is receiving
    // an Intent broadcast.
    // In your broad cast receiver class you created, implement the vibrator class.
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast=Toast.makeText(context,"Time for android man",Toast.LENGTH_LONG);
        //sets the position of the toast
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        //calling the vibrator
        Vibrator vibratoralarm;
        vibratoralarm = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibratoralarm.vibrate(3000);


    }
}
