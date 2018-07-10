package com.yanshou.lteian.acceptance;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class AddHjActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jc_add_hj);
        //        1.接收参数
        final String[] get_loco;
        Bundle bundle = this.getIntent().getExtras();
        get_loco = bundle.getStringArray("post_loco");
//        2.Button 写入数据
        Button button = findViewById(R.id.hj_submit);
        final Spinner spinner = findViewById(R.id.spinner_acceptance_type);
        final EditText editText = findViewById(R.id.hj_description);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acceptance_type = spinner.getSelectedItem().toString().trim();
                String hj_description = editText.getText().toString().trim();


//                3.存储数据

//                3.1存储机车信息，这里有个判断，先不写

                String loco_date =String.valueOf(new Date());
                Loco_loco locoLoco =new Loco_loco();

                locoLoco.setLoco_type(get_loco[0]);
                locoLoco.setLoco_no(get_loco[1]);
                locoLoco.setClassification(get_loco[2]);
                locoLoco.setLoco_date(loco_date);

                Loco_locoDao locoDao = new Loco_locoDao(AddHjActivity.this);
//                想一想还是写一下判断吧
                int i =locoDao.findId(get_loco[1], loco_date);
                if(i==0){
                    locoDao.add(locoLoco);
                }

//                3.2存储活件信息
                loco_acceptance acceptance= new loco_acceptance();
                acceptance.setType(acceptance_type);
                acceptance.setDesc(hj_description);
//                acceptance.setPic();
                acceptance.setLoco_id(i);
//            返回上级Activity
                onBackPressed();

            }
        });
    }
}
