package com.yanshou.lteian.acceptance;

import android.content.Context;
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
        ViewHolder vh;
        Loco_loco loco = list.get(position);

        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.card_loco,null);
            vh.editTextClassification = convertView.findViewById(R.id.loco_card_classification);
            vh.editTextDate = convertView.findViewById(R.id.loco_card_date);
            vh.editTextLocoType = convertView.findViewById(R.id.loco_card_type);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }

        vh.editTextLocoType.setText(String.format("机车型号：%s %s", loco.getLoco_type(), loco.getLoco_no()));
        vh.editTextDate.setText(String.format("交验日期：%s", loco.getLoco_date()));
        vh.editTextClassification.setText(String.format("修程：%s", loco.getClassification()));

        return convertView;
    }

    private class ViewHolder{
        EditText editTextLocoType;
        EditText editTextClassification;
        EditText editTextDate;
    }
}