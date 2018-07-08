package com.yanshou.lteian.acceptance;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class AddHjActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        1.接收参数
        String[] get_loco;
        Bundle bundle = this.getIntent().getExtras();
        get_loco = bundle.getStringArray("post_loco");
        TextView textView = findViewById(R.id.get_loco);
//        Toast.makeText(AddHjActivity.this,get_loco[0]+","+get_loco[1]+","+get_loco[2],Toast.LENGTH_SHORT).show();
        textView.setText("机车型号："+get_loco[0]+" "+get_loco[1]+"，修程："+get_loco[2]);

//        2.动态的添CardView
        LinearLayout linearLayout =findViewById(R.id.loco_check_card_container);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(R.layout.card_jc_hj,linearLayout,true);
        layoutInflater.inflate(R.layout.card_jc_hj,linearLayout,true);
        layoutInflater.inflate(R.layout.card_jc_hj,linearLayout,true);


    }
}
