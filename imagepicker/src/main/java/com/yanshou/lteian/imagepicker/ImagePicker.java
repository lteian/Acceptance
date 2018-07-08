package com.yanshou.lteian.imagepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ImagePicker extends AppCompatActivity{
    //    **定义常量
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int REQUEST_CROP =3;

    public static final String IMAGE_UPSPECIFIED = "image/*";

    private ImageButton mPictureIb;

    private File mImageFile;
    //        2.图片选择器
    mPictureIb = findViewById(R.id.ib_picture);
        mPictureIb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickPicker(v);
        }
    });
}

    public void onClickPicker(View v){
        new AlertDialog.Builder(this).setTitle("选择照片").setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    selectCamera();
                }else{
                    selectAlbum();
                }
            }
        }).create().show();
    }

    private void selectAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UPSPECIFIED);
        startActivityForResult(albumIntent, REQUEST_ALBUM);
    }

    private void selectCamera() {
        createImageFile();
        if(!mImageFile.exists()){
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(cameraIntent, REQUEST_ALBUM);
    }

    private void createImageFile() {
        mImageFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try{
            mImageFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"出错啦",Toast.LENGTH_SHORT).show();
        }
    }

    private void cropImage(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UPSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX",4);
        intent.putExtra("aspectY",3);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(intent,REQUEST_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK != resultCode) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAMERA:
                cropImage(Uri.fromFile(mImageFile));
                break;
            case REQUEST_ALBUM:
                createImageFile();
                if(!mImageFile.exists()){
                    return;
                }

                Uri uri = data.getData();
                if (uri != null) {
                    cropImage(uri);
                }
                break;

            case REQUEST_CROP:
                mPictureIb.setImageURI(Uri.fromFile(mImageFile));
                break;
        }
}
