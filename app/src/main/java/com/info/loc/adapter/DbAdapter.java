package com.info.loc.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.info.loc.adapter.DbContract.CSEntry;

import java.sql.SQLException;

/**
 * Created by ranjan on 7/25/2015.
 */
public class DbAdapter {



    // table name
    public static final String TABLE_NAME = "cstable";

    // database creation details
    private static final String DATABASE_NAME = "cityscopedb";
    private static final int DATABASE_VERSION = 1;
    // sql create table
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + CSEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CSEntry.COLUMN_LATITUDE + " TEXT NOT NULL, "
            + CSEntry.COLUMN_LONGITUDE + " TEXT NOT NULL, "
            + CSEntry.COLUMN_TITLE + " TEXT NOT NULL, "
            + CSEntry.COLUMN_REVIEW +  " TEXT NOT NULL, "
            + CSEntry.COLUMN_CATEGORY + " TEXT NOT NULL, "
            + CSEntry.COLUMN_IMAGE + " TEXT " + " );";
    DatabaseHelper DbHelper;
    private final Context context;
    private SQLiteDatabase db;

    public DbAdapter(Context ctx) {
        this.context = ctx;
        this.DbHelper = new DbAdapter.DatabaseHelper(this.context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           /*Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);*/

            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            //onCreate(db);
        }

        }
    // ---opens the database---
    public DbAdapter open() throws SQLException {
        db = DbHelper.getWritableDatabase();
        return this;
    }


    // ---closes the database---
    public void close() {
        DbHelper.close();
    }
    // BASIC CRUD IMPLEMENTATION
    public long insertData(String latitude,String longitude, String title,String review,String category,String image)
    {


        ContentValues initialValues = new ContentValues();

        initialValues.put(CSEntry.COLUMN_LATITUDE,latitude);
        initialValues.put(CSEntry.COLUMN_LONGITUDE, longitude);
        initialValues.put(CSEntry.COLUMN_TITLE, title);
        initialValues.put(CSEntry.COLUMN_REVIEW, review);
        initialValues.put(CSEntry.COLUMN_CATEGORY, category);
        initialValues.put(CSEntry.COLUMN_IMAGE, image);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    public Cursor getAllData() throws SQLException {
        Cursor mCursor = db.query(true, TABLE_NAME, new String[] {
                        CSEntry.COLUMN_ID, CSEntry.COLUMN_LATITUDE,CSEntry.COLUMN_LONGITUDE,CSEntry.COLUMN_TITLE,CSEntry.COLUMN_REVIEW,CSEntry.COLUMN_IMAGE},null,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getData(String latitude,String longitude) throws SQLException {
        Cursor mCursor = db.query(true, TABLE_NAME, new String[] {
                        CSEntry.COLUMN_ID, CSEntry.COLUMN_LATITUDE,CSEntry.COLUMN_LONGITUDE,CSEntry.COLUMN_TITLE,CSEntry.COLUMN_REVIEW,CSEntry.COLUMN_IMAGE }, CSEntry.COLUMN_LATITUDE + "=" + latitude + "AND" + CSEntry.COLUMN_LONGITUDE + "=" + longitude,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getDataCategory(String category) throws SQLException {
        Cursor mCursor = db.query(true, TABLE_NAME, new String[] {
                        CSEntry.COLUMN_ID,CSEntry.COLUMN_LATITUDE,CSEntry.COLUMN_LONGITUDE,CSEntry.COLUMN_TITLE,CSEntry.COLUMN_REVIEW,CSEntry.COLUMN_IMAGE }, CSEntry.COLUMN_CATEGORY + "=" + category,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



}