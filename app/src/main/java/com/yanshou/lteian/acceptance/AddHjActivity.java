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
        Button button = findViewById(R.id.add_loco_submit);
        final Spinner spinner = findViewById(R.id.spinner_acceptance_type);
        final EditText editText = findViewById(R.id.hj_description);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acceptance_type = spinner.getSelectedItem().toString().trim();
                String hj_description = editText.getText().toString().trim();


            }
        });
    }
}
