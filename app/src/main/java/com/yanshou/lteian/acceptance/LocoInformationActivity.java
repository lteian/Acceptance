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

import java.util.ArrayList;
import java.util.List;

public class LocoInformationActivity extends AppCompatActivity {
    private RecyclerView recyclerCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        1.接收参数
        final String[] get_loco;
        Bundle bundle = this.getIntent().getExtras();
        get_loco = bundle.getStringArray("post_loco");
        TextView textView = findViewById(R.id.get_loco);
//        Toast.makeText(AddHjActivity.this,get_loco[0]+","+get_loco[1]+","+get_loco[2],Toast.LENGTH_SHORT).show();
        textView.setText("机车型号："+get_loco[0]+" "+get_loco[1]+"，修程："+get_loco[2]);

//        2.动态的添CardView

//        查找该页机车活件

        recyclerCategory = findViewById(R.id.job_list_recycler);

        JobListAdapter mAdatper = new JobListAdapter(findHj(get_loco));

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
                Intent intent = new Intent(LocoInformationActivity.this, AddHjActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("post_loco",get_loco);
                intent.putExtras(bundle);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

//
    }

    private List<LocoAcceptance> findHj(String[] get_loco) {
        LocoAcceptanceDao locoDao = new LocoAcceptanceDao(LocoInformationActivity.this);
        List<LocoAcceptance> acceptanceList = new ArrayList<LocoAcceptance>();
        LocoLocoDao dao = new LocoLocoDao(LocoInformationActivity.this);
        LocoLoco loco = new LocoLoco();

        loco.setLocoType(get_loco[0]);
        loco.setLocoNumber(get_loco[1]);
        loco.setLocoClassification(get_loco[2]);

        Long locoId = dao.findid(loco);
        acceptanceList = locoDao.findAll(locoId);

        return acceptanceList;
    }


}
