package com.yanshou.lteian.acceptance.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LocoJobDao {
    private LocoSqliteHelper helper;
    public LocoJobDao(Context context){
        helper = new LocoSqliteHelper(context);
    }

    public long add(LocoJob acceptance){
        SQLiteDatabase db = helper.getWritableDatabase();
        return cupboard().withDatabase(db).put(acceptance);
    }

    public List<LocoJob> findJobList(Long locoId){

//        查找id等于该机车的活件

        List<LocoJob> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        if(locoId != null){
            QueryResultIterable<LocoJob> iter = cupboard().withDatabase(db).query(LocoJob.class).withSelection("locoId = ?", String.valueOf(locoId)).query();
            for (LocoJob acceptance : iter){
                list.add(acceptance);
            }
            iter.close();
        }
        return list;
    }

    public String countJob(Long locoId){
        //        查找id等于该机车的活件

        String count = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        if(locoId != null){
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql = "select count from LocoJob where(locoId = ?)";
            Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(locoId)});
            count = cursor.getString(0);
        }
        return count;
    }

}
