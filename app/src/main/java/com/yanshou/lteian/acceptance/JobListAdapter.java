package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<LocoAcceptance> mDatas;

    private Context context;

    public JobListAdapter(List<LocoAcceptance> datas){
     mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        return  new ViewHolder(mInflater.inflate(R.layout.loco_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocoAcceptance locoCategory = mDatas.get(position);
        holder.locoNoTextView.setText(String.format("活件类型：%s %s", locoCategory.getAcceptanceType()));
        holder.locoClassificationTextView.setText(String.format("活件描述：%s", locoCategory.getAcceptanceDesc()));
        holder.locoDateTextView.setText(String.format("附件：%s", locoCategory.getAcceptancePic()));
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