package com.example.glsvn.crudandroid;

/*
  Created by utkuglsvn
  utku.glsvn@gmail.com
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler db;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DBHandler(this);
        //addDataDatabase();
        //updateDataDatabase();
        deleteDataDatabase();
        readDatabase();
    }

    private void readDatabase() {


        List<OgrenciModel> students = db.getOgrenci();

        for (OgrenciModel i: students) {
            String log = "No: " + i.getNo() + " ,Ad: " + i.getAd() + " ,Soyad: " + i.getSoyad();
            Log.d("readDatabase", log);
        }

    }

    private void addDataDatabase() {

        db.addOgr(new OgrenciModel(234567, "utku", "glsvn"));
        db.addOgr(new OgrenciModel(2344567, "hakan", "sonmez"));
        db.addOgr(new OgrenciModel(25787, "utku", "glsvn"));

    }

    private void updateDataDatabase()
    {
        db.updateOgr(new OgrenciModel(2344567, "utku", "glsvn"));
    }
    private void deleteDataDatabase()
    {
        db.deleteOgr(2344567);
    }
}
