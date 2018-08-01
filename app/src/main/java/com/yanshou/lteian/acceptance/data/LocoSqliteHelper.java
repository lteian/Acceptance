package com.yanshou.lteian.acceptance.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LocoSqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loco_ys.db";
    private static final int DATABASE_VERSION = 2;

    public LocoSqliteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    static {
        cupboard().register(LocoLoco.class);
        cupboard().register(LocoJob.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        cupboard().withDatabase(db).createTables();
//        db.execSQL("CREATE TABLE loco_acceptance (id INTEGER PRIMARY KEY AUTOINCREMENT,loco_id INTEGER REFERENCES loco_loco (id), acceptance_type TEXT NOT NULL, acceptance_desc TEXT NOT NULL, acceptance_pic TEXT);");
//        db.execSQL("CREATE TABLE loco_loco (id INTEGER PRIMARY KEY, loco_type TEXT NOT NULL, loco_no TEXT NOT NULL, loco_classification TEXT NOT NULL, loco_date DATE NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
