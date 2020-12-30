package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);
    }
    public void SubmitData(View view){
        //inserting a new product record on clicking the submit button.
        ContentValues contentValues = new ContentValues();
        contentValues.put( MyProductsProvider.PNAME, ((EditText)findViewById(R.id.edit_txt9)).getText().toString());

        Uri newUri = getContentResolver().insert(MyProductsProvider.CONTENT_URI, contentValues);
        Toast.makeText(getBaseContext(), newUri.toString(), Toast.LENGTH_LONG).show();

    }
    public void RetrieveData (View view) {
        //retrieving or viewing records inserted.
        String URL = "content://com.example.android.MyProductsProvider/products";
         Uri nnewUri= Uri.parse(URL);
        Cursor cursor = getContentResolver().query( nnewUri, null, null, null, "product_name");
        if (cursor.moveToFirst()){
            do {
                Toast.makeText(this, cursor.getString(cursor.getColumnIndex(MyProductsProvider.ID)) +
                        ", " + cursor.getString(cursor.getColumnIndex(MyProductsProvider.PNAME)), Toast.LENGTH_LONG).show();
            }
            while (cursor.moveToNext());
        }
        cursor.close();

    }

}
