package com.yanshou.lteian.acceptance.joblist;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.annotation.XmlRes;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yanshou.lteian.acceptance.R;
import com.yanshou.lteian.acceptance.data.LocoJob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<LocoJob> mDatas;

    private LocoInformationActivity mcontext;

    public JobListAdapter(LocoInformationActivity context, List<LocoJob> datas){
        this.mcontext = context;
        mDatas = datas;
    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, String jobPic);
        void onItemLongClick(View view, int position);

    }

    private JobListAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(JobListAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        return  new ViewHolder(mInflater.inflate(R.layout.cardview_job_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final LocoJob locoCategory = mDatas.get(position);
        holder.jobTitle.setText(String.format("活件类型：%s", locoCategory.getJobType()));
        holder.jobPosition.setText(String.format("活件部位：%s",locoCategory.getJobPosition()));
        holder.jobDiscript.setText(String.format("活件描述：%s", locoCategory.getJobDesc()));
        final String pic = locoCategory.getJobPic();
        if(pic.contains("content")){
            holder.jobPic.setImageDrawable(mcontext.getDrawable(R.drawable.ic_image));
//             Toast.makeText(mcontext,getRealFilePath(mcontext,Uri.parse(pic)),Toast.LENGTH_SHORT).show();

            holder.jobPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = LayoutInflater.from(mcontext);
                    View imgEntryView = inflater.inflate(R.layout.dialog_photo_entry, null); // 加载自定义的布局文件
                    final AlertDialog dialog = new AlertDialog.Builder(mcontext).create();
                    final QMUITipDialog Tipdialog = new QMUITipDialog.CustomBuilder(v.getContext()).setContent(R.layout.dialog_photo_entry).create();
                    ImageView img = (ImageView)imgEntryView.findViewById(R.id.large_image);
                    img.setImageURI(Uri.parse(pic));
                    dialog.setView(imgEntryView); // 自定义dialog
                    Tipdialog.setContentView(imgEntryView);
                    Tipdialog.show();
//                    dialog.show();
                    // 点击布局文件（也可以理解为点击大图）后关闭dialog，这里的dialog不需要按钮
                    imgEntryView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            Tipdialog.dismiss();
                        }
                    });
                }
            });
        }

        //点击事件
        if(null != mOnItemClickListener){
            if(null != holder.jobPic)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos, pic);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView jobTitle;
        TextView jobPosition;
        TextView jobDiscript;
        ImageView jobPic;


        public ViewHolder(View itemView) {
            super(itemView);

            jobPosition = itemView.findViewById(R.id.job_card_position);
            jobTitle = (TextView) itemView.findViewById(R.id.job_card_title);
            jobPic = (ImageView) itemView.findViewById(R.id.job_card_pic);
            jobDiscript = (TextView) itemView.findViewById(R.id.job_card_more);
        }

    }


}