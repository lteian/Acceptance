package com.yanshou.lteian.acceptance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocoInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loco_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        1.接收参数

        final Intent intent = getIntent();
        final Long locoId = intent.getLongExtra("locoId",0);
        LocoLocoDao locoDao = new LocoLocoDao(LocoInformationActivity.this);
        LocoLoco loco = new LocoLoco();
        if (locoId != 0) {
            loco= locoDao.find(locoId) ;
        }

        TextView textView = findViewById(R.id.get_loco);
        Toast.makeText(LocoInformationActivity.this,"接收参数："+locoId,Toast.LENGTH_SHORT).show();
        if (loco != null) {
            textView.setText(String.format("机车型号：%s %s，修程：%s", loco.getLocoType(), loco.getLocoNumber(), loco.getLocoClassification()));
        }

//        2.动态的添CardView

//        查找该页机车活件

        RecyclerView recyclerCategory = findViewById(R.id.job_list_recycler);

        JobListAdapter mAdatper = null;
        if (loco != null) {
            mAdatper = new JobListAdapter(findHj(loco.get_id()));
        }

        recyclerCategory.setAdapter(mAdatper);

//        recyclerCategory.addItemDecoration(new DividerItemDecoration());

        recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

//        3.toolbar navigationbutton 点击返回上级菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        4.悬浮按钮添加活件动作
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocoInformationActivity.this, JobAddActivity.class);
                intent.putExtra("locoId",locoId);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

//
    }
//      获取活件列表
    private List<LocoAcceptance> findHj(Long locoId) {
        LocoAcceptanceDao acceptanceDao = new LocoAcceptanceDao(LocoInformationActivity.this);
        List<LocoAcceptance> acceptanceList = new ArrayList<LocoAcceptance>();

        acceptanceList = acceptanceDao.findAll(locoId);

        return acceptanceList;
    }


}
