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
        db.execSQL("INSERT INTO loco_acceptance (loco_id,acceptance_type, acceptance_desc, acceptance_pic) VALUES (?, ?, ?, ?)"
                ,new Object[]{acceptance.getLoco_id(),acceptance.getAcceptance_type(),acceptance.getAcceptance_desc(),acceptance.getAcceptance_pic()});
    }

}
