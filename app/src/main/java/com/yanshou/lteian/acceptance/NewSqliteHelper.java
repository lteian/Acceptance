package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewSqliteHelper extends SQLiteOpenHelper {

    public NewSqliteHelper(Context context) {
        super(context,"loco_ys.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE loco_acceptance (id INTEGER PRIMARY KEY AUTOINCREMENT,loco_id INTEGER REFERENCES loco_loco (id), acceptance_type TEXT NOT NULL, acceptance_desc TEXT NOT NULL, acceptance_pic TEXT);");
        db.execSQL("CREATE TABLE loco_loco (id INTEGER PRIMARY KEY, loco_type TEXT NOT NULL, loco_no TEXT NOT NULL, loco_classification TEXT NOT NULL, loco_date DATE NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
