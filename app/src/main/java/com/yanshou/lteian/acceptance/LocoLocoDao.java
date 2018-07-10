package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LocoLocoDao {
    LocoSqliteHelper helper;
    public LocoLocoDao(Context context){
        helper = new LocoSqliteHelper(context);
    }

    public Long add(LocoLoco loco){
        SQLiteDatabase db = helper.getWritableDatabase();
        Long id = cupboard().withDatabase(db).put(loco);
        return id;
    }
//
    public List<LocoLoco> findAll(){
        List<LocoLoco> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        QueryResultIterable<LocoLoco> itr = cupboard().withDatabase(db).query(LocoLoco.class).query();
        for (LocoLoco locoLoco : itr) {
            list.add(locoLoco);
        }
        itr.close();

        return list;

    }
}
