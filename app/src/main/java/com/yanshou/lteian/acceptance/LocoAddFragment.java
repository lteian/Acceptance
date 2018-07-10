package com.yanshou.lteian.acceptance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LocoAddFragment extends Fragment{
    private Button button;
    private String [] post_loco = new String[3];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.jc_add,null);

//        按钮传参
        button = view.findViewById(R.id.add_loco_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocoInformationActivity.class);
                //        提取机车型号、车号、修程，保存至post_loco数组
//                        1.提取机车型号
                Spinner spinner = view.findViewById(R.id.spinner_LocomotiveModel);
                post_loco[0] = (String) spinner.getSelectedItem();
                //        2.提取车号
                EditText locoNo = view.findViewById(R.id.loco_no);
                post_loco[1] = String.valueOf(locoNo.getText());
//                          3.提取修程
                EditText locoCF =view.findViewById(R.id.loco_classification);
                post_loco[2] = String.valueOf(locoCF.getText());
//                4.传参
                Bundle bundle = new Bundle();
                bundle.putStringArray("post_loco",post_loco);
                intent.putExtras(bundle);
                startActivity(intent);
//                Toast.makeText(getActivity(),"车型："+post_loco[0]+"，车号："+ post_loco[1] +",修程："+ post_loco[2],Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
