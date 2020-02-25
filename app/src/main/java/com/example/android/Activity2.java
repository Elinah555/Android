package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ListView prdtview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        prdtview1=findViewById(R.id.prdt);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Cement");
        arrayList.add("Nails");
        arrayList.add("IronSheets");
        arrayList.add("Paint(Sadolin)");
        arrayList.add("Pipes");
        arrayList.add("Tanks");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this
                ,android.R.layout.simple_list_item_multiple_choice,arrayList);
        prdtview1.setAdapter(arrayAdapter);
    }
}
