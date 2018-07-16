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
import android.widget.Toast;

public class LocoAddFragment extends Fragment{
    private Button button;
    private EditText locoCF;
    LocoLoco loco = new LocoLoco();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_loco_add,null);
        locoCF =view.findViewById(R.id.loco_classification);
//        按钮传参
        button = view.findViewById(R.id.add_loco_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                //        提取机车型号、车号、修程，保存至post_loco数组
//                        1.提取机车型号


                Spinner spinner = view.findViewById(R.id.spinner_LocomotiveModel);
                loco.setLocoType(String.valueOf(spinner.getSelectedItem()));
                //        2.提取车号
                EditText locoNo = view.findViewById(R.id.loco_no);
                loco.setLocoNumber(String.valueOf(locoNo.getText()));
//                          3.提取修程

                loco.setLocoClassification(String.valueOf(locoCF.getText()));
//                4.保存机车数据
                //               保存机车信息
                Long locoId = locoSave(loco);
//                5.传参
                Intent intent = new Intent(getActivity(), LocoInformationActivity.class);
                intent.putExtra("locoId",locoId);
                startActivity(intent);


            }
        });

        return view;
    }



    //         保存机车信息
    private Long locoSave(LocoLoco loco) {
//                保存机车信息，有个if，判断机车是否存在
        LocoLocoDao locoDao = new LocoLocoDao(getActivity());
        loco.setLocoDate(System.currentTimeMillis());
        Long locoId = locoDao.findid(loco);
        if(locoId == null){
            locoId = locoDao.add(loco);
            Toast.makeText(getActivity(),"机车信息添加成功，编号："+locoId,Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(getActivity(), "找到以下机车信息，编号：" + locoId, Toast.LENGTH_SHORT).show();
        }
        return locoId;
    }

}
