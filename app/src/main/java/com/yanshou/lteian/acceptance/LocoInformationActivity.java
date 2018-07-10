package com.yanshou.lteian.acceptance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class LocoInformationActivity extends AppCompatActivity {



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
//        LinearLayout linearLayout =findViewById(R.id.loco_check_card_container);
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        layoutInflater.inflate(R.layout.card_jc_hj,linearLayout,true);

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
}
