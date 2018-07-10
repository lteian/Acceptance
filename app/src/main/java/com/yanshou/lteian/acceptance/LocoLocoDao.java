package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
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

//    根据机车车型、车号、修程及日期中的年，查找机车信息，返回机车id
//    input Locoloco 数组
//    output Long
    public Long findid(LocoLoco locoLoco){
        SQLiteDatabase db = helper.getReadableDatabase();
        Long _id = null;

        LocoLoco loco = new LocoLoco();
        Long timeStamp = System.currentTimeMillis(); //获取当前时间戳
        Long compareYear = (timeStamp - locoLoco.getLocoDate())/(365*60*60*24); //获取时间上的差值，小于一年的机车不重新建表
        loco = cupboard().withDatabase(db).query(LocoLoco.class).withSelection("locoType = ? and locoNumber = ? and locoClassification = ?", new String[]{locoLoco.getLocoType(),locoLoco.getLocoNumber(),locoLoco.getLocoClassification()}).get();
        if(compareYear < 366 || loco == null){
            return null;
        }else{

            return loco.get_id();
        }
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
