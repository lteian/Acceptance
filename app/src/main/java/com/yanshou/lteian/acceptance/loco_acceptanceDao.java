package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class loco_acceptanceDao {
    NewSqliteHelper helper;
    public loco_acceptanceDao(Context context){
        helper = new NewSqliteHelper(context);
    }

    public void add(loco_acceptance acceptance){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO loco_acceptance (type, \"desc\", pic, loco_no) VALUES (?, ?, ?, ?)"
                ,new Object[]{acceptance.getType(),acceptance.getDesc(),acceptance.getPic(),acceptance.getLoco_no()});
    }
}
