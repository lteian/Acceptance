package com.yanshou.lteian.acceptance;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LocoListFragment extends Fragment{
    private RecyclerView recyclerCategory;
    List<LocoLoco> list = new ArrayList<LocoLoco>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loco_list,null);

//        1.读取数据
        LocoSqliteHelper helper = new LocoSqliteHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();

            QueryResultIterable<LocoLoco> itr = cupboard().withDatabase(db).query(LocoLoco.class).query();
            for (LocoLoco locoLoco : itr) {
                list.add(locoLoco);
            }
            itr.close();

            recyclerCategory = view.findViewById(R.id.loco_list_recycler);

            CardViewAdapter mAdatper = new CardViewAdapter(list);

            recyclerCategory.setAdapter(mAdatper);

//        recyclerCategory.addItemDecoration(new DividerItemDecoration());

            recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        return view;


    }

}
