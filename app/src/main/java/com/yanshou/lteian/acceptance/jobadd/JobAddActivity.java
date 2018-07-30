package com.yanshou.lteian.acceptance.jobadd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoAcceptance;
import com.yanshou.lteian.acceptance.data.LocoAcceptanceDao;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.List;

public class JobAddActivity extends AppCompatActivity {

    private final String APPID ="5b4611c4";
    private SpeechRecognizer mIat;
    private RecognizerDialog mIatDialog;
    private RecognizerDialogListener mRListener;
    private final static int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1, MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 2, MY_PERMISSIONS_CAMERA = 3;
    private final int REQUEST_CODE_CHOOSE=0;
    private ImageButton JobImage;

    private EditText editText;
    private String result = "";
    private String ImageUri="";
    private Uri capterUri= Uri.parse("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);

        editText = findViewById(R.id.hj_description);
        JobImage = findViewById(R.id.job_image);
        Button selectImageButton = findViewById(R.id.select_pic_button);
        Button capterImageButton = findViewById(R.id.capter_pic_button);

        /**
         * 加入讯飞语音
         */
// 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
// 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"="+APPID);
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
        mIatDialog = new RecognizerDialog(this, null);
        mIatDialog.setListener(mRListener);

        ImageButton iflytekButton = findViewById(R.id.iflytek_listen);

        iflytekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(JobAddActivity.this, Manifest.permission.RECORD_AUDIO);
                if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                    setIatParam("zph");
                    mIatDialog.show();
                }else{
                    if(ActivityCompat.shouldShowRequestPermissionRationale(JobAddActivity.this,Manifest.permission.RECORD_AUDIO)){

                    }else{
                        ActivityCompat.requestPermissions(JobAddActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                    }
                }
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
                LocoAcceptanceDao acceptanceDao = new LocoAcceptanceDao(JobAddActivity.this);
                acceptance.setAcceptanceType(spinner.getSelectedItem().toString().trim());
                acceptance.setAcceptanceDesc(editText.getText().toString().trim());
                acceptance.setLocoId(locoId);
                // 如果pic有图片，保存图片
                if(ImageUri != ""){
                    acceptance.setAcceptancePic(ImageUri);
                }
                Long acceptanceId = acceptanceDao.add(acceptance);

                Toast.makeText(JobAddActivity.this,"活件已添加，活件编号"+acceptanceId,Toast.LENGTH_LONG).show();

                finish();
            }
        });


        /**
         * 使用相机拍照
         */

        capterImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int permissionCheckCamera = ContextCompat.checkSelfPermission(JobAddActivity.this,Manifest.permission.CAMERA);
                if(permissionCheckCamera == PackageManager.PERMISSION_GRANTED){
                    // 这里的getExternalCacheDir() 与 xml中external-cache-path 相对应
                    File imagePath = new File(getExternalCacheDir(), "Pictures");
                    if (!imagePath.exists()){imagePath.mkdirs();}
                    File newFile = new File(imagePath, "mycamera.jpg");
                    //   com.yanshou.lteian.acceptance.fileprovider 为manifest重配置的 domain
                    Uri contentUri = FileProvider.getUriForFile(JobAddActivity.this,"com.yanshou.lteian.acceptance.fileprovider", newFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    startActivityForResult(intent, 100);
                    capterUri = contentUri;
                }else{
                   if(ActivityCompat.shouldShowRequestPermissionRationale(JobAddActivity.this,Manifest.permission.CAMERA)) {

                    }else{
                        ActivityCompat.requestPermissions(JobAddActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_CAMERA);
                    }
                }

            }
        });
        /**
         * 图片选择器
         */
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取权限
                int permissionCheckReadExternalStorage = ContextCompat.checkSelfPermission(JobAddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if(permissionCheckReadExternalStorage == PackageManager.PERMISSION_GRANTED){
                    Matisse.from(JobAddActivity.this)
                            .choose(MimeType.ofAll()) // 选择 mime 的类型
                            .countable(false) // 显示选择的数量
                            .theme(R.style.Matisse_Dracula) // 黑色背景
                            .maxSelectable(1) // 图片选择的最多数量
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f) // 缩略图的比例
                            .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                            .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码，返回图片时使用

                }else{
                    if(ActivityCompat.shouldShowRequestPermissionRationale(JobAddActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    }else{
                        ActivityCompat.requestPermissions(JobAddActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                    }
                }
            }
        });

    }

    /***
     *
     * @param filename
     */
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(JobAddActivity.this,"未获取录音权限，请开启录音权限使用此功能", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(JobAddActivity.this,"未获取数据读取权限，请开启数据读取使用此功能", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case MY_PERMISSIONS_CAMERA:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(JobAddActivity.this,"未获取相机权限，请开启相机权限使用此功能", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    /**
     * 重写Matisse方法
     */
    //这里方法是选择图片后返回的Uri数组
    //返回的Uri数组
    List<Uri> mSelected;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            //用setImageURI将Uri数组第一个Uri显示在ImageView上
            JobImage.setImageURI(mSelected.get(0));
            //将Uri转换为String保存在SharedPreferences中
            ImageUri = String.valueOf(mSelected.get(0));
            Toast.makeText(this, "Set up successfully", Toast.LENGTH_SHORT).show();

        }else if(requestCode!=RESULT_OK&&requestCode!=RESULT_CANCELED){
            //设置失败提示
            JobImage.setImageURI(capterUri);
            Toast.makeText(this, "Set Capture up successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
