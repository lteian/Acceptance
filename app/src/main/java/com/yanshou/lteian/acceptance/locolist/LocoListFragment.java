package com.yanshou.lteian.acceptance.locolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanshou.lteian.acceptance.joblist.LocoInformationActivity;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoLoco;
import com.yanshou.lteian.acceptance.data.LocoLocoDao;

import java.util.ArrayList;
import java.util.List;

public class LocoListFragment extends Fragment{
    private RecyclerView recyclerCategory;
    List<LocoLoco> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view=inflater.inflate(R.layout.fragment_loco_list,null);

        getSellList(view);

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        getSellList(getView());
    }


    public void getSellList(View view){
        //        1.读取数据
        LocoLocoDao dao = new LocoLocoDao(getActivity());
        try{
            list = dao.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }


        recyclerCategory = view.findViewById(R.id.loco_list_recycler);

        LocoListAdapter mAdatper = new LocoListAdapter(list);

        recyclerCategory.setAdapter(mAdatper);

        mAdatper.setOnItemClickListener(new LocoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Long locoId) {
                Intent intent = new Intent(getActivity(),LocoInformationActivity.class);
                intent.putExtra("locoId",locoId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

//        recyclerCategory.addItemDecoration(new DividerItemDecoration());

        recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }
}
