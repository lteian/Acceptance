package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Loco_locoDao {

    NewSqliteHelper helper;
    public Loco_locoDao(Context context){
        helper = new NewSqliteHelper(context);
    }

    public void add(Loco_loco loco){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO loco_loco (loco_type, loco_no, classification, loco_date) VALUES (?, ?, ?, ?)"
                ,new Object[]{loco.getLoco_type(),loco.getLoco_no(),loco.getClassification(),loco.getLoco_date()});
    }

    public Loco_loco find(String id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from loco_loco where id=?", new String[]{id});

        boolean result = cursor.moveToNext();

        Loco_loco loco = null;

        if (result) {
            int _id = cursor.getInt(cursor.getColumnIndex("id"));
            String loco_type = cursor.getString(cursor.getColumnIndex("loco_type"));
            String loco_no = cursor.getString(cursor.getColumnIndex("loco_no"));
            String classification = cursor.getString(cursor.getColumnIndex("classification"));
            String loco_date = cursor.getString(cursor.getColumnIndex("loco_date"));

            loco = new Loco_loco();
            loco.setId(_id);
            loco.setLoco_type(loco_type);
            loco.setLoco_date(loco_date);
            loco.setClassification(classification);
            loco.setLoco_no(loco_no);
        }
        cursor.close();

        return loco;
    }

    public int findId(String loco_no, String loco_date) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select id from loco_loco where loco_no=? and loco_date=?", new String[]{loco_no, loco_date});

        boolean result = cursor.moveToNext();

        int id= 0;

        if (result) {
            id= cursor.getInt(cursor.getColumnIndex("id"));

        }
        cursor.close();

        return id;
    }

    public List<Loco_loco> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from loco_loco", null);

        List<Loco_loco> list = new ArrayList<>();

        while (cursor.moveToNext()) {

            Loco_loco loco;

            int _id = cursor.getInt(cursor.getColumnIndex("id"));
            String loco_type = cursor.getString(cursor.getColumnIndex("loco_type"));
            String loco_no = cursor.getString(cursor.getColumnIndex("loco_no"));
            String classification = cursor.getString(cursor.getColumnIndex("classification"));
            String loco_date = cursor.getString(cursor.getColumnIndex("loco_date"));

            loco = new Loco_loco();
            loco.setId(_id);
            loco.setLoco_type(loco_type);
            loco.setLoco_date(loco_date);
            loco.setClassification(classification);
            loco.setLoco_no(loco_no);

            list.add(loco);
        }
        cursor.close();

        return list;
    }
}
