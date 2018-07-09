package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Loco_locoDao {
    NewSqliteHelper helper;
    public Loco_locoDao(Context context){
        helper = new NewSqliteHelper(context);
    }

    public void add(Loco_loco loco){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO loco_loco (loco_type, loco_no, loco_date, loco_classification) VALUES (?, ?, ?, ?)",
                new Object[]{loco.getLoco_type(),loco.getLoco_no(),loco.getLoco_date(),loco.getLoco_classification()} );
    }
}
