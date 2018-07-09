package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<LocoLoco> mDatas;

    private Context context;

    public CardViewAdapter(List<LocoLoco> datas){
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
        holder.locoNoTextView.setText(locoCategory.getLoco_no());
        holder.locoClassificationTextView.setText(locoCategory.getLoco_classification());
        holder.locoDateTextView.setText(locoCategory.getLoco_date());
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