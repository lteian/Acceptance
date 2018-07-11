package com.yanshou.lteian.acceptance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AddHjActivity extends AppCompatActivity {

    private final String APPID ="5b4611c4";
    private SpeechRecognizer mIat;
    private RecognizerDialog mIatDialog;
    private RecognizerDialogListener mRListener;

    private EditText editText;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);

        editText = findViewById(R.id.hj_description);

//          加入讯飞语音
// 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
// 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(AddHjActivity.this, SpeechConstant.APPID +"=5b4611c4");
//        Button 点击 ，语音听写
        mRListener = new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                String text = parseIatResult(results.getResultString());
                result += text;
                editText.setText(result);
                if (isLast) {

                    result = "";
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        };
        mIatDialog = new RecognizerDialog(AddHjActivity.this, null);
        mIatDialog.setListener(mRListener);

        Button iflytekButton = findViewById(R.id.iflytek_listen);

        iflytekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIatParam("zph");
                mIatDialog.show();
            }
        });

        //        1.接收参数
        Intent intent= getIntent();
        final Long locoId= intent.getLongExtra("locoId",0);
//        2.Button 写入数据
        Button addHjSubmit = findViewById(R.id.add_loco_submit);
        final Spinner spinner = findViewById(R.id.spinner_acceptance_type);

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


    private void setIatParam(String filename) {
        // 清空参数
        mIatDialog.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIatDialog.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);

        // 设置返回结果格式
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "2000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIatDialog.setParameter(SpeechConstant.ASR_PTT, "1");
;
    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }
}
