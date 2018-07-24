package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LocoAcceptanceDao {
    LocoSqliteHelper helper;
    public LocoAcceptanceDao(Context context){
        helper = new LocoSqliteHelper(context);
    }

    public long add(LocoAcceptance acceptance){
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = cupboard().withDatabase(db).put(acceptance);
        return id;
    }

    public List<LocoAcceptance> findJobList(Long locoId){

//        查找id等于该机车的活件

        List<LocoAcceptance> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        if(locoId != null){
            QueryResultIterable<LocoAcceptance> iter = cupboard().withDatabase(db).query(LocoAcceptance.class).withSelection("locoId = ?", String.valueOf(locoId)).query();
            for (LocoAcceptance acceptance : iter){
                list.add(acceptance);
            }
            iter.close();
        }
        return list;
    }

}
