package com.sjs.dz.rzxt2;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.util.VersionInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjs.dz.rzxt2.my.AboutActivity;
import com.sjs.dz.rzxt2.my.ClearActivity;
import com.sjs.dz.rzxt2.view.ActionSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by janiszhang on 2016/6/10.
 */

public class TabFragment3 extends Fragment implements View.OnClickListener{
    private String TAG = this.getClass().getSimpleName();
    private String URL="";
    private String userAccount="";
    private String path="";
    private LinearLayout Updately,Memoly,Clearly,AboutLy,Settingly,OutLy;
    private ImageButton UserIconIm;
    private TextView UserAccountTv,UserNameTv;
    private File apkFile;
    private SharedPreferences sharedPrefs;
    private Button ll_update;
    private ProgressDialog pDialog;
    private String nowVersion;
    private ProgressDialog progressDialog;

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, null);
        //注入view和事件
        x.view().inject(getActivity());

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tab3_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        Updately = (LinearLayout)view.findViewById(R.id.ly_update);
//        Memoly = (LinearLayout)view.findViewById(R.id.ly_memo);
        Clearly = (LinearLayout)view.findViewById(R.id.ly_clear);
        AboutLy = (LinearLayout)view.findViewById(R.id.ly_about);
        Settingly = (LinearLayout)view.findViewById(R.id.ly_setting);
        OutLy = (LinearLayout)view.findViewById(R.id.ly_out);
        UserIconIm = (ImageButton) view.findViewById(R.id.im_user_icon);
        UserAccountTv = (TextView) view.findViewById(R.id.tv_user_account);
        UserNameTv = (TextView) view.findViewById(R.id.tv_user_name);

        Updately.setOnClickListener(this);
//        Memoly.setOnClickListener(this);
        Clearly.setOnClickListener(this);
        AboutLy.setOnClickListener(this);
        Settingly.setOnClickListener(this);
        OutLy.setOnClickListener(this);
        UserIconIm.setOnClickListener(this);
        UserAccountTv.setOnClickListener(this);
        UserNameTv.setOnClickListener(this);


        //显示用户名
        sharedPrefs = getActivity().getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        UserAccountTv.setText(sharedPrefs.getString("USER_NAME", "110"));
        UserNameTv.setText(sharedPrefs.getString("USER_TEL", "110"));
        //查询并显示用户头像

//        if(sharedPrefs.getString("username", "110")==null||sharedPrefs.getString("username", "110").equals("")) {
//
        ImageOptions options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.defaulticon)
//设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.defaulticon)
                //设置圆形
                .setCircular(false)
                .setSquare(true)
                //某些手机拍照时图片自动旋转，设置图片是否自动旋转为正
                .setAutoRotate(true)
                //等比例缩放居中显示
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
        x.image().bind(UserIconIm,sharedPrefs.getString("USER_ICON", "R.mipmap.defaulticon)"), options);

//        }else{
//            ImageOptions options = new ImageOptions.Builder()
//                    //设置圆形
//                    .setCircular(true)
//                    //某些手机拍照时图片自动旋转，设置图片是否自动旋转为正
//                    .setAutoRotate(true)
//                    //等比例缩放居中显示
//                    .setImageScaleType(ImageView.ScaleType.FIT_XY)
//                    .build();
//            x.image().bind(UserIconIm, sharedPrefs.getString("username", "110"), options);
//        }
//

        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0);
            nowVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //更換頭像
            case R.id.im_user_icon:
                showSheetDialog();
                break;
            //版本更新
            case R.id.ly_update:
                checkUpdate();

//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    //2.获取SD卡的路径,使用File.separator维护"/"或者"\"
//                    path = Environment.getExternalStorageDirectory().getAbsolutePath()
//                            + File.separator +"RZXT/";
//                    Log.i(TAG, "PATH= "+path);
//                    if(FileUtils.isFileExist(path)){
//                        downloadAPK(""+0,path);
//                    }
//                    else{
//                        try {
//                            apkFile= FileUtils.createSDDir(path);
//                            downloadAPK(""+0,path);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }

//                }
                break;
            //备忘录
//            case R.id.ly_memo:
//                Intent intent1 = new Intent(getActivity(), MemoActivity.class);
//                startActivity(intent1);
//                break;
            //清除缓存
            case R.id.ly_clear:
                Intent intent1 = new Intent(getActivity(), ClearActivity.class);
                startActivity(intent1);
                break;
            //设置ip
            case R.id.ly_setting:
                Intent intent2 = new Intent(getActivity(), EditIPActivity.class);
                startActivity(intent2);
                break;
            //关于我们
            case R.id.ly_about:
                Intent intent3 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent3);
                break;
            //退出
            case R.id.ly_out:
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("AUTH_TOKEN","");
                editor.commit();
                System.exit(0);
//                ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
//                manager.restartPackage(getActivity().getPackageName());
                break;
            default:
                break;
        }
    }
//    private void downloadAPK(String appVersion,String Feilpath) {
//        //1.检查SD卡是否挂载上
//
//        //3.发送请求，获取指定APK，放置到指定位置
//        RequestParams params = new RequestParams(URL+"isUpdate");
//        params.addParameter("userAccount", "11000");
//        params.addParameter("appVersion", "0");
////        params.setSaveFilePath(path);
//        //4.发送请求，传送参数(要下载的apk文件的url，下载成功后存储位置，回调函数)
//
//        Callback.Cancelable post =
//                x.http().post(params, new Callback.ProgressCallback<String>() {
//
//                    @Override
//
//                    public void onSuccess(String result) {
//                        installAPK(path);
//                    }
//
//                    @Override
//
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        //下载失败
//                        Log.d(TAG, "下载失败");
//                    }
//
//                    @Override
//
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//
//                    public void onFinished() {
//                    }
//
//                    @Override
//
//                    public void onWaiting() {
//
//                    }
//
//                    @Override
//
//                    public void onStarted() {
//
//                    }
//
//                    @Override
//                    public void onLoading(long total, long current, boolean isDownloading) {
//                        //文件下载时回调的方法
//
//                        Log.i("xxxxxxxxxxxxx", "current<<" + current + "total<<" + total);
//                    }
//                });
//    }



//
//    /**
//     * 开启安装APK页面的逻辑
//     *
//     */
//    private void installAPK(String downAPKurl) {
//        RequestParams params1 = new RequestParams(downAPKurl);
//        params1.setAutoRename(true);//断点下载
//        params1.setSaveFilePath(path);
//        Callback.Cancelable post = x.http().post(params1, new Callback.ProgressCallback<File>() {
//                    @Override
//                    public void onSuccess(File result) {
//                        Log.d(TAG, "aaaaaaaaaaaaaa下载成功result"+result);
//                        //系统应用界面，安装apk入口，看源码
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        intent.addCategory("android.intent.category.DEFAULT");
////        intent.setData(Uri.fromFile(file));
////        intent.setType("application/vnd.android.package-archive");
//
//                        //切记当要同时配Data和Type时一定要用这个方法，否则会出错
//                        intent.setDataAndType(Uri.fromFile(new File(path)),"application/vnd.android.package-archive");
//                        startActivityForResult(intent,0);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//
//                    @Override
//                    public void onWaiting() {
//
//                    }
//
//                    @Override
//                    public void onStarted() {
//
//                    }
//
//                    @Override
//                    public void onLoading(long total, long current, boolean isDownloading) {
//
//                    }
////        Intent intent = new Intent(Intent.ACTION_VIEW);
////        intent.setDataAndType(Uri.fromFile(new File( Environment.getExternalStorageDirectory().getAbsolutePath()
////                        + File.separator + "rzxt.apk")),
////                "application/vnd.android.package-archive");
////        startActivity(intent);
//    });
//    }
//创建SD文件夹

    //    public  File createSDDir(String dirName) throws IOException {
//        File dir = new File(dirName);
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            dir.mkdirs();
//
////			System.out.println("createSDDir:" + dir.getAbsolutePath());
////			System.out.println("createSDDir:" + dir.mkdir());
//        }
//        Log.i(TAG,"dir="+dir);
//        return dir;
//    }
//
//    //判断文件是否存在
//    public  boolean isFileExist(String fileName) {
//        File file = new File(fileName);
//        file.isFile();
//        Log.i(TAG,"file.exists()="+file.exists());
//        return file.exists();
//    }
    //头像选择
    private void showSheetDialog() {

        new ActionSheetDialog(getActivity()).builder()
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.DEFAULT,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
//                                Toast.makeText(MainActivity.this,"paizhao",Toast.LENGTH_LONG).show();
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

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {

        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {


            ImageOptions options = new ImageOptions.Builder()
                    //设置圆形
                    .setCircular(false)
                    .setSquare(true)
                    //某些手机拍照时图片自动旋转，设置图片是否自动旋转为正
                    .setAutoRotate(true)
                    //等比例缩放居中显示
                    .setImageScaleType(ImageView.ScaleType.FIT_XY)
                    .build();

            if (resultList != null) {
                //获取图片路径
                String photoPath= resultList.get(0).getPhotoPath();
                //获取NavigationView父布局
                x.image().bind(UserIconIm,photoPath, options);


                //存储头像路径到数据库

                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("USER_ICON",photoPath);
                editor.commit();


            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 下载更新,
     */
    //http://172.16.10.242:8080/MVNFHM/appInterface/isUpdate?userAccount=11000&appVersion=0
    protected void checkUpdate() {
        // TODO Auto-generated method stub
        proDialogShow(getActivity(), "正在查询...");
        RequestParams params = new RequestParams(URL+"/MVNFHM/appInterface/appCheckUpdate");
        params.addBodyParameter("userAccount", userAccount);
        params.addBodyParameter("appVersion", nowVersion);
        Log.i(TAG,params+"");
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                PDialogHide();
                System.out.println("提示网络错误");
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(String arg0) {
                // TODO Auto-generated method stub
                PDialogHide();
//                try {
//                    JSONObject object = new JSONObject(arg0);
////                    boolean success = object.getBoolean("succss");
////                    if (success) {
//                        String desc = object.getString("desc");
////                    172.16.10.242:8080/MVNFHM//appInterface/downRZXT
////                        String downloadurl = object.getString("downloadurl");
//                    String downloadurl = object.getString("downloadurl");
//                        String versionname = object.getString("versionname");
//                        if (nowVersion.equals(versionname)) {
//                            Log.i(TAG,"当前版本为最新，不用更新");
//                        } else {
//                            // 不同，弹出更新提示对话框
//                            setUpDialog(versionname, downloadurl, desc);
////                        }
//                    }
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                if (arg0.equals("002")) {
                    Toast.makeText(getActivity(),"当前版本为最新版本",Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"当前版本为最新，不用更新");
                } else {
                    // 不同，弹出更新提示对话框
                    setUpDialog(nowVersion, arg0, "最新版");
//                        }
                }
            }
        });
    }

    /**
     *
     * @param versionname
     *            地址中版本的名字
     * @param downloadurl
     *            下载包的地址
     * @param desc
     *            版本的描述
     */
    protected void setUpDialog(String versionname, final String downloadurl,
                               String desc) {
        // TODO Auto-generated method stub
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setCancelable(false)
                .setTitle("下载" + versionname + "版本").setMessage(desc)
                .setNegativeButton("取消", null)
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        setDownLoad(downloadurl);
                    }
                }).create();
        dialog.show();
    }

    /**
     * 下载包
     *
     * @param downloadurl
     *            下载的url
     *
     */
    @SuppressLint("SdCardPath")
    protected void setDownLoad(String downloadurl) {
        // TODO Auto-generated method stub
        RequestParams params = new RequestParams(downloadurl);
        params.setAutoRename(true);//断点下载

//        params.setSaveFilePath("/mnt/sdcard/rzxt.apk");
        params.setSaveFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/rzxt.apk");
        x.http().get(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                System.out.println("提示更新失败");
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(File arg0) {
                // TODO Auto-generated method stub
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/", "rzxt.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onLoading(long arg0, long arg1, boolean arg2) {
                // TODO Auto-generated method stub
                progressDialog.setMax((int)arg0);
                progressDialog.setProgress((int)arg1);
            }

            @Override
            public void onStarted() {
                // TODO Auto-generated method stub
                System.out.println("开始下载");
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置为水平进行条
                progressDialog.setMessage("正在下载中...");
                progressDialog.setProgress(0);
                progressDialog.show();
            }

            @Override
            public void onWaiting() {
                // TODO Auto-generated method stub

            }
        });
    }

    private void proDialogShow(Context context, String msg) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        // pDialog.setCancelable(false);
        pDialog.show();
    }

    private void PDialogHide() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        userAccount=sharedPrefs.getString("USER_ACCOUNT", "null");
        super.onResume();
    }
}
