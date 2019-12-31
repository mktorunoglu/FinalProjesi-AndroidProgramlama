package com.example.ezberdefteri;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class Database extends SQLiteOpenHelper {
    private static final String Database_Name="veriler";
    private static final int Version=1;

    public Database(Context c) {
        super(c, Database_Name, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE veriler(kelime TEXT, anlam TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS veriler");
        onCreate(db);
    }

    public void onDelete(String silinecekKelime) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("veriler", "kelime" + "=?", new String[]{silinecekKelime});
    }

    public void addRecord(String kelime, String anlam){
        if(kelime != "" && anlam != ""){
            try{
                SQLiteDatabase veriler = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("kelime", kelime);
                cv.put("anlam", anlam);
                veriler.insertOrThrow("veriler", null, cv);
                veriler.close();
            }
            finally{
                this.close();
            }
        }
    }
}
