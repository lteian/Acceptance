package com.yanshou.lteian.acceptance.locolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoLoco;

import java.text.SimpleDateFormat;
import java.util.List;

class LocoListAdapter extends RecyclerView.Adapter<LocoListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<LocoLoco> mDatas;

    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Long locoId);
        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    LocoListAdapter(List<LocoLoco> datas){
     mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        return  new ViewHolder(mInflater.inflate(R.layout.cardview_loco_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final LocoLoco locoCategory = mDatas.get(position);
        holder.locoNoTextView.setText(String.format("机车型号：%s %s", locoCategory.getLocoType(), locoCategory.getLocoNumber()));
        holder.locoClassificationTextView.setText(String.format("修程：%s", locoCategory.getLocoClassification()));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.locoDateTextView.setText(String.format("交验时间：%s", sdf.format(locoCategory.getLocoDate())));

        if(null != mOnItemClickListener){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    Long locoId = locoCategory.get_id();
                    mOnItemClickListener.onItemClick(holder.itemView, pos, locoId);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView locoNoTextView;
        TextView locoClassificationTextView;
        TextView locoDateTextView;
        CardView locoIdCardView;


        ViewHolder(View itemView) {
            super(itemView);

            locoNoTextView = itemView.findViewById(R.id.loco_no_textView);
            locoDateTextView = itemView.findViewById(R.id.loco_date_textView);
            locoClassificationTextView = itemView.findViewById(R.id.loco_classification_textView);
            locoIdCardView = itemView.findViewById(R.id.loco_id_cardview);
        }

    }


}