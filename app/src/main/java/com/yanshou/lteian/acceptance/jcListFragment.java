package com.yanshou.lteian.acceptance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.Objects;

public class jcListFragment extends Fragment{
    Loco_locoDao dao = null;
    List<Loco_loco> list= null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.jc_list,null);

        ListView listView = view.findViewById(R.id.loco_list);

        dao = new Loco_locoDao(Objects.requireNonNull(getActivity()).getApplicationContext());

//         listView.setAdapter(new ListAdapter(list,getActivity()));

        return view;
    }


}
