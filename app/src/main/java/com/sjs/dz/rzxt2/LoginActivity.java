package com.sjs.dz.rzxt2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sjs.dz.rzxt2.DB.MEDIA;
import com.sjs.dz.rzxt2.DB.MediaPro;
import com.sjs.dz.rzxt2.DB.ServerBean;
import com.sjs.dz.rzxt2.DB.TASK;
import com.sjs.dz.rzxt2.DB.TaskPro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //http://172.16.10.242:8080/MVNFHM//appInterface/appLogin?userAccount=yang&passWord=1
    private String TAG = this.getClass().getSimpleName();
    private Context context = LoginActivity.this;
    private String ACC,PASSWORD,URL;
    private EditText et_Acc,et_Password;
    private ImageButton imBtn_IP_Edit;
    private Button btn_Login;
    private TextView spr_IP;
    private SharedPreferences sharedPrefs;
    private ServerBean serverBean;
    private Button ll_update;
    private ProgressDialog pDialog;
    private String nowVersion;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG,"LoginActivity登陆");
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        x.view().inject(this);
        et_Acc = (EditText)findViewById(R.id.et_acc);
        et_Password = (EditText)findViewById(R.id.et_password);
        imBtn_IP_Edit= (ImageButton) findViewById(R.id.imbtn_ip_edit);
        btn_Login= (Button) findViewById(R.id.btn_login);
        spr_IP= (TextView) findViewById(R.id.spinner_ip);


        imBtn_IP_Edit.setOnClickListener(this);
        btn_Login.setOnClickListener(this);
