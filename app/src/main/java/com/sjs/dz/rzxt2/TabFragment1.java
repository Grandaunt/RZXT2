package com.sjs.dz.rzxt2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sjs.dz.rzxt2.DB.MEDIA;
import com.sjs.dz.rzxt2.DB.MediaPro;
import com.sjs.dz.rzxt2.DB.ServerBean;
import com.sjs.dz.rzxt2.DB.TASK;
import com.sjs.dz.rzxt2.DB.TaskPro;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;


public class TabFragment1 extends ListFragment {
    private String TAG = this.getClass().getSimpleName();
    private View viewContent,viewItem;
    private int mType = 0;
    private String mTitle;
    private PtrFrameLayout mPtrFrame;
    private int position=0;
    private String com_name;
    private TextView tv_status;
    private ArrayList<HashMap<String, String>> taskList;
    private SharedPreferences sharedPrefs;
    private String userAcc;
    private String URL;
    private Button btn_sreach;
    private EditText et_sreach;
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public void setType(int mType) {
        this.mType = mType;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getType() {
        return mType;
    }
    public String getTitle() {
        return mTitle;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        x.view().inject(getActivity());

        sharedPrefs = getActivity().getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        //布局文件中只有一个居中的TextView
//        if(viewContent == null) {
        viewContent = inflater.inflate(R.layout.fragment_tab1, container, false);
        viewItem = inflater.inflate(R.layout.item_main_over, container, false);
        Toolbar toolbar = (Toolbar) viewContent.findViewById(R.id.tab1_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        pullRefresh();
        userAcc = sharedPrefs.getString("USER_ACCOUNT", "null");
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        tv_status=(TextView)viewItem.findViewById(R.id.item_audit_ruf_2);
        TaskPro repo = new TaskPro(getActivity());
        Log.i(TAG, "getPosition()=" + getPosition());
//        taskList = repo.showStatusList(getPosition(), userAcc);
        taskList = repo.showStatusList( userAcc);
//        if(getPosition()==0) {
            if(taskList.size()>0) {
                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_content, new String[]{"task_com_name", "task_com_addr", "task_end_date","task_status"}, new int[]{R.id.item_company_name, R.id.item_company_address, R.id.item_date,R.id.item_status});
                setListAdapter(adapter);
//            }

//        }
//        else{
//            if(taskList.size()>0) {
//                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_over, new String[]{"task_com_name", "task_com_no", "task_status", "task_end_date"}, new int[]{R.id.item_company_name_2, R.id.item_company_no_2, R.id.item_audit_ruf_2, R.id.item_date_2});
//                setListAdapter(adapter);
//            }

        }
        /***
         * 搜索
         */
//        position=this.position;
        et_sreach = (EditText)viewContent.findViewById(R.id.et_sreach_task_info);
        btn_sreach = (Button)viewContent.findViewById(R.id.btn_sreach);
        btn_sreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  info = et_sreach.getText().toString();
                TaskPro repo = new TaskPro(getActivity());
                taskList = repo.showSreach(info,getPosition(), userAcc);
//                if(getPosition()==0) {
                    if(taskList.size()>0) {
//                    Log.i(TAG,"item_main_content"+taskList.get(0).get("task_status"));
                        ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_content, new String[]{"task_com_name", "task_com_addr", "task_end_date","task_status"}, new int[]{R.id.item_company_name, R.id.item_company_address, R.id.item_date,R.id.item_status});
                        setListAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getActivity(),"无数据",Toast.LENGTH_SHORT).show();
                    }
//                }
//                else{
//                    if(taskList.size()>0) {
////                    Log.i(TAG,"item_main_over"+taskList.get(0).get("task_status"));
//                        ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_over, new String[]{"task_com_name", "task_com_no", "task_status", "task_end_date"}, new int[]{R.id.item_company_name_2, R.id.item_company_no_2, R.id.item_audit_ruf_2, R.id.item_date_2});
//                        setListAdapter(adapter);
//                    }
//                    else{
//                        Toast.makeText(getActivity(),"无数据",Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
        return viewContent;
    }

    public void pullRefresh(){
        mPtrFrame = (PtrFrameLayout) viewContent.findViewById(R.id.ptr);
        /**
         * 在 xml 中配置过了，就不要在这里配置了。
         */
        /*mPtrFrame.setResistance(1.7f); //阻尼系数 默认: 1.7f，越大，感觉下拉时越吃力。
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f); //触发刷新时移动的位置比例 默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
        mPtrFrame.setDurationToClose(200);//回弹延时 默认 200ms，回弹到刷新高度所用时间
        mPtrFrame.setDurationToCloseHeader(1000);//头部回弹时间 默认1000ms
        mPtrFrame.setPullToRefresh(false);// 刷新是保持头部 默认值 true.
        mPtrFrame.setKeepHeaderWhenRefresh(true);//下拉刷新 / 释放刷新 默认为释放刷新*/

        /**
         * 经典 风格的头部实现
         */
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);


        /**
         * StoreHouse风格的头部实现
         */
        /*final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);*/

        /**
         * using a string, support: A-Z 0-9 - .
         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        // header.initWithString("Alibaba");


        /**
         * Material Design风格的头部实现
         */
        // final MaterialHeader header = new MaterialHeader(this);
        //header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);//显示相关工具类，用于获取用户屏幕宽度、高度以及屏幕密度。同时提供了dp和px的转化方法。


        /**
         * Rentals Style风格的头部实现
         * 这个需要引入这两个类RentalsSunDrawable.java ; RentalsSunHeaderView.java
         * 在人家git上的daemon中能找到
         */
       /* final RentalsSunHeaderView header = new RentalsSunHeaderView(this);

        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
        header.setUp(mPtrFrame);
        mPtrFrame.setLoadingMinTime(1000);
        mPtrFrame.setDurationToCloseHeader(1500);*/


        // mPtrFrame = (PtrFrameLayout) findViewById(R.id.ptr);
        mPtrFrame.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        mPtrFrame.setPtrHandler(new PtrHandler() {

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("MainActivity.onRefreshBegin");
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UpdateHttp();
                        TaskPro repo = new TaskPro(getActivity());
//                        taskList = repo.showStatusList(getPosition(), userAcc);
                        taskList = repo.showStatusList( userAcc);
//                        if(getPosition()==0) {
                            if(taskList.size()>0) {
                                Log.i(TAG, "item_main_content" + taskList.get(0).get("task_status"));
                                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_content, new String[]{"task_com_name", "task_com_addr", "task_end_date","task_status"}, new int[]{R.id.item_company_name, R.id.item_company_address, R.id.item_date,R.id.item_status});
                                setListAdapter(adapter);
                            }
                            else{
                                Toast.makeText(getActivity(),"无数据",Toast.LENGTH_SHORT).show();
                            }
//                        }
//                        else{
//                            if(taskList.size()>0) {
//                                Log.i(TAG, "item_main_over" + taskList.get(0).get("task_status"));
//                                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_over, new String[]{"task_com_name", "task_com_no", "task_status", "task_end_date"}, new int[]{R.id.item_company_name_2, R.id.item_company_no_2, R.id.item_audit_ruf_2, R.id.item_date_2});
//                                setListAdapter(adapter);
//                            }
//                            else{
//                                Toast.makeText(getActivity(),"无数据",Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
                        mPtrFrame.refreshComplete();
                        //mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("MainActivity.checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });

    }



    private void UpdateHttp(){
        //显示用户名
        String AUTH_TOKEN = sharedPrefs.getString("AUTH_TOKEN", "0");
        RequestParams params = new RequestParams(URL+"/MVNFHM/appInterface/appUpdateInfo");
        params.addParameter("AUTH_TOKEN", AUTH_TOKEN);
        Log.i("LoginActivity_post", "params："+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess" + result);
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                ServerBean serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                int err = serverBean.getError();
                String mag = serverBean.getMsg();
                if (err == 0) {
                    //解析成功
                    Log.i(TAG, "解析成功：" + mag);

                    ServerBean.User user = serverBean.getUser();
                    List<ServerBean.TaskBean> taskbeanList = serverBean.getTaskList();
                    List<ServerBean.MediaBean> mediabeanList = serverBean.getFileList();
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
                            TaskPro repo = new TaskPro(getActivity());
                            if (repo.getNo(bean.getTASK_NO())) {
                                repo.update(task);
                                Log.i(TAG, "task表更新:" + bean.getTASK_NO());
                            } else {
                                repo.insert(task);
                                Log.i(TAG, "task表插入:" + bean.getTASK_NO());
                            }
                        }
                    }
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

                            MediaPro repo1 = new MediaPro(getActivity());
                            if (repo1.getID(bean.getMT_ID())) {
                                repo1.update(media);
                                Log.i(TAG, "media表更新:" + bean.getMT_ID());


                            } else {
                                repo1.insert(media);
                                Log.i(TAG, "media表插入:" + bean.getMT_ID());


                            }
                        }
                    }

                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("AUTH_TOKEN", user.getAUTH_TOKEN());
                    editor.putString("USER_ACCOUNT", user.getUSER_ACCOUNT());
                    editor.putString("USER_NAME", user.getUSER_NAME());
                    editor.putString("USER_IDE", user.getUSER_IDE());
                    editor.putString("USER_TEL", user.getUSER_TEL());
                    editor.putString("USER_DEPT_NAME", user.getUSER_DEPT_NAME());
                    editor.putString("USER_DEPT_ORG_CODE", user.getUSER_DEPT_ORG_CODE());
                    editor.commit();


                } else {

                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("LogActivity","onError网络请求错误"+ex);
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
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG,"onResume");
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        TaskPro repo = new TaskPro(getActivity());
//        taskList = repo.showStatusList(getPosition(), userAcc);
        taskList = repo.showStatusList( userAcc);
//        if(getPosition()==0) {
            if(taskList.size()>0) {
                Log.i(TAG, "item_main_content" + taskList.get(0).get("task_status"));
                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_content, new String[]{"task_com_name", "task_com_addr", "task_end_date","task_status"}, new int[]{R.id.item_company_name, R.id.item_company_address, R.id.item_date,R.id.item_status});
                setListAdapter(adapter);
            }
            else{
                Toast.makeText(getActivity(),"无数据",Toast.LENGTH_SHORT).show();
            }
//        }
//        else{
//            if(taskList.size()>0) {
//                Log.i(TAG, "item_main_over" + taskList.get(0).get("task_status"));
//                ListAdapter adapter = new SimpleAdapter(getActivity(), taskList, R.layout.item_main_over, new String[]{"task_com_name", "task_com_no", "task_status", "task_end_date"}, new int[]{R.id.item_company_name_2, R.id.item_company_no_2, R.id.item_audit_ruf_2, R.id.item_date_2});
//                setListAdapter(adapter);
//            }

//        }
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String task_no=taskList.get(position).get("task_no");
        Intent objIndent = new Intent(getActivity(), TaskInfoActivity.class);
        objIndent.putExtra("task_no",task_no);
        startActivity(objIndent);
    }

}
