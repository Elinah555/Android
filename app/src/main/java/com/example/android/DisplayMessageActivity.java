package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Button n = findViewById(R.id.buttonc);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayMessageActivity.this,CpActivity.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        String message= intent.getStringExtra("MESSAGE");
        TextView messageView= findViewById(R.id.messageTextView);
        messageView.setText(message);
    }

    public void onClose(View view){

        super.finish();
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
