package com.wordpress.tahmidcse.mathkidding;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper{

    private static final String DB_Name = "Math_Kidding_DataBase.db";
    private static final String Table_Name = "Math_Kidding_DB_Table";
    private static final String State = "State";
    private static final String Value = "Value";

    public MyDB(Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String quary;
        quary = "CREATE TABLE "+Table_Name+ "(" +State+ " INTEGER PRIMARY KEY, " +Value+ " INTEGER " + ")";
        sqLiteDatabase.execSQL(quary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(sqLiteDatabase);
    }

    public void setInfo(String state,String value){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(State,state);
        contentValues.put(Value,value);
        sqLiteDatabase.insert(Table_Name,null,contentValues);
    }

    public Cursor getInfo(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor result;
        result = sqLiteDatabase.rawQuery("SELECT * FROM "+Table_Name, null);
        return result;
    }

    public void updateInfo(String state,String value){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(State,state);
        contentValues.put(Value,value);
        sqLiteDatabase.update(Table_Name,contentValues,"State = ?",new String[]{ state });
    }

    public void deleteInfo(String state){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(Table_Name,"State = ?",new String[]{ state });
    }
}
