package com.sjs.dz.rzxt2.my;//package com.bcm.sjs.rzxt.my;
//
///**
// * Created by SJS on 2017/2/24.
// */
//import java.io.File;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.x;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
// import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
// import android.content.pm.PackageManager.NameNotFoundException;
// import android.net.Uri;
// import android.os.Bundle;
//import android.os.Environment;
// import android.view.View;
// import android.view.Window;
// import android.widget.Button;
//
//import com.bcm.sjs.rzxt.R;
//
///**
// 28  * 设置xutils下载
// 29  *
// 30  * @author Administrator
// 31  *
// 32  */
//
//
//
// public class AppUpadteUtil extends Activity {
//    private String URL="http://172.16.10.242:8080/MVNFHM/appInterface/isUpdate";
//   private Button ll_update;
//         private ProgressDialog pDialog;
//           private String nowVersion;
//             private ProgressDialog progressDialog;
//
//                     @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                  // TODO Auto-generated method stub
//                    super.onCreate(savedInstanceState);
//                   requestWindowFeature(Window.FEATURE_NO_TITLE);
//                   setContentView(R.layout.activity_version_update);
//                    ll_update = (Button) findViewById(R.id.ll_update);
//                    ll_update.setOnClickListener(new View.OnClickListener() {
//
//                                  @Override
//                         public void onClick(View arg0) {
//                                  // TODO Auto-generated method stub
//                                    checkUpdate();
//                                }
//                       });
//                    try {
//                          PackageInfo packageInfo = getPackageManager().getPackageInfo(
//                                          getPackageName(), 0);
//                        nowVersion = packageInfo.versionName;
//                       } catch (NameNotFoundException e) {
//                             // TODO Auto-generated catch block
//                             e.printStackTrace();
//                       }
//              }
//            /**
//         65      * 下载更新,
//         66      */
//                    protected void checkUpdate() {
//                    // TODO Auto-generated method stub
//                    proDialogShow(AppUpadteUtil.this, "正在查询...");
//                    RequestParams params = new RequestParams(URL);
//                        params.addParameter("userAccount", "11000");
//                        params.addParameter("appVersion", "0");
//                        params.setSaveFilePath(path);
//                     x.http().get(params, new Callback.CommonCallback<String>() {
//
//                                     @Override
//                          public void onCancelled(CancelledException arg0) {
//                                   // TODO Auto-generated method stub
//
//                                 }
//
//                         @Override
//                            public void onError(Throwable arg0, boolean arg1) {
//                                   // TODO Auto-generated method stub
//                             PDialogHide();
//                             System.out.println("提示网络错误");
//                         }
//
//                                    @Override
//                             public void onFinished() {
//                                     // TODO Auto-generated method stub
//
//                                }
//
//                                    @Override
//                            public void onSuccess(String arg0) {
//                                     // TODO Auto-generated method stub
//                                     PDialogHide();
//                                     try {
//                                             JSONObject object = new JSONObject(arg0);
//                                            boolean success = object.getBoolean("succee");
//                                             if (success) {
//                                                    String desc = object.getString("desc");
//                                                     String downloadurl = object.getString("downloadurl");
//                                                     String versionname = object.getString("versionname");
//                                                    if (nowVersion.equals(versionname)) {
//                                                            System.out.println("当前版本为最新，不用跟新");
//                                                         } else {
//                                                             // 不同，弹出更新提示对话框
//                                                             setUpDialog(versionname, downloadurl, desc);
//                                                         }
//                                                 }
//                                         } catch (JSONException e) {
//                                             // TODO Auto-generated catch block
//                                            e.printStackTrace();
//                                         }
//                                }
//                        });
//                }
//
//                    /**
//         119      *
//         120      * @param versionname
//         121      *            地址中版本的名字
//         122      * @param downloadurl
//         123      *            下载包的地址
//         124      * @param desc
//         125      *            版本的描述
//         126      */
//                     protected void setUpDialog(String versionname, final String downloadurl,
//                                                                String desc) {
//                     // TODO Auto-generated method stub
//                     AlertDialog dialog = new AlertDialog.Builder(this).setCancelable(false)
//                             .setTitle("下载" + versionname + "版本").setMessage(desc)
//                             .setNegativeButton("取消", null)
//                            .setPositiveButton("下载", new DialogInterface.OnClickListener() {
//
//                                             @Override
//                                     public void onClick(DialogInterface arg0, int arg1) {
//                                            // TODO Auto-generated method stub
//                                           setDownLoad(downloadurl);
//                                        }
//                                 }).create();
//                     dialog.show();
//                 }
//
//     /**
//         145      * 下载包
//         146      *
//         147      * @param downloadurl
//         148      *            下载的url
//         149      *
//         150      */
//                     @SuppressLint("SdCardPath")
//            protected void setDownLoad(String downloadurl) {
//                     // TODO Auto-generated method stub
//                     RequestParams params = new RequestParams(downloadurl);
//                     params.setAutoRename(true);//断点下载
//                     params.setSaveFilePath("/mnt/sdcard/demo.apk");
//                     x.http().get(params, new Callback.ProgressCallback<File>() {
//
//                                     @Override
//                            public void onCancelled(CancelledException arg0) {
//                                     // TODO Auto-generated method stub
//
//                                 }
//
//                                     @Override
//                             public void onError(Throwable arg0, boolean arg1) {
//                                    // TODO Auto-generated method stub
//                                     if(progressDialog!=null && progressDialog.isShowing()){
//                                             progressDialog.dismiss();
//                                         }
//                                     System.out.println("提示更新失败");
//                                 }
//
//                                     @Override
//                            public void onFinished() {
//                                     // TODO Auto-generated method stub
//
//                                }
//
//                                    @Override
//                             public void onSuccess(File arg0) {
//                                    // TODO Auto-generated method stub
//                                     if(progressDialog!=null && progressDialog.isShowing()){
//                                             progressDialog.dismiss();
//                                         }
//                                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setDataAndType(Uri.fromFile(new File(Environment
//                                                             .getExternalStorageDirectory(), "demo.apk")),
//                                            "application/vnd.android.package-archive");
//                                     startActivity(intent);
//                                 }
//
//                                     @Override
//                            public void onLoading(long arg0, long arg1, boolean arg2) {
//                                    // TODO Auto-generated method stub
//                                     progressDialog.setMax((int)arg0);
//                                    progressDialog.setProgress((int)arg1);
//                                 }
//
//                                     @Override
//                           public void onStarted() {
//                                    // TODO Auto-generated method stub
//                                     System.out.println("开始下载");
//                                     progressDialog = new ProgressDialog(AppUpadteUtil.this);
//                                     progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置为水平进行条
//                                    progressDialog.setMessage("正在下载中...");
//                                    progressDialog.setProgress(0);
//                                    progressDialog.show();
//                                 }
//
//                                     @Override
//                             public void onWaiting() {
//                                     // TODO Auto-generated method stub
//
//                                 }
//                         });
//                 }
//
//                     private void proDialogShow(Context context, String msg) {
//                     pDialog = new ProgressDialog(context);
//                     pDialog.setMessage(msg);
//                     // pDialog.setCancelable(false);
//                     pDialog.show();
//                 }
//
//                     private void PDialogHide() {
//                     try {
//                             if (pDialog != null && pDialog.isShowing()) {
//                                     pDialog.dismiss();
//                                 }
//                         } catch (Exception e) {
//                             e.printStackTrace();
//                         }
//                 }
//         }
//
