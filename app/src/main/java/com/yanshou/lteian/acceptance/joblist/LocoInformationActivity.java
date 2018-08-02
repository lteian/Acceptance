package com.yanshou.lteian.acceptance.joblist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.yanshou.lteian.acceptance.jobadd.JobAddActivity;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoJob;
import com.yanshou.lteian.acceptance.data.LocoJobDao;
import com.yanshou.lteian.acceptance.data.LocoLoco;
import com.yanshou.lteian.acceptance.data.LocoLocoDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocoInformationActivity extends AppCompatActivity {
    private String locoInformation;
    LocoLoco loco = new LocoLoco();
    Long locoId = null;
    private QMUIListPopup listPopup;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loco_information);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        Intent intent = getIntent();
        locoId = intent.getLongExtra("locoId",0);

//        接收参数
        locoInformation = getLocoData(locoId);

//        设置Toolbar;
        initToolbar();
        //设置参数
        setJobCardList();
//        4.悬浮按钮添加活件动作

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setRotation(-155);
                initListPopIfNeed();
                listPopup.setAnimStyle(QMUIListPopup.ANIM_GROW_FROM_CENTER);
                listPopup.setPreferredDirection(QMUIListPopup.DIRECTION_TOP);
                listPopup.show(view);

            }
        });
    }

    private void initListPopIfNeed() {
        if(listPopup == null){
            final String[] items = getResources().getStringArray(R.array.JobType);
            List<String> data = new ArrayList<>();

            Collections.addAll(data, items);

            ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.simple_list_item, data);

            listPopup = new QMUIListPopup(this, QMUIListPopup.DIRECTION_NONE, adapter);
            listPopup.create(QMUIDisplayHelper.dp2px(this, 150), QMUIDisplayHelper.dp2px(this, 250), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Long _id = locoId;
                    Intent intent = new Intent(LocoInformationActivity.this, JobAddActivity.class);
                        intent.putExtra("locoId",_id);
                        intent.putExtra("jobType",items[position]);
                        listPopup.dismiss();
                        startActivity(intent);

//                    Toast.makeText(LocoInformationActivity.this, "position is:" + items[position] + " The id is:" + _id, Toast.LENGTH_SHORT).show();
                }
            });
            listPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    fab.setRotation(0);
                }
            });
        }
    }

    //        设置toolbar
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(locoInformation);
        //        toolbar navigationbutton 点击返回上级菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                finish();
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
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.create();
                        }
                    }).show();
                }
                if(item.getItemId() == R.id.menu_loco_Modify){

                }
                return false;
            }
        });
    }

    //      获取活件列表
    private List<LocoJob> findJob(Long locoId) {
        LocoJobDao acceptanceDao = new LocoJobDao(LocoInformationActivity.this);
        List<LocoJob> acceptanceList = new ArrayList<LocoJob>();

        acceptanceList = acceptanceDao.findJobList(locoId);

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

    @Override
    public void onResume() {
        super.onResume();
        setJobCardList();
    }

    public void setJobCardList(){
//        2.动态的添CardView
//        查找该页机车活件
        RecyclerView recyclerCategory = findViewById(R.id.job_list_recycler);
        JobListAdapter mAdatper = null;
        if (loco != null) {
            mAdatper = new JobListAdapter(this,findJob(loco.get_id()));
        }
        recyclerCategory.setAdapter(mAdatper);
        recyclerCategory.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerCategory.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

    }
}
