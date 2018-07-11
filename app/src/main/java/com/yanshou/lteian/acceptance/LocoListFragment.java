package com.yanshou.lteian.acceptance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LocoListFragment extends Fragment{
    private RecyclerView recyclerCategory;
    List<LocoLoco> list = new ArrayList<LocoLoco>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loco_list,null);

//        1.读取数据
            LocoLocoDao dao = new LocoLocoDao(getActivity());
            list = dao.findAll();

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

            recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        return view;


    }

}