//        checkUpdate();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), 0);
            nowVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.imbtn_ip_edit:
                Intent intent = new Intent(LoginActivity.this,EditIPActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                ACC = et_Acc.getText().toString();
                PASSWORD = et_Password.getText().toString();
//                if(TextUtils.isEmpty(spr_IP.getText())){
                    URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
                    LoginHttp(ACC,PASSWORD,URL);
//                    test();
//                }
//               else{
//                    Toast.makeText(LoginActivity.this,"请设置连接",Toast.LENGTH_SHORT).show();
//                }
                break;
            default:
                break;
        }

    }



    private void LoginHttp(String ACC,String PASSWORD,String URL){

        RequestParams params = new RequestParams(URL+"/MVNFHM/appInterface/appLogin");
        params.addParameter("userAccount", ACC);
        //根据当前请求方式添加参数位置
        params.addParameter("passWord", PASSWORD);
        Log.i("LoginActivity_post", "params："+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess" + result);
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                int err = serverBean.getError();
                String mag = serverBean.getMsg();
                if (err == 0) {
                    //解析成功
                    Log.i(TAG, "解析成功：" + mag);

                    ServerBean.User user = serverBean.getUser();
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("AUTH_TOKEN", user.getAUTH_TOKEN());
                    editor.putString("USER_ACCOUNT", user.getUSER_ACCOUNT());
                    editor.putString("USER_NAME", user.getUSER_NAME());
                    editor.putString("USER_IDE", user.getUSER_IDE());
                    editor.putString("USER_TEL", user.getUSER_TEL());
                    editor.putString("USER_DEPT_NAME", user.getUSER_DEPT_NAME());
                    editor.putString("USER_DEPT_ORG_CODE", user.getUSER_DEPT_ORG_CODE());
                    editor.commit();

                    List<ServerBean.MediaBean> mediabeanList = serverBean.getFileList();
                    if (mediabeanList.size() > 0) {
                        for (int i = 0; i < mediabeanList.size(); i++) {

                            ServerBean.MediaBean bean = mediabeanList.get(i);

                            MEDIA media = new MEDIA();
                            //记录主键
                            media.mt_no = bean.getMT_ID();
                            //模板名称
                            media.mt_name = bean.getMT_NAME();
                            //模板项目信息
                            media.mt_item_info = bean.getMT_ITEM_INFO();
                            //模板上传照片数量
//                                media.mt_u_7_desc = bean.getMT_U_7_DESC();
//                                //模板上传照片数量
//                                media.mt_u_8_desc = bean.getMT_U_8_DESC();
//                                //模板上传照片数量
//                                media.mt_u_9_desc = bean.getMT_U_9_DESC();
                            //模板上传照片描述Im:aaa;im:bbb;im:ccc;im:ddd;w:eee;p:kkk;e:lll
                            media.mt_d_desc = bean.getMT_D_DESC();

                            media.mt_is_note = bean.getMT_IS_NOTE();


                            //模板状态
                            media.mt_status = bean.getMT_STATUS();

                            MediaPro repo1 = new MediaPro(context);
                            if (repo1.getID(bean.getMT_ID())) {
                                repo1.update(media);
                                Log.i(TAG, "media表更新:" + bean.getMT_ID());


                            } else {
                                repo1.insert(media);
                                Log.i(TAG, "media表插入:" + bean.getMT_ID());


                            }
                        }
                    }
                    List<ServerBean.TaskBean> taskbeanList = serverBean.getTaskList();

                    Log.i(TAG,"taskbeanList.size()="+taskbeanList.size());
                    Log.i(TAG,"mediabeanList.size()="+mediabeanList.size());
                    if (taskbeanList.size() > 0) {
                        for (int i = 0; i < taskbeanList.size(); i++) {

                            ServerBean.TaskBean bean = taskbeanList.get(i);
                            TASK task = new TASK();
                            task.task_no = bean.getTASK_NO();
                            task.task_type = bean.getTASK_TYPE();
                            task.task_status = bean.getTASK_STATUS();
                            task.task_is_early_file = bean.getTASK_IS_EARLY_FILE();


                            //检查机构代码
                            task.task_check_org_no = bean.getTASK_CHECK_ORG_NO();
                            //检查机构名称
                            task.task_check_org_name = bean.getTASK_CHECK_ORG_NAME();
                            //检查类型(首次检查、定期检查、不定期检查)
                            task.task_check_type = bean.getTASK_CHECK_TYPE();
                            //检查选项(按合同检查)
                            task.task_check_option = bean.getTASK_CHECK_OPTION();
                            //任务检查人账号
                            task.task_iner_acc = bean.getTASK_INER_ACC();


                            //任务检查人姓名
                            task.task_iner_name = bean.getTASK_INER_NAME();
                            //任务开始日期
                            task.task_start_date = bean.getTASK_START_DATE();
                            //任务截止日期
                            task.task_end_date = bean.getTASK_END_DATE();
                            //任务完成日期
                            task.task_finish_date = bean.getTASK_FINISH_DATE();
                            //审核人账号
                            task.task_audit_acc = bean.getTASK_AUDIT_ACC();


                            //审核人姓名
                            task.task_audit_name = bean.getTASK_AUDIT_NAME();
                            //申请公司编号
                            task.task_com_no = bean.getTASK_COM_NO();
                            //申请公司名称
                            task.task_com_name = bean.getTASK_COM_NAME();
                            //申请合同编号
                            task.task_con_no = bean.getTASK_CON_NO();
                            //申请项目编号
                            task.task_item_no = bean.getTASK_ITEN_NO();


                            //申请产品编号
                            task.task_prdt_no = bean.getTASK_PRDT_NO();
                            //申请产品名称
                            task.task_prdt_name = bean.getTASK_PRDT_NAME();
                            //申请生产类型
                            task.task_prdt_type = bean.getTASK_PRDT_TYPE();
                            //申请认证范围
                            task.task_rz_scope = bean.getTASK_RZ_SCOPE();
                            //申请认证类型
                            task.task_rz_type = bean.getTASK_RZ_TYPE();

                            //申请公司地址
                            task.task_com_addr = bean.getTASK_COM_ADDR();
                            //申请公司电话
                            task.task_com_tel = bean.getTASK_COM_TEL();
                            // 申请公司邮编
                            task.task_com_post_code = bean.getTASK_COM_POST_CODE();
                            //申请公司邮箱
                            task.task_com_email = bean.getTASK_COM_EMAIL();
                            //申请公司传真
                            task.task_com_fax = bean.getTASK_COM_FAX();


                            //申请公司网络url
                            task.task_web_url = bean.getTASK_COM_WEB_URL();
                            //公司联系人姓名
                            task.task_com_con_name = bean.getTASK_COM_CON_NAME();
                            //公司联系人电话
                            task.task_com_con_tel = bean.getTASK_COM_CON_TEL();
                            //公司联系人职位
                            task.task_com_con_ide = bean.getTASK_COM_CON_IDE();
                            //申请产品介绍
                            task.task_item_info = bean.getTASK_ITEM_INFO();

                            //信息采集模板ID
                            task.task_mt_id = bean.getTASK_MT_ID();
                            //任务备注
                            task.task_note = bean.getTASK_NOTE();
                            //申请号
                            task.apply_id = bean.getAPPLY_ID();
                            TaskPro repo = new TaskPro(context);
                            if (repo.getNo(bean.getTASK_NO())) {
                                repo.update(task);
                                Log.i(TAG, "task表更新:" + bean.getTASK_NO());
                            } else {
                                repo.insert(task);
                                Log.i(TAG, "task表插入:" + bean.getTASK_NO());
                            }
                        }
                    }





                        //跳转Activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("USER_ACCOUNT", user.getUSER_ACCOUNT());
                        startActivity(intent);
                        finish();


                } else {
                    Log.i(TAG, "解析失败" + mag);
                    et_Acc.setText("");
                    et_Password.setText("");
                    Toast.makeText(LoginActivity.this, "请重新登录", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.i("LogActivity","onError网络请求错误"+ex);
////                Toast.makeText(LoginActivity.this, "网络连接", Toast.LENGTH_LONG).show();
//                et_Acc.setText("");
//                et_Password.setText("");
//                Toast.makeText(LoginActivity.this, "请重新登录", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("LogActivity","onCancelled网络请求取消");
            }

            @Override
            public void onFinished() {

                Log.i("LogActivity","onFinished网络请求完毕");
            }
        });
//            Toast.makeText(LoginActivity.this, "用户名或密码错误请重新输入", Toast.LENGTH_LONG).show();
//
                            }
//        public  void test(){
//            //跳转Activity
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//
//            startActivity(intent);
//        }
    /**
     * 下载更新,
     */
    //http://172.16.10.242:8080/MVNFHM/appInterface/isUpdate?userAccount=11000&appVersion=0
    protected void checkUpdate() {
        // TODO Auto-generated method stub
        proDialogShow(LoginActivity.this, "正在查询...");
        RequestParams params = new RequestParams(URL+"/MVNFHM/appInterface/appCheckUpdate");
        params.addBodyParameter("userAccount", ACC);
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
                    Toast.makeText(LoginActivity.this,"当前版本为最新版本",Toast.LENGTH_SHORT).show();
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
        AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this).setCancelable(false)
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
                progressDialog = new ProgressDialog(LoginActivity.this);
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
    protected void onRestart() {
        super.onRestart();
        spr_IP.setText(sharedPrefs.getString("connect_name", "recome"));
    }

    @Override
    protected void onResume() { //在另一个Activity中
        super.onResume();
        Log.i(TAG,"onResume获取IP");
        Log.i(TAG,sharedPrefs.getString("CONNECT_NAME", "recome"));
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        spr_IP.setText(sharedPrefs.getString("CONNECT_NAME", "recome"));
    }
}

