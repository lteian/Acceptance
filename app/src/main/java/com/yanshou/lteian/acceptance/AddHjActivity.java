package com.yanshou.lteian.acceptance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddHjActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);
        //        1.接收参数
        Intent intent= getIntent();
        final Long locoId= intent.getLongExtra("locoId",0);
//        2.Button 写入数据
        Button addHjSubmit = findViewById(R.id.add_loco_submit);
        final Spinner spinner = findViewById(R.id.spinner_acceptance_type);
        final EditText editText = findViewById(R.id.hj_description);
//      Button 监听事件
        addHjSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                保存活件信息
                LocoAcceptance acceptance = new LocoAcceptance();
                LocoAcceptanceDao acceptanceDao = new LocoAcceptanceDao(AddHjActivity.this);
                acceptance.setAcceptanceType(spinner.getSelectedItem().toString().trim());
                acceptance.setAcceptanceDesc(editText.getText().toString().trim());
                acceptance.setLocoId(locoId);

                Long acceptanceId = acceptanceDao.add(acceptance);

                Toast.makeText(AddHjActivity.this,"活件已添加，活件编号"+acceptanceId,Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });
    }

}
