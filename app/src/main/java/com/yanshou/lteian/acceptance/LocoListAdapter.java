package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class LocoListAdapter extends RecyclerView.Adapter<LocoListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<LocoLoco> mDatas;

    private Context context;

    public LocoListAdapter(List<LocoLoco> datas){
     mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        return  new ViewHolder(mInflater.inflate(R.layout.loco_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocoLoco locoCategory = mDatas.get(position);
        holder.locoNoTextView.setText(String.format("机车型号：%s %s", locoCategory.getLocoType(), locoCategory.getLocoNumber()));
        holder.locoClassificationTextView.setText(String.format("修程：%s", locoCategory.getLocoClassification()));
        holder.locoDateTextView.setText(String.format("交验时间：%s", locoCategory.getLocoDate()));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView locoNoTextView;
        TextView locoClassificationTextView;
        TextView locoDateTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            locoNoTextView = (TextView) itemView.findViewById(R.id.loco_no_textView);
            locoDateTextView = (TextView) itemView.findViewById(R.id.loco_date_textView);
            locoClassificationTextView = (TextView) itemView.findViewById(R.id.loco_classification_textView);
        }

    }
}