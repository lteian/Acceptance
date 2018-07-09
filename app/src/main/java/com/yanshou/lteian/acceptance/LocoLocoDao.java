package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LocoLocoDao {
    NewSqliteHelper helper;
    public LocoLocoDao(Context context){
        helper = new NewSqliteHelper(context);
    }

    public void add(LocoLoco loco){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO loco_loco (loco_type, loco_no, loco_date, loco_classification) VALUES (?, ?, ?, ?)",
                new Object[]{loco.getLoco_type(),loco.getLoco_no(),loco.getLoco_date(),loco.getLoco_classification()} );
    }

    public List<LocoLoco> findAll(){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM loco_loco",null);

        List<LocoLoco> list = new ArrayList<>();

        while (cursor.moveToNext()){
            LocoLoco loco = new LocoLoco();

            int _id = cursor.getInt(cursor.getColumnIndex("id"));
            String locoType = cursor.getString(cursor.getColumnIndex("loco_type"));
            String locoNo = cursor.getString(cursor.getColumnIndex("loco_no"));
            String locoDate = cursor.getString(cursor.getColumnIndex("loco_date"));
            String locoClassification = cursor.getString(cursor.getColumnIndex("loco_classification"));

            loco.setId(_id);
            loco.setLoco_type(locoType);
            loco.setLoco_no(locoNo);
            loco.setLoco_date(locoDate);
            loco.setLoco_classification(locoClassification);

            list.add(loco);
        }
        cursor.close();

        if(list == null || list.size() == 0){
            return null;
        }else{
            return list;
        }


    }
}
