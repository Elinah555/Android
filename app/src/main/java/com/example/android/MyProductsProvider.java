package com.example.android;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
//import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyProductsProvider extends ContentProvider {
    //declaring and initialising variables
    static final String PROVIDER_NAME = " com.example.android.MyProductsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/products";
    static final Uri CONTENT_URI = Uri.parse(URL);

    //declaring and initialising static final strings displaying column_names.
    static final String ID = "product_id";
    static final String PNAME= "product_name";

     static HashMap<String, String> PRODUCT_PROJECTION_MAP;

    static final int PRODUCTS =1;
    static final int PRODUCT_ID = 2;

    static UriMatcher uriMatcher = null;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "products",PRODUCTS);
        uriMatcher.addURI(PROVIDER_NAME, "products/#", PRODUCT_ID);
    }

    /**
     *Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DB_NAME = "ItemStore";
    static final String PRODUCTS_TABLE_NAME = "products";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_PRODUCT_TABLE =
            " CREATE TABLE " + PRODUCTS_TABLE_NAME +
                    " (product_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " product_name TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository
     */
    private static class DbHelper extends SQLiteOpenHelper {
        DbHelper(Context context){
            super(context, DB_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_PRODUCT_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME);
            onCreate(db);

        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DbHelper helper = new DbHelper(context);
        /**
         * create a writable database which will trigger its
         * creation if it doesn't already exist.
         */
        db = helper.getWritableDatabase();

        return (db == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(PRODUCTS_TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case PRODUCTS:
                queryBuilder.setProjectionMap(PRODUCT_PROJECTION_MAP);
                break;

            case PRODUCT_ID:
                queryBuilder.appendWhere( ID +  "=" + uri.getPathSegments().get(1));
                break;
            default:
        }

        /**
         *  we arrange basing on the name of the products
         */

        if (sortOrder == null || sortOrder == "") {
            sortOrder = PNAME;
        }
        Cursor c = queryBuilder.query(db, projection, selection, selectionArgs,null,null, sortOrder);
        // register to watch a content URI for changes
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            // retrieving all records
            case PRODUCTS:
                return "vnd.android.cursor.dir/vnd.example.products";

                //retrieving a particular product record
            case PRODUCT_ID:
                return "vnd.android.cursor.item/vnd.example.products";

            default:
                throw new IllegalArgumentException("URI is not supported: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        /**
         * Adding a new record or row in a given table.
         */
        long rowID = db.insert(   PRODUCTS_TABLE_NAME, "", values);
        /**
         * Checking if the record is entered successfully using the if statement
         */
        if (rowID > 0){
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri1, null);
            return  uri1;
        }
        throw new SQLException("Record not added or inserted successfully" + uri);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int number = 0;
        switch (uriMatcher.match(uri)){
            case PRODUCTS:
                number = db.delete(PRODUCTS_TABLE_NAME, selection, selectionArgs);
                break;

            case PRODUCT_ID:
                String id = uri.getPathSegments().get(1);
                number = db.delete( PRODUCTS_TABLE_NAME, ID + "=" + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')'  : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("URI not known " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return number;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs){

     int y = 0;
     switch (uriMatcher.match(uri)){
         case PRODUCTS:
             y = db.update(PRODUCTS_TABLE_NAME, values, selection, selectionArgs);
             break;

         case PRODUCT_ID:
             y = db.update(PRODUCTS_TABLE_NAME, values, ID + "=" + uri.getPathSegments().get(1) +
                     (!TextUtils.isEmpty(selection) ? "  AND (" +selection + ')' : ""), selectionArgs);
             break;
             default:
                 throw new IllegalArgumentException("URI not recognised " + uri);
     }

     getContext().getContentResolver().notifyChange(uri, null);

        return y;
    }
}
