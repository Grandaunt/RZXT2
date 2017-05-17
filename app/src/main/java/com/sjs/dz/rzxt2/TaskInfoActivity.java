package com.sjs.dz.rzxt2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjs.dz.rzxt2.Adapter.TaskInfoDownReAdapter;
import com.sjs.dz.rzxt2.Adapter.TaskInfoGet1ReAdapter;
import com.sjs.dz.rzxt2.Adapter.TaskInfoGet2ReAdapter;
import com.sjs.dz.rzxt2.Adapter.TaskInfoGet3ReAdapter;
import com.sjs.dz.rzxt2.DB.MEDIA;
import com.sjs.dz.rzxt2.DB.MediaPro;
import com.sjs.dz.rzxt2.DB.ServerBean;
import com.sjs.dz.rzxt2.DB.TaskPro;
import com.sjs.dz.rzxt2.Utils.FileUtils;
import com.sjs.dz.rzxt2.view.ActionSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PhotoPreviewActivity;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class TaskInfoActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private TextView tv_task_info_toolbar,tv_com_info_name,tv_com_info_addr,tv_com_info_tel,tv_com_info_con_tel,tv_com_con_name;
    private EditText com_info_memo;
    private Button saveBtn;
    private RecyclerView Down_RecyclerView,Get_RecyclerView1,Get_RecyclerView2,Get_RecyclerView3;
    private LinearLayoutManager mLayoutManager;
    private TaskInfoDownReAdapter mDownAdapter;
    private ImageView im_com_info_visibility,im_down_visibility,im_get_visibility1,im_get_visibility2,im_get_visibility3,im_memo_visibility;
    private boolean isVisible = true;
    private LinearLayout ly_com_info,ly_download,ly_memo,ly_get1,ly_get2,ly_get3;
    private String URL="";
    private String[] type7img, type8img,type9img;
    private int  sumLeng ,fen;
    private SharedPreferences sharedPrefs;
    private ProgressDialog progressDialog;
    private ProgressDialog UpDialog;
    private String AUTH_TOKEN;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    private int mPhotoname;
    private MEDIA mMedialist;
    private String task_no;
    private  MediaPro mediapro;
    private String userAcc;
    private ArrayList<HashMap<String, String>> task_info_list;
    private int uploadi;
    private String type;
    private boolean photoflag=false;
    private  int Dposition;
    private  String Dname;
    private String DdownPath;


    private TaskInfoGet1ReAdapter mGetAdapter1;
    private TaskInfoGet2ReAdapter mGetAdapter2;
    private TaskInfoGet3ReAdapter mGetAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
        x.view().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.task_info_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_left);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TaskInfoActivity.this, MainActivity.class);
                startActivity(intent1);

//                finish();
            }
        });

         sharedPrefs =getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        userAcc = sharedPrefs.getString("USER_ACCOUNT", "null");
        im_com_info_visibility=(ImageView)findViewById(R.id.com_info_visibility);
        im_down_visibility=(ImageView)findViewById(R.id.down_info_visibility);
        im_get_visibility1=(ImageView)findViewById(R.id.get_info_visibility1);
        im_get_visibility2=(ImageView)findViewById(R.id.get_info_visibility2);
        im_get_visibility3=(ImageView)findViewById(R.id.get_info_visibility3);
        im_memo_visibility=(ImageView)findViewById(R.id.memo_info_visibility);
        ly_com_info=(LinearLayout) findViewById(R.id.ly_com_info);
        ly_download=(LinearLayout) findViewById(R.id.ly_download);
        ly_memo=(LinearLayout) findViewById(R.id.ly_com_info_memo);
        ly_get1=(LinearLayout) findViewById(R.id.ly_get1);
        ly_get2=(LinearLayout) findViewById(R.id.ly_get2);
        ly_get3=(LinearLayout) findViewById(R.id.ly_get3);
        im_com_info_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_com_info.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_com_info.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
                im_down_visibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVisible) {
                            isVisible = false;
                            ly_download.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                        } else {
                            ly_download.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                            isVisible = true;
                        }
                    }
                });
        im_get_visibility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get1.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get1.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
        im_get_visibility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get2.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get2.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
        im_get_visibility3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get3.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get3.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
     im_memo_visibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVisible) {
                            isVisible = false;
                            ly_memo.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                        } else {
                            ly_memo.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                            isVisible = true;
                        }
                    }
                });
        Intent i = getIntent();
        TaskPro taskPro = new TaskPro(TaskInfoActivity.this);
        task_no=i.getStringExtra("task_no");
        task_info_list = taskPro.showTaskInfo(task_no);

//        tv_task_info_toolbar =(TextView)findViewById(R.id.task_info_toolbar_tv);
//        tv_task_info_toolbar.setText(task_info_list.get(0).get("task_com_name"));
        tv_com_info_name =(TextView)findViewById(R.id.com_info_name);
        tv_com_info_addr =(TextView)findViewById(R.id.com_info_addr);
        tv_com_info_tel =(TextView)findViewById(R.id.com_info_com_tel);
        tv_com_info_con_tel =(TextView)findViewById(R.id.com_info_con_tel);
        tv_com_con_name =(TextView)findViewById(R.id.com_info_con_name);
        com_info_memo =(EditText)findViewById(R.id.info_memo);
        Log.i(TAG,task_info_list.size()+"");
        Log.i(TAG,task_info_list.get(0)+"");
        Log.i(TAG,task_info_list.get(0).get("task_com_addr")+"");
        tv_com_info_name.setText(task_info_list.get(0).get("task_com_name"));
        tv_com_info_addr.setText(task_info_list.get(0).get("task_com_addr"));
        tv_com_info_tel.setText(task_info_list.get(0).get("task_com_tel"));
        tv_com_con_name .setText(task_info_list.get(0).get("task_com_con_name"));
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        com_info_memo.setText(task_info_list.get(0).get("task_note"));

        //获取数据库中下载的信息
         mediapro = new MediaPro(TaskInfoActivity.this);
        mMedialist= taskPro.getMedialistDown(task_no);
        DownReView(mMedialist);

        GetReView1(mMedialist);
        GetReView2(mMedialist);
        GetReView3(mMedialist);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadHttp(URL+"/MVNFHM/appInterface/appUploadFile");

            }
        });

    }



    private void DownReView(MEDIA  medialist) {

        //开始设置RecyclerView
        Down_RecyclerView=(RecyclerView)findViewById(R.id.recyclerview_info_down);
        //设置固定大小
        Down_RecyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
//        Down_RecyclerView.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
        Down_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mDownAdapter = new TaskInfoDownReAdapter(TaskInfoActivity.this,medialist,task_no);
        Down_RecyclerView.setAdapter(mDownAdapter);
        mDownAdapter.setOnItemClickLitener(new TaskInfoDownReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view,  int position,  String name,  String downPath)
            {Dposition=position;
             Dname=name;
             DdownPath=downPath;
                String path= Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Down/";
                if(!FileUtils.isFileExist(path)){
                    try {
                        File file= FileUtils.createSDDir(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                String cpath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Down/"+Dname;
                Log.i(TAG,"cpath="+cpath);
//                                        String cpath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
//                                                + "Download/qqmail/";
                if(!FileUtils.isFileExist(cpath)){
                    downloadFile(URL+"/MVNFHM/appInterface/appDownFile",Dname,path,DdownPath);
//                    Toast.makeText(TaskInfoActivity.this,"本地无该文件",Toast.LENGTH_SHORT).show();
                }
                else{
                    Uri uri = Uri.parse(cpath);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "*/*");
                    startActivity(intent);
                }
                Log.i(TAG,"ActionSheetDialog查看");
//                new ActionSheetDialog(TaskInfoActivity.this).builder()
//                        .setCanceledOnTouchOutside(true)
//                        .addSheetItem("下载", ActionSheetDialog.SheetItemColor.DEFAULT,
//                                new ActionSheetDialog.OnSheetItemClickListener() {
//                                    @Override
//                                    public void onClick(int which) {
//                                        Log.i(TAG,"ActionSheetDialog文件下载");
//                                        String path= Environment.getExternalStorageDirectory().getAbsolutePath()
//                                                + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Down/";
//                                        if(!FileUtils.isFileExist(path)){
//                                            try {
//                                                File file= FileUtils.createSDDir(path);
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//
//                                       downloadFile(URL+"/MVNFHM/appInterface/appDownFile",Dname,path,DdownPath);
//                                    }
//                                })
//                        .addSheetItem("查看", ActionSheetDialog.SheetItemColor.DEFAULT,
//                                new ActionSheetDialog.OnSheetItemClickListener() {
//                                    @Override
//                                    public void onClick(int which) {
//                                        String cpath = Environment.getExternalStorageDirectory().getAbsolutePath()
//                                                + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Down/"+Dname;
//                                        Log.i(TAG,"cpath="+cpath);
////                                        String cpath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
////                                                + "Download/qqmail/";
//                                        if(!FileUtils.isFileExist(cpath)){
//                                            Toast.makeText(TaskInfoActivity.this,"本地无该文件",Toast.LENGTH_SHORT).show();
//                                        }
//                                        else{
//                                            /***
//                                             * 一、music：
//
//                                             String file_type = "audio/*";
//
//
//                                             二、movice：
//
//                                             String file_type = "video/*";
//
//
//                                             三、pdf：
//
//                                             String file_type = "application/*";
//
//
//                                             四、picture：
//
//                                             String file_type = "image/*";
//                                             */
//                                            Uri uri = Uri.parse(cpath);
////                                            Uri uri = Uri.parse(cpath+"面试通知（北京）");//调用系统自带的播放器
////                    Uri uri = Uri.parse("sdcard/VideoRecorderTest/"+FileToStr(videoList)[position]);//调用系统自带的播放器
//                                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                                            intent.setDataAndType(uri, "*/*");
//                                            startActivity(intent);
//                                        }
//                                        Log.i(TAG,"ActionSheetDialog查看");
//                                    }
//                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position, String numm, String path) {

            }


        });


    }

    private void GetReView1(MEDIA  medialist) {

        //开始设置RecyclerView
        Get_RecyclerView1=(RecyclerView)findViewById(R.id.recyclerview_info_get1);
        //设置固定大小
        Get_RecyclerView1.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
        Get_RecyclerView1.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
//        Get_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mGetAdapter1 = new TaskInfoGet1ReAdapter(TaskInfoActivity.this,medialist,task_no);
        Get_RecyclerView1.setAdapter(mGetAdapter1);
        mGetAdapter1.setOnItemClickLitener(new TaskInfoGet1ReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, final int position, String num)
            {   type=num;
                mPhotoname=position ;
                new ActionSheetDialog(TaskInfoActivity.this).builder()
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Log.i(TAG,"ActionSheetDialog拍照");
                                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                                    }
                                })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);


                                    }
                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position,String num)
            {

                PhotoInfo ptinfo = new PhotoInfo();
                Log.i(TAG,"461 mPhotoname ="+mPhotoname);
                ptinfo.setPhotoPath( Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+num+"/" + mPhotoname+".JPG");
                //mPhotoname=0@qwe
                Log.i(TAG,Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+ num+"/" + mPhotoname+".JPG");
                List<PhotoInfo> PhotoList= new ArrayList<>();
                PhotoList.add(0, ptinfo);

                Intent intent = new Intent(TaskInfoActivity.this, PhotoPreviewActivity.class);
                intent.putExtra("photo_list", (Serializable) PhotoList);
                startActivity(intent);
            }
        });

    }
    private void GetReView2(MEDIA  medialist) {

        //开始设置RecyclerView
        Get_RecyclerView2=(RecyclerView)findViewById(R.id.recyclerview_info_get2);
        //设置固定大小
        Get_RecyclerView2.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
        Get_RecyclerView2.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
//        Get_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mGetAdapter2 = new TaskInfoGet2ReAdapter(TaskInfoActivity.this,medialist,task_no);
        Get_RecyclerView2.setAdapter(mGetAdapter2);
        mGetAdapter2.setOnItemClickLitener(new TaskInfoGet2ReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, final int position, String num)
            {   type=num;
                mPhotoname=position ;
                new ActionSheetDialog(TaskInfoActivity.this).builder()
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Log.i(TAG,"ActionSheetDialog拍照");
                                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                                    }
                                })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);


                                    }
                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position,String num)
            {

                PhotoInfo ptinfo = new PhotoInfo();
                Log.i(TAG,"461 mPhotoname ="+mPhotoname);
                ptinfo.setPhotoPath( Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+num+"/" + mPhotoname+".JPG");
                //mPhotoname=0@qwe
                Log.i(TAG,Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+ num+"/" + mPhotoname+".JPG");
                List<PhotoInfo> PhotoList= new ArrayList<>();
                PhotoList.add(0, ptinfo);

                Intent intent = new Intent(TaskInfoActivity.this, PhotoPreviewActivity.class);
                intent.putExtra("photo_list", (Serializable) PhotoList);
                startActivity(intent);
            }
        });

    }
    private void GetReView3(MEDIA  medialist) {

        //开始设置RecyclerView
        Get_RecyclerView3=(RecyclerView)findViewById(R.id.recyclerview_info_get3);
        //设置固定大小
        Get_RecyclerView3.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
        Get_RecyclerView3.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
//        Get_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mGetAdapter3 = new TaskInfoGet3ReAdapter(TaskInfoActivity.this,medialist,task_no);
        Get_RecyclerView3.setAdapter(mGetAdapter3);
        mGetAdapter3.setOnItemClickLitener(new TaskInfoGet3ReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view,  int position, String num)
            {   type=num;
                Log.i(TAG,"String position ="+position);
                mPhotoname=position;
                type=num;
                Log.i(TAG,"504 mPhotoname ="+mPhotoname);
                new ActionSheetDialog(TaskInfoActivity.this).builder()
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Log.i(TAG,"ActionSheetDialog拍照");
                                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                                    }
                                })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);


                                    }
                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position,String num)
            {
                PhotoInfo ptinfo = new PhotoInfo();
                Log.i(TAG,"461 mPhotoname ="+mPhotoname);
                ptinfo.setPhotoPath( Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+num+"/" + mPhotoname+".JPG");
                //mPhotoname=0@qwe
                Log.i(TAG,Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+ num+"/" + mPhotoname+".JPG");
                List<PhotoInfo> PhotoList= new ArrayList<>();
                PhotoList.add(0, ptinfo);

                Intent intent = new Intent(TaskInfoActivity.this, PhotoPreviewActivity.class);
                intent.putExtra("photo_list", (Serializable) PhotoList);
                startActivity(intent);
            }
        });

    }
    private void downloadFile(String url,String name, String Filepath,String downPath) {
         progressDialog = new ProgressDialog(this);
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("AUTH_TOKEN", AUTH_TOKEN);
        requestParams.addParameter("FILE_NAME", name);
        requestParams.addParameter("FILE_PATH", downPath);
        requestParams.setSaveFilePath(Filepath+name);
        Log.i(TAG,"requestParams="+requestParams);
        Log.i(TAG,"Filepath+name="+Filepath+name);
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("努力下载中。。。");
                progressDialog.show();
                progressDialog.setMax((int) total);
                progressDialog.setProgress((int) current);
            }

            @Override
            public void onSuccess(File result) {
                String cpath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Down/"+Dname;
                Log.i(TAG,"cpath="+cpath);
                Log.i(TAG,"result="+result);
//                                        String cpath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
//                                                + "Download/qqmail/";
                if(!FileUtils.isFileExist(cpath)){
                    Toast.makeText(TaskInfoActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                }
                else{
                    /***
                     * 一、music：

                     String file_type = "audio/*";


                     二、movice：

                     String file_type = "video/*";


                     三、pdf：

                     String file_type = "application/*";


                     四、picture：

                     String file_type = "image/*";
                     */
                    Toast.makeText(TaskInfoActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse(cpath);
//                                            Uri uri = Uri.parse(cpath+"面试通知（北京）");//调用系统自带的播放器
//                    Uri uri = Uri.parse("sdcard/VideoRecorderTest/"+FileToStr(videoList)[position]);//调用系统自带的播放器
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "*/*");
                    startActivity(intent);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(TaskInfoActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                Toast.makeText(TaskInfoActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void uploadHttp(String url){
        UpDialog  = new ProgressDialog(this);
        UpDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        UpDialog.setMessage("努力上传中。。。");
        UpDialog.show();
        UpDialog.setMax((int) 100);
        UpDialog.setProgress((int) 0);
        UpDialog.setCancelable(false);//弹框弹出时页面无法点击
        type7img =mMedialist.mt_u_7_desc.split("%");
        type8img =mMedialist.mt_u_8_desc.split("%");
        type9img =mMedialist.mt_u_9_desc.split("%");
        sumLeng=type7img.length+type8img.length+type9img.length-3;
        if(!TextUtils.isEmpty(com_info_memo.getText())){
            sumLeng++;
        }
         fen=100/sumLeng;
        for (int imagei = 0; imagei < type7img.length-1; imagei++) {
            String[] img_desc7 = type7img[imagei].split("@");
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+img_desc7[0] +"/"+ img_desc7[1];
            Log.i(TAG, "path=" + path);
            File f = new File(path);
            if (f.exists()) {
                RequestParams params = new RequestParams(url);
                //支持断点续传
                params.setAutoResume(true);
                params.setMultipart(true);
                /**
                 * 多媒体文件上传
                 *
                 * @param AUTH_TOKEN 手机令牌
                 * @param FILE 文件对象
                 * @param APPLY_ID 多媒体文件类型，0-照片，1-录像
                 * @param TASK_NO 信贷员ID
                 * @param TASK_ITEM_NO 任务号，客户基本信息的任务号为info
                 * @param FILE_TYPE 文件描述，标明文件的位置及说明
                 * @return
                 * setAsJsonContent（boolean is）
                 * 如果有中文，要进行URLEncoder.encode方法
                 *params.addBodyParameter("deviceIntoTime",URLEncoder.encode(deviceIntoTime, "utf-8"));
                 */
                params.addBodyParameter("AUTH_TOKEN", AUTH_TOKEN);
                params.addBodyParameter("FILE", new File(path));
                params.addBodyParameter("APPLY_ID",task_info_list.get(0).get("apply_id"));
                params.addBodyParameter("TASK_NO", task_no);
                params.addBodyParameter("TASK_ITEM_NO", task_info_list.get(0).get("task_item_no"));
                params.addBodyParameter("FILE_TYPE", img_desc7[0]);
                Log.i(TAG, "629params:" + params);
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,result);
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                         ServerBean serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                        int err = serverBean.getError();
                        String msg = serverBean.getMsg();
                        if (err == 000) {

                                    uploadi++;
                                } else {
                                    Toast.makeText(TaskInfoActivity.this,msg+"请检查网络",Toast.LENGTH_SHORT).show();
                                }


                        UpDialog.setProgress((int) fen*uploadi);
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG,"uploadi="+uploadi);
                       if(uploadi==sumLeng){

                           TaskPro pro= new TaskPro(TaskInfoActivity.this);
                           String okstr =pro.updateStatus(task_no,"2");
                           Log.i(TAG,"okstr="+okstr);
                    }
                      Intent intent = new Intent(TaskInfoActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            } else {
                if(UpDialog!=null && UpDialog.isShowing()){
                    UpDialog.dismiss();
                }
                Toast.makeText(TaskInfoActivity.this,"照片上传失败，不存在",Toast.LENGTH_SHORT).show();
                Log.i(TAG, path + "照片上传失败，不存在");
            }
        }

        for (int imagei = 0; imagei < type8img.length-1; imagei++) {
            String[] img_desc8 = type8img[imagei].split("@");
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+img_desc8[0]+"/" + img_desc8[1];
            Log.i(TAG, "path=" + path);
            File f = new File(path);
            if (f.exists()) {
                RequestParams params = new RequestParams(url);
                //支持断点续传
                params.setAutoResume(true);
                params.setMultipart(true);
                /**
                 * 多媒体文件上传
                 *
                 * @param AUTH_TOKEN 手机令牌
                 * @param FILE 文件对象
                 * @param APPLY_ID 多媒体文件类型，0-照片，1-录像
                 * @param TASK_NO 信贷员ID
                 * @param TASK_ITEM_NO 任务号，客户基本信息的任务号为info
                 * @param FILE_TYPE 文件描述，标明文件的位置及说明
                 * @return
                 * setAsJsonContent（boolean is）
                 * 如果有中文，要进行URLEncoder.encode方法
                 *params.addBodyParameter("deviceIntoTime",URLEncoder.encode(deviceIntoTime, "utf-8"));
                 */
                params.addBodyParameter("AUTH_TOKEN", AUTH_TOKEN);
                params.addBodyParameter("FILE", new File(path));
                params.addBodyParameter("APPLY_ID",task_info_list.get(0).get("apply_id"));
                params.addBodyParameter("TASK_NO", task_no);
                params.addBodyParameter("TASK_ITEM_NO", task_info_list.get(0).get("task_item_no"));
                params.addBodyParameter("FILE_TYPE", img_desc8[0]);
                Log.i(TAG, "693params:" + params);
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,result);
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                        ServerBean serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                        int err = serverBean.getError();
                        String msg = serverBean.getMsg();
                        if (err == 000) {

                            uploadi++;
                        } else {
                            Toast.makeText(TaskInfoActivity.this,msg+"请检查网络",Toast.LENGTH_SHORT).show();
                        }


                        UpDialog.setProgress((int) fen*uploadi);
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG,"uploadi="+uploadi);
                        if(uploadi==sumLeng){
                            mMedialist.mt_status=2+"";
                            String okstr =mediapro.UpdateStatus(mMedialist);
                            Log.i(TAG,"okstr="+okstr);
                            if(UpDialog!=null && UpDialog.isShowing()){
                                UpDialog.dismiss();
                            }
                        }
                        Intent intent = new Intent(TaskInfoActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            } else {
                if(UpDialog!=null && UpDialog.isShowing()){
                    UpDialog.dismiss();
                }
                Toast.makeText(TaskInfoActivity.this,"照片上传失败，不存在",Toast.LENGTH_SHORT).show();
                Log.i(TAG, path + "照片上传失败，不存在");
            }
        }

        for (int imagei = 0; imagei < type9img.length-1; imagei++) {
            String[] img_desc = type9img[imagei].split("@");
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+ img_desc[0]+ "/"+img_desc[1];
            Log.i(TAG, "path=" + path);
            File f = new File(path);
            if (f.exists()) {
                RequestParams params = new RequestParams(url);
                //支持断点续传
                params.setAutoResume(true);
                params.setMultipart(true);
                /**
                 * 多媒体文件上传
                 *
                 * @param auth_token 手机令牌
                 * @param file 文件对象
                 * @param media_type 多媒体文件类型，0-照片，1-录像
                 * @param userAccount 信贷员ID
                 * @param task_no 任务号，客户基本信息的任务号为info
                 * @param img_desc 文件描述，标明文件的位置及说明
                 * @return
                 * setAsJsonContent（boolean is）
                 * 如果有中文，要进行URLEncoder.encode方法
                 *params.addBodyParameter("deviceIntoTime",URLEncoder.encode(deviceIntoTime, "utf-8"));
                 */
                params.addBodyParameter("AUTH_TOKEN", AUTH_TOKEN);
                params.addBodyParameter("FILE", f);
                params.addBodyParameter("APPLY_ID",task_info_list.get(0).get("apply_id"));
                params.addBodyParameter("TASK_NO", task_no);
                params.addBodyParameter("TASK_ITEM_NO", task_info_list.get(0).get("task_item_no"));
                params.addBodyParameter("FILE_TYPE",img_desc[0]);
                Log.i(TAG, "756+params:" + params);
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,result);
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                        ServerBean serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                        int err = serverBean.getError();
                        String msg = serverBean.getMsg();
                        if (err == 000) {

                            uploadi++;
                        } else {
                            Toast.makeText(TaskInfoActivity.this,msg+"请检查网络",Toast.LENGTH_SHORT).show();
                        }


                        UpDialog.setProgress((int) fen*uploadi);
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG,"uploadi="+uploadi);
                        if(uploadi==sumLeng){
                            mMedialist.mt_status=2+"";
                            String okstr =mediapro.UpdateStatus(mMedialist);
                            Log.i(TAG,"okstr="+okstr);
                            if(UpDialog!=null && UpDialog.isShowing()){
                                UpDialog.dismiss();
                            }
                            Intent intent = new Intent(TaskInfoActivity.this,MainActivity.class);
                            startActivity(intent);
                        }

                    }
                });

            } else {
                if(UpDialog!=null && UpDialog.isShowing()){
                    UpDialog.dismiss();
                }
                Toast.makeText(TaskInfoActivity.this,"照片上传失败，不存在",Toast.LENGTH_SHORT).show();
                Log.i(TAG, path + "照片上传失败，不存在");
            }
        }
        if(!com_info_memo.getText().equals("")) {
                RequestParams params = new RequestParams(url);
                //支持断点续传
                params.setAutoResume(true);
                params.setMultipart(true);
                /**
                 * 多媒体文件上传
                 *
                 * @param auth_token 手机令牌
                 * @param file 文件对象
                 * @param media_type 多媒体文件类型，0-照片，1-录像
                 * @param userAccount 信贷员ID
                 * @param task_no 任务号，客户基本信息的任务号为info
                 * @param img_desc 文件描述，标明文件的位置及说明
                 * @return
                 * setAsJsonContent（boolean is）
                 * 如果有中文，要进行URLEncoder.encode方法
                 *params.addBodyParameter("deviceIntoTime",URLEncoder.encode(deviceIntoTime, "utf-8"));
                 */
                params.addBodyParameter("AUTH_TOKEN", AUTH_TOKEN);
                params.addBodyParameter("FILE", com_info_memo.getText().toString());
                params.addBodyParameter("APPLY_ID", task_info_list.get(0).get("apply_id"));
                params.addBodyParameter("TASK_NO", task_no);
                params.addBodyParameter("TASK_ITEM_NO", task_info_list.get(0).get("task_item_no"));
                params.addBodyParameter("FILE_TYPE", "99");
                Log.i(TAG, "894+params:" + params);
                Log.i(TAG, "!TextUtils.isEmpty(com_info_memo.getText())" + !com_info_memo.getText().equals(""));
                Log.i(TAG, "com_info_memo.getText()" + com_info_memo.getText().toString());

                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,result);
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                        ServerBean serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                        int err = serverBean.getError();
                        String msg = serverBean.getMsg();
                        if (err == 000) {

                            uploadi++;
                        } else {
                            Toast.makeText(TaskInfoActivity.this,msg+"请检查网络",Toast.LENGTH_SHORT).show();
                        }


                        UpDialog.setProgress((int) fen*uploadi);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        if(UpDialog!=null && UpDialog.isShowing()){
                            UpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG,"uploadi="+uploadi);
                        if(uploadi==sumLeng){
                            mMedialist.mt_status=2+"";
                            String okstr =mediapro.UpdateStatus(mMedialist);
                            Log.i(TAG,"okstr="+okstr);
                            if(UpDialog!=null && UpDialog.isShowing()){
                                UpDialog.dismiss();
                                Intent intent = new Intent(TaskInfoActivity.this,MainActivity.class);
                                startActivity(intent);
                            }


                        }
                    }
                });

        }

    }
    //拍照照片回调函数
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {

        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            photoflag=true;
            String path= Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator +"RZXT/"+userAcc+"/"+task_no+"/Get/"+type+"/";
            if(!FileUtils.isFileExist(path)){
                try {
                    File file= FileUtils.createSDDir(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                //获取图片
                Bitmap Bitmap = BitmapFactory.decodeFile(resultList.get(0).getPhotoPath());
                Log.i(TAG,resultList.get(0).getPhotoPath());
                //把新图片放到指定路径
            Log.i(TAG,"805 mPhotoname ="+mPhotoname);
                FileUtils.saveBitmap(path,mPhotoname+ ".JPG",Bitmap);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            photoflag=false;
            Toast.makeText(TaskInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    public void descUp(){
                int poss=0;
                poss=mPhotoname+1;
        if(type.equals("7")){
            String[] oneGetInfo = mMedialist.mt_u_7_desc.split("%");
            if(poss==oneGetInfo.length) {
                Log.i(TAG, "num=" + type);
                mMedialist.mt_u_7_desc = mMedialist.mt_u_7_desc + "%" + type + "@" + poss + ".jpg";
            }
        }else if(type.equals("8")){
            String[] oneGetInfo = mMedialist.mt_u_8_desc.split("%");
            if(poss==oneGetInfo.length) {
                mMedialist.mt_u_8_desc = mMedialist.mt_u_8_desc + "%" + type + "@" + poss + ".jpg";
            }
        }else if(type.equals("9")){
            String[] oneGetInfo = mMedialist.mt_u_9_desc.split("%");
            if(poss==oneGetInfo.length) {
                mMedialist.mt_u_9_desc = mMedialist.mt_u_9_desc + "%" + type + "@" + poss + ".jpg";
            }
        }
      Log.i(TAG,"827mMediaList.no"+mMedialist.mt_no);
        mediapro.addMediaGetDesc(mMedialist);

        //        Log.i(TAG,"810 oneGetInfo ="+oneGetInfo.length);



//        if(poss==oneGetInfo.length){
//            MediaPro mediapro = new MediaPro(TaskInfoActivity.this);
//            Log.i(TAG,"num="+type);

//            mediapro.update(mMedialist);
//            mediapro.addMediaGetDesc(mMedialist.mt_no,mPhotoname+".JPG",type);

//        }
    }
    @Override
    protected void onStart() {
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        URL= "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        AUTH_TOKEN = sharedPrefs.getString("AUTH_TOKEN", "0");
        super.onStart();
    }

    @Override
    protected void onResume() {
        if(photoflag){
            descUp();
        }
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        DownReView(mMedialist);
        GetReView1(mMedialist);
        GetReView2(mMedialist);
        GetReView3(mMedialist);
        Log.i(TAG,"844=onResume");
        photoflag=false;
        super.onResume();
    }

    @Override
    protected void onStop() {
        TaskPro repo = new TaskPro(TaskInfoActivity.this);
        //任务备注
        String task_note =  com_info_memo.getText().toString() ;
        repo.updateNote(task_no,task_note);
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        Log.i(TAG,"onPostResume");
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

}
