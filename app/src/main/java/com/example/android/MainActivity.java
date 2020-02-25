package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button musicy= (Button)findViewById(R.id.btnplaymusic);

        musicy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PlayMusic.class);
                startActivity(intent);
            }

        });


    }

    public void sendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.message);
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
                startActivity( new Intent(this,ProductActivity.class));
                return true;
            case R.id.list:
                startActivity( new Intent(this,Activity2.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
