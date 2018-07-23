package com.yanshou.lteian.acceptance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocoInformationActivity extends AppCompatActivity {
    private String locoInformation;
    LocoLoco loco = new LocoLoco();
    Long locoId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loco_information);
        Intent intent = getIntent();
        locoId = intent.getLongExtra("locoId",0);

//        接收参数
        locoInformation = getLocoData(locoId);

//        设置Toolbar;
        initToolbar();

//        2.动态的添CardView
//        查找该页机车活件
        RecyclerView recyclerCategory = findViewById(R.id.job_list_recycler);
        JobListAdapter mAdatper = null;
        if (loco != null) {
            mAdatper = new JobListAdapter(findJob(loco.get_id()));
        }
        recyclerCategory.setAdapter(mAdatper);
        recyclerCategory.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

//        4.悬浮按钮添加活件动作
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Long _id = locoId;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocoInformationActivity.this, JobAddActivity.class);
                intent.putExtra("locoId",_id);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }
//        设置toolbar
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(locoInformation);
        //        toolbar navigationbutton 点击返回上级菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

//添加溢出菜单
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setTitle(locoInformation);
//        添加菜单点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_loco_delete){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LocoInformationActivity.this);
                    builder.setMessage("确认删除？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LocoLocoDao dao = new LocoLocoDao(LocoInformationActivity.this);
                            dao.del(locoId);
                        }
                    }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.create();
                        }
                    });
                }
                if(item.getItemId() == R.id.menu_loco_Modify){

                }
                return false;
            }
        });
    }

    //      获取活件列表
    private List<LocoAcceptance> findJob(Long locoId) {
        LocoAcceptanceDao acceptanceDao = new LocoAcceptanceDao(LocoInformationActivity.this);
        List<LocoAcceptance> acceptanceList = new ArrayList<LocoAcceptance>();

        acceptanceList = acceptanceDao.findAll(locoId);

        return acceptanceList;
    }

//     获取机车信息
    public String getLocoData(Long locoId) {
//        1.接收参数

        LocoLocoDao locoDao = new LocoLocoDao(LocoInformationActivity.this);
        String locoIm;
        if (locoId != 0) {
            loco= locoDao.find(locoId) ;
        }

        TextView textView = findViewById(R.id.get_loco);
//        Toast.makeText(LocoInformationActivity.this,"接收参数："+locoId,Toast.LENGTH_SHORT).show();
        if (loco != null) {
            textView.setText(String.format("修程：%s", loco.getLocoClassification()));
            locoIm = (loco.getLocoType()+" "+ loco.getLocoNumber()).toString();
        }else{
            locoIm= "";
        }
        return locoIm;
    }
}
