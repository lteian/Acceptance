package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

class ListAdapter extends BaseAdapter {
    private List<Loco_loco> list;
    private Context context;

    public ListAdapter(List<Loco_loco> list, Context context){
     this.list = list;
     this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Loco_loco loco = list.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.card_loco,null);
        }

        EditText editTextLocoType = convertView.findViewById(R.id.loco_card_type);
        EditText editTextClassification = convertView.findViewById(R.id.loco_card_classification);
        EditText editTextData = convertView.findViewById(R.id.loco_card_date);

        editTextLocoType.setText("机车型号："+loco.getLoco_type()+" "+loco.getLoco_no());
        editTextClassification.setText("修程："+ loco.getClassification());
        editTextData.setText("交验日期："+ loco.getLoco_date());

        return convertView;
    }

}