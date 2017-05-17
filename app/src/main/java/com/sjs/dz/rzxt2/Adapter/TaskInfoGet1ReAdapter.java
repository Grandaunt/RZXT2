package com.sjs.dz.rzxt2.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjs.dz.rzxt2.DB.MEDIA;
import com.sjs.dz.rzxt2.R;
import com.sjs.dz.rzxt2.Utils.FileUtils;
import com.sjs.dz.rzxt2.Utils.MyUtils;

import java.io.File;

/**
 * Created by SJS on 2017/1/4.
 */


public class TaskInfoGet1ReAdapter extends RecyclerView.Adapter<TaskInfoGet1ReAdapter.ViewHolder> {
    private String TAG = this.getClass().getSimpleName();
    private LayoutInflater mInflater;
    private MEDIA medialist;
    private String imageUrl="";
    private  String value;
    private String[] oneGetInfo;
    private String[] type;
//    private String[] name;
    //    private ViewHolder mholder;
    private String task_no;
    private  Context mContext;
    private SharedPreferences sharedPrefs;
    private String userAcc;
    public TaskInfoGet1ReAdapter(Context context, MEDIA mediaList, String task_no) {
        this.mInflater = LayoutInflater.from(context);
        this.medialist = mediaList;
        this.mContext=context;
        this.task_no=task_no;
        value= medialist.mt_u_7_desc;
        Log.i(TAG,value.toString());
        oneGetInfo = value.split("%");
        Log.i(TAG,oneGetInfo.length+"");
        Log.i(TAG,oneGetInfo.toString());
        sharedPrefs =context.getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        userAcc = sharedPrefs.getString("USER_ACCOUNT", "null");
    }

    /**
     * item显示类型
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_info_get,parent,false);
        int screenWidth = MyUtils.getScreenMetrics(mInflater.getContext()).widthPixels;
        int space = (int) MyUtils.dp2px(mInflater.getContext(), 30f);
        int mImageWidth = (screenWidth - space) / 4;
        LinearLayout item=(LinearLayout)view.findViewById(R.id.item_get);
        item.setLayoutParams(new RecyclerView.LayoutParams(mImageWidth,250));
//        View view = mInflater.inflate(R.layout.item_info_download), parent, false);
        //view.setBackgroundColor(Color.RED);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
//        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                HomeActivity.this).inflate(R.layout.item_home, parent,
//                false));
//        return holder;
    }



    /**
     * 数据的绑定显示
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


//        Log.i(TAG,"onBindViewHolder+position="+position);
        //初始化数据
        type= oneGetInfo[position].split("@");
//        Log.i(TAG,"DownInfo="+GetInfo[position]);
//        Log.i(TAG,"type[0]="+type[0]);
//        Log.i(TAG,"type[1]="+type[1]);
        String path   =Environment.getExternalStorageDirectory().getAbsolutePath() +  File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+type[0]+"/";;
        File f = new File(path, type[1]);
        if (!f.exists()) {
            Log.i(TAG,type[1]+"照片不存在");
        }
        else{
//              mSignBitmap = (Bitmap) object;
//              String sign_dir = Environment.getExternalStorageDirectory()
//                      + "/XHLS/" + UserID + "/" + event_id + "/QM/";
//              String path = "qm.png";
//              signPath = FileUtils.saveByteBitmap(sign_dir, path, mSignBitmap);
//
            Bitmap zoombm = FileUtils.getCompressBitmap(path+type[1]);
//              //ivSign.setImageBitmap(mSignBitmap);
//              ivSign.setImageBitmap(zoombm);
            holder.im.setImageBitmap(zoombm);
        }
//        holder.tv.setText(type[1]);

        // 如果设置了回调，则设置点击事件

        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
//                    int poss=pos+1;
//                    if(poss==oneGetInfo.length){
//                        MediaPro pro = new MediaPro(mContext);
//                        medialist.mt_u_8_desc=value+"%"+type[0]+"@"+poss+".JPG";
//                        pro.addMediaGetDesc(medialist);
//                    }
                    type= oneGetInfo[pos].split("@");
                    String num = type[0];
                    Log.i(TAG,"String num ="+num);
                    mOnItemClickLitener.onItemClick(holder.itemView, pos,num);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    type= oneGetInfo[pos].split("@");
                    String name = type[0];
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos,name);
                    return false;
                }
            });
        }
        else{
            Log.i(TAG,"mOnItemClickLitener = null");
        }
    }

    @Override
    public int getItemCount() {

        return oneGetInfo.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView im ;
        public TextView tv;

        public ViewHolder(View view) {
            super(view);
            im = (ImageView)view.findViewById(R.id.item_im_get);
//            tv = (TextView) view.findViewById(R.id.item_tv_get);
        }
    }

    public void addData(int position) {
//        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
//        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position, String numm);
        void onItemLongClick(View view, int position, String numm);
    }

    private OnItemClickLitener mOnItemClickLitener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
        this.mOnItemLongClickLitener =mOnItemLongClickLitener;

    }



}