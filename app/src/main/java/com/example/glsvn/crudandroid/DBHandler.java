package com.example.glsvn.crudandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*
  Created by utkuglsvn
  utku.glsvn@gmail.com 
*/
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Ogrenci";
    private static final String TABLE_NAME= "ogrenciler";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS ogrenciler(o_no  INTEGER (10) PRIMARY KEY, ad TEXT, soyad TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add
    public  void addOgr(OgrenciModel ogrenciModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("o_no", ogrenciModel.getNo());
        values.put("ad", ogrenciModel.getAd());
        values.put("soyad", ogrenciModel.getSoyad());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //listeleme tek
    public OgrenciModel getOgrenci(int no)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { "o_no", "ad", "soyad"},
                "o_no" + "=?",
                new String[] { String.valueOf("o_no") },
                null,
                null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();
        OgrenciModel ogr = new OgrenciModel(Integer.parseInt(cursor.getString(0))
        ,cursor.getString(1),cursor.getString(2));

        return ogr;
    }
    //listeleme list
    public List<OgrenciModel> getOgrenci()
    {
        List<OgrenciModel> studentList = new ArrayList<OgrenciModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OgrenciModel ogr = new OgrenciModel(Integer.parseInt(cursor.getString(0))
                        ,cursor.getString(1),cursor.getString(2));

                studentList.add(ogr);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    public int updateOgr(OgrenciModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ad", student.getAd());
        values.put("soyad", student.getSoyad());

        return db.update(TABLE_NAME,
                values,
                "o_no = ?",
                new String[] { String.valueOf(student.getNo())});

    }


    public void deleteOgr(int no) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"o_no=?",new String[] { String.valueOf(no) });
        db.close();
    }
}
