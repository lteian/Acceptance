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

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoJob;
import com.yanshou.lteian.acceptance.data.LocoJobDao;
import com.yanshou.lteian.acceptance.data.LocoLoco;

import java.text.SimpleDateFormat;
import java.util.List;

class LocoQmuiAdapter extends RecyclerView.Adapter<LocoQmuiAdapter.ViewHolder> {

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

    LocoQmuiAdapter(List<LocoLoco> datas){
     mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        return  new ViewHolder(mInflater.inflate(R.layout.qmui_loco_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final LocoLoco locoCategory = mDatas.get(position);
        LocoJobDao jobDao = new LocoJobDao(context);
        String jobCount = jobDao.countJob(locoCategory.get_id());
        holder.locoIdCardView.setText(locoCategory.getLocoType()+" "+locoCategory.getLocoNumber() +" "+locoCategory.getLocoClassification());
        holder.locoIdCardView.setDetailText(jobCount);
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        holder.locoDateTextView.setText(String.format("交验时间：%s", sdf.format(locoCategory.getLocoDate())));

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

        QMUICommonListItemView locoIdCardView;


        ViewHolder(View itemView) {
            super(itemView);

            locoIdCardView = itemView.findViewById(R.id.loco_id_cardview);
        }

    }


}