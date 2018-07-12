package com.yanshou.lteian.acceptance;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;

public class KeyboardUtils {
    private Context mContext;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;

    public KeyboardUtils(Context context){
        mContext = context;

        mKeyboard = new Keyboard(mContext,R.xml.classification_add_keyboard);

        try {
            mKeyboardView = ((Activity)context).findViewById(R.id.classification_keyboardview);
            mKeyboardView.setKeyboard(mKeyboard);
            mKeyboardView.setPreviewEnabled(false);

//            监听
            mKeyboardView.setOnKeyboardActionListener(mListener);
        }catch (Exception e){
            Log.e("sun","初始化失败");
        }
    }

    private KeyboardView.OnKeyboardActionListener mListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
            Log.e("sun","onPress=========："+primaryCode);
        }

        @Override
        public void onRelease(int primaryCode) {
            Log.e("sun","onRelease=========："+primaryCode);
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Log.e("sun","onRelease=========："+primaryCode+" keycodes" +keyCodes);
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };
}
