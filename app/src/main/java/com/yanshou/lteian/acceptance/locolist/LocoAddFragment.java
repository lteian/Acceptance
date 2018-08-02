package com.yanshou.lteian.acceptance.locolist;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.yanshou.lteian.acceptance.joblist.LocoInformationActivity;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoLoco;
import com.yanshou.lteian.acceptance.data.LocoLocoDao;
import com.yanshou.lteian.classificationkeyboardview.ClassificationKeyboardViewEditText;

import org.w3c.dom.Text;

public class LocoAddFragment extends Fragment{
    private Button button;
    private EditText locoCF;
    LocoLoco loco = new LocoLoco();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_loco_add,null);
        locoCF =view.findViewById(R.id.loco_classification_editText);
        locoCF.setTextDirection(View.TEXT_DIRECTION_RTL);
        locoCF.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(locoCF.hasFocus()){
                    locoCF.setText("");
                }
            }
        });

        //设置itemlist内容
        final QMUICommonListItemView locoTypeListView = view.findViewById(R.id.loco_type_listview);
        locoTypeListView.setText("车型");
        locoTypeListView.setDetailText("请选择");
        locoTypeListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = getResources().getStringArray(R.array.LocomotiveModel);
                QMUIDialog.CheckableDialogBuilder locoCheckableDialog = new QMUIDialog.CheckableDialogBuilder(getContext());
                locoCheckableDialog.setCheckedIndex(1).addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        locoTypeListView.setDetailText(items[which]);
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });

        //设置车号EditText
        QMUICommonListItemView locoNoText = view.findViewById(R.id.loco_no_text);
        locoNoText.setText(getString(R.string.loco_number));
        final EditText locoNoEditText = new EditText(getContext());
        locoNoEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        locoNoEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        locoNoEditText.setGravity(Gravity.FILL);
        locoNoEditText.setBackground(getResources().getDrawable(R.drawable.qmui_divider_bottom_bitmap));
        locoNoEditText.setFocusable(true);
        locoNoEditText.setBackgroundColor(Color.WHITE);
        locoNoEditText.setTextDirection(View.TEXT_DIRECTION_RTL);
        locoNoEditText.setHint("请输入4位机车号");
        locoNoEditText.setTextSize(16);
        locoNoText.addAccessoryCustomView(locoNoEditText);
        locoNoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (locoNoEditText.hasFocus()) {
                    locoNoEditText.setText("");
                }
            }
        });


        //  按钮传参
        button = view.findViewById(R.id.add_loco_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                //        提取机车型号、车号、修程，保存至post_loco数组
                //1.提取机车型号
                loco.setLocoType(String.valueOf(locoTypeListView.getDetailText()));
                //        2.提取车号
                int length = locoNoEditText.getText().length();
                String locoNumber = String.valueOf(locoNoEditText.getText());
                for(int i = length; i<4; i++) {
                    locoNumber = "0" + locoNumber;
                }
                locoNoEditText.setText(locoNumber);
                loco.setLocoNumber(String.valueOf(locoNoEditText.getText()));
//                          3.提取修程

                loco.setLocoClassification(String.valueOf(locoCF.getText()));
//                4.保存机车数据
                //               保存机车信息
                Long locoId = locoSave(loco);
//                5.传参
                Intent intent = new Intent(getActivity(), LocoInformationActivity.class);
                intent.putExtra("locoId",locoId);
                startActivity(intent);


            }
        });

        return view;
    }


    /**
     * 保存机车信息
     * @param loco
     * @return Long 机车id
     */
    private Long locoSave(LocoLoco loco) {
//                保存机车信息，有个if，判断机车是否存在
        LocoLocoDao locoDao = new LocoLocoDao(getActivity());
        loco.setLocoDate(System.currentTimeMillis());
        Long locoId = locoDao.findid(loco);
        if(locoId == null){
            locoId = locoDao.add(loco);
            Toast.makeText(getActivity(),"机车信息添加成功，编号："+locoId,Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(getActivity(), "找到以下机车信息，编号：" + locoId, Toast.LENGTH_SHORT).show();
        }
        return locoId;
    }

}
