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
        db.execSQL("CREATE TABLE loco_acceptance (id INTEGER PRIMARY KEY AUTOINCREMENT,loco_no INTEGER REFERENCES loco_loco (id), type TEXT NOT NULL, \"desc\" TEXT NOT NULL, pic TEXT);");
        db.execSQL("CREATE TABLE loco_loco (id INTEGER PRIMARY KEY, types TEXT NOT NULL, \"No.\" TEXT NOT NULL, classification TEXT NOT NULL, date DATE NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
