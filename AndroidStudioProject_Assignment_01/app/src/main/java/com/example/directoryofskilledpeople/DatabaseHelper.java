package com.example.directoryofskilledpeople;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database Version
    public static final int DATABASE_VERSION = 1;
    //Database Name
     public static final String DATABASE_NAME = "experts_database";
    //Database Table Name
    public static final String TABLE_NAME = "experts_table";
    //Table Columns names
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String TITLE = "TITLE";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String NATIONALITY = "NATIONALITY";

    //Database Instance Variable
    private SQLiteDatabase sqLiteDatabase;

    //SQL Queries create Database
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("+ID+"TEXT PRIMARY KEY AUTOINCREMENT,"
            +NAME+ "TEXT," +ADDRESS+ "TEXT,"+TITLE + "TEXT, " +PHONE + "TEXT, " + EMAIL + "TEXT, " + NATIONALITY + "TEXT);";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //Add Expert Person
    public void addExperts(Skilled_People skilled_People){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, skilled_People.getName());
        contentValues.put(DatabaseHelper.ADDRESS, skilled_People.getAddress());
        contentValues.put(DatabaseHelper.TITLE, skilled_People.getTitle());
        contentValues.put(DatabaseHelper.PHONE, skilled_People.getPhone());
        contentValues.put(DatabaseHelper.EMAIL, skilled_People.getEmail());
        contentValues.put(DatabaseHelper.NATIONALITY, skilled_People.getNationality());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public List<Skilled_People> getSkilled_people_List(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<Skilled_People> store_Skilled_People = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String title = cursor.getString(3);
                String phone = cursor.getString(4);
                String email = cursor.getString(5);
                String nationality = cursor.getString(6);
                store_Skilled_People.add(new Skilled_People(id,name,address,title,phone,email,nationality));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return store_Skilled_People;
    }
}
