package com.yanshou.lteian.acceptance.locolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoLoco;

import java.text.SimpleDateFormat;
import java.util.List;

class LocoQmuiAdapter extends BaseAdapter {
    private List<LocoLoco> mData;
    private Context mContext;
    public LocoQmuiAdapter(Context mContext, List<LocoLoco> mList){
        super();
        this.mData = mList;
        this.mContext = mContext;
    }

    public void setData(List<LocoLoco> mData){
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.qmui_loco_list,null);
        QMUICommonListItemView listItemView = view.findViewById(R.id.loco_id_cardview);
        LocoLoco locoCategory = mData.get(position);
        listItemView.setText(locoCategory.getLocoType()+" "+ locoCategory.getLocoNumber()+" "+locoCategory.getLocoClassification());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        listItemView.setDetailText(sdf.format(locoCategory.getLocoDate()));
        return listItemView;
    }
}