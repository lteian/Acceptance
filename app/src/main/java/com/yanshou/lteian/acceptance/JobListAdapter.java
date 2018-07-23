package com.yanshou.lteian.acceptance;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        return  new ViewHolder(mInflater.inflate(R.layout.cardview_job_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocoAcceptance locoCategory = mDatas.get(position);
        holder.jobTitle.setText(String.format("活件类型：%s", locoCategory.getAcceptanceType()));
        holder.jobDiscript.setText(String.format("活件描述：%s", locoCategory.getAcceptanceDesc()));
        holder.jobPic.setImageURI(Uri.parse(locoCategory.getAcceptancePic()));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView jobTitle;
        TextView jobDiscript;
        ImageView jobPic;


        public ViewHolder(View itemView) {
            super(itemView);

            jobTitle = (TextView) itemView.findViewById(R.id.job_card_title);
            jobPic = (ImageView) itemView.findViewById(R.id.job_card_pic);
            jobDiscript = (TextView) itemView.findViewById(R.id.job_card_more);
        }

    }
}