package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity implements WorkOne.ItemClickListener {
RecyclerView recyclerView;
WorkOne adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        recyclerView=findViewById(R.id.location);

        ArrayList<String> elements = new ArrayList<>();
        elements.add("Mbarara");
        elements.add("Masaka");
        elements.add("Mukono");
        elements.add("Lira");
        elements.add("Mpigi");
        elements.add("Kampala");

        //set up the RecyclerView
recyclerView.setLayoutManager(new LinearLayoutManager(this));
adapter=new WorkOne(this,elements);
adapter.setClickListener(this);
recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
