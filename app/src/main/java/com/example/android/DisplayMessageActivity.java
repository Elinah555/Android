package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Button n =(Button)findViewById(R.id.buttonc);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayMessageActivity.this,CpActivity.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        String message= intent.getStringExtra("MESSAGE");
        TextView messageView=(TextView)findViewById(R.id.messageTextView);
        messageView.setText(message);
    }

    public void onClose(View view){

        super.finish();
    }

}
