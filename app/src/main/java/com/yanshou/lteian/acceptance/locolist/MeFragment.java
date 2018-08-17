package com.yanshou.lteian.acceptance.locolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.layout.QMUIButton;
import com.yanshou.lteian.acceptance.R;

import org.json.JSONArray;

public class MeFragment extends Fragment{
    private QMUIButton loginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,null);

        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = new JSONArray();
                String stringArray = "http://10.107.233.73/index.php/index/login/login/user_id/%E6%9D%A8%E6%B4%8B/user_password/zkm0324";
                jsonArray.getJSONArray();
            }
        });

        return view;
    }

}
