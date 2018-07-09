package com.yanshou.lteian.acceptance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LocoListFragment extends Fragment{
    private RecyclerView recyclerCategory;
    private List<LocoLoco> list = new ArrayList<>();
    private LocoLocoDao Dao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loco_list,null);

//        1.读取数据
        list = Dao.findAll();

        if(list == null || list.size() == 0){

            recyclerCategory = view.findViewById(R.id.loco_list_recycler);

            CardViewAdapter mAdatper = new CardViewAdapter(list);

            recyclerCategory.setAdapter(mAdatper);

//        recyclerCategory.addItemDecoration(new DividerItemDecoration());

            recyclerCategory.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        }

        return view;


    }

}
