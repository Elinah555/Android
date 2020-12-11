package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Hope you remember how did everything. hahaha
public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int display = intent.getIntExtra("level", 0);
            ProgressBar bar = findViewById(R.id.progressBar2);
            bar.setProgress(display);
            TextView textView = findViewById(R.id.texthello);
            textView.setText("current battery level" + display + "%");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(broadcastReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    // implementing the alarm but at first create a button in a mainActivity xml and call the onclick maethod within the button tag
    //and give it any name, mine is called "alarmsetting" , this is where the implementation is done.
    // Also create an edit text in the main activity xml
    public void alarmsetting(View view){
        EditText editText = findViewById(R.id.alarmtext);
        //changing the entered  text to a string then to an integer
        int z =Integer.parseInt(editText.getText().toString());

        //Create the intent and call your receiver explicit intent
        Intent intent= new Intent(getApplicationContext(),MyReceiverBroad.class);

        //creating the pending intent
        PendingIntent pendingIntent;
        pendingIntent=PendingIntent.getBroadcast(this.getApplicationContext(),0,intent,0);

        //calling the alarm manager class to use the alarm also accessing the system service method to access services provided by
        // the android system
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        //setting the alarm time
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (z* 1000),pendingIntent);

        //make a toast using a class Toast
        Toast.makeText(this," Your Alarm is set in"+  z +"seconds",Toast.LENGTH_LONG).show();

    }

    public void sendMessage(View view) {
        EditText message = findViewById(R.id.messageone);
        Toast.makeText(this, "Sending message " + message.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);
        message.setText("");
    }
    public boolean onCreateOptionsMenu(Menu menudisplay){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menufile,menudisplay);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
//respond to menu item selection
        switch (item.getItemId()) {
            case R.id.locat:
                startActivity( new Intent(this,LocationActivity.class));
                return true;

            case R.id.prod:
                //declaring an implicit intent to call a person
                Intent intentthree = new Intent(Intent.ACTION_DIAL);
                 intentthree.setData(Uri.parse("tel:0757361800"));
                // startActivity( new Intent(this,ProductActivity.class));
                startActivity(intentthree);
                return true;

            case R.id.list:
                startActivity( new Intent(this,Activity2.class));
                return true;

            case R.id.email:
                //an intent for sending an email to three people
                Intent e = new Intent(Intent.ACTION_SEND);
                e.setData(Uri.parse("mailto:"));
                String[] to = {"elinahnabasitu@gmail.com", "preskakuru@gmail.com", "birungitricia1@gmail.com"};
                e.putExtra(Intent.EXTRA_EMAIL, to);
                e.putExtra(Intent.EXTRA_SUBJECT, "Receive the document below");
                e.putExtra(Intent.EXTRA_TEXT, "Thanks for receiving the document");
                e.setType("message/rfc822");
                startActivity(e);
                return true;

            case R.id.music:
                startActivity( new Intent(this,PlayMusic.class));
                return true;

            case R.id.extstr:
                startActivity( new Intent(this,externalsaveactivity.class));
                return true;

            case R.id.Internally:
                startActivity( new Intent(this,InternalFileSave.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
