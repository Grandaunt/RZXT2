package com.sjs.dz.rzxt2.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sjs.dz.rzxt2.Adapter.Contact;
import com.sjs.dz.rzxt2.Adapter.ContactAdapter;
import com.sjs.dz.rzxt2.DB.TaskPro;
import com.sjs.dz.rzxt2.R;
import com.sjs.dz.rzxt2.TaskInfoActivity;
import com.sjs.dz.rzxt2.Utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClearActivity extends Activity implements View.OnClickListener  {
    private String TAG = this.getClass().getSimpleName();
    private TextView tvMultiChoose;			//打开多选
    private TextView tvMultiChooseCancel;	//关闭多选

    private ListView lv;
    private ContactAdapter adapter;
    private List<Contact> contacts = new ArrayList<Contact>();
    private List<Contact> contactSelectedList = new ArrayList<Contact>(); 	//记录被选中过的item

    private LinearLayout llDeleteContainer;
    private TextView tvChooseAll;			//全选
    private TextView tvChooseDeletel;		//删除所选项
    private EditText etOverEvent;
    private Button sreachBtn;
    private ImageButton backBtn;
    private  ArrayList<HashMap<String, String>> taskList;
    private String userAcc = "";//信贷员
    private int task_status=3;//任务状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        findView();
        initView();

    }
    private void findView(){
        tvMultiChoose = (TextView)findViewById(R.id.tv_batchdelete);
        tvMultiChooseCancel = (TextView)findViewById(R.id.tv_batchdelete_cancel);
        tvMultiChoose.setOnClickListener(this);
        tvMultiChooseCancel.setOnClickListener(this);
        etOverEvent = (EditText)findViewById(R.id.etOverEvent);
        sreachBtn = (Button)findViewById(R.id.btn_sreach_over_event);
        sreachBtn.setOnClickListener(this);
        backBtn = (ImageButton)findViewById(R.id.im_back);
        backBtn.setOnClickListener(this);
        llDeleteContainer = (LinearLayout)findViewById(R.id.ll_delete_container);
        tvChooseAll = (TextView)findViewById(R.id.tv_choose_all);
        tvChooseDeletel = (TextView)findViewById(R.id.tv_choose_delete);


        tvChooseAll.setOnClickListener( this);
        tvChooseDeletel.setOnClickListener(this);
        lv = (ListView)findViewById(R.id.lv_recent_contact);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                TextView event_Id = (TextView) view.findViewById(R.id.tv_task_no);
//                String eventId = event_Id.getText().toString();
                //查询任务状态
//                EventRepo repo = new EventRepo(ManageCacheActivity.this);
                TaskPro repo = new TaskPro(ClearActivity.this);
//                taskList = repo.showStatusList(3,userAcc);
                taskList = repo.showStatusList(userAcc);
                Log.i(TAG,"taskList.get(0).get(task_status)="+taskList.get(0).get("task_status"));
//                ListAdapter adapter = new SimpleAdapter(ClearActivity.this, taskList, R.layout.item_main_over, new String[]{"task_com_name", "task_com_addr","task_status", "task_end_date"}, new int[]{R.id.item_company_name_2, R.id.item_company_address_2,R.id.item_audit_ruf_2, R.id.item_date_2});
//                setListAdapter(adapter);
//                String task_status = repo.getEventByTAG(eventId);
//                if(task_status.equals("3")){
//                    Intent objIndent = new Intent(ManageCacheActivity.this, EventEventShowActivity.class);
//                    objIndent.putExtra("eventinfo", eventId);
//                    Log.i(TAG, "eventID=" + eventId);
//                    startActivity(objIndent);
//                }
//                else {
                    Intent objIndent = new Intent(ClearActivity.this, TaskInfoActivity.class);
                    objIndent.putExtra("task_no", taskList.get(0).get("task_no"));
                    startActivity(objIndent);
//                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvMultiChoose.setVisibility(View.GONE);
                tvMultiChooseCancel.setVisibility(View.VISIBLE);
                llDeleteContainer.setVisibility(View.VISIBLE);

                contactSelectedList.clear();	//清空被选中的item项
                adapter = new ContactAdapter(ClearActivity.this, contacts, true);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        boolean isSelect = adapter.getisSelectedAt(position);

                        if(!isSelect){
                            //当前为被选中，记录下来，用于删除
                            contactSelectedList.add(contacts.get(position));
                        }else{
                            contactSelectedList.remove(contacts.get(position));
                        }

                        //选中状态的切换
                        adapter.setItemisSelectedMap(position, !isSelect);
                    }
                });
                return false;
            }
        });
    }

    private void initView(){
        //获取信贷员id
        SharedPreferences sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
         userAcc = sharedPrefs.getString("USER_ACCOUNT", "null");
        TaskPro repo = new TaskPro(ClearActivity.this);

        contacts = repo.getManageCache(userAcc,3);
        adapter = new ContactAdapter(this, contacts, false);
        lv.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id. btn_sreach_over_event:
                TaskPro repo = new TaskPro(ClearActivity.this);
                String overEventinfo = etOverEvent.getText().toString();
                overEventinfo = overEventinfo.replaceAll("\\s*","");

                if (TextUtils.isEmpty(overEventinfo) ) {

                }
                else {
                    contacts.clear();
                    contacts  = repo.getSreachManageCache(overEventinfo,userAcc);

                    /**
                     * 第三个参数表示是否显示checkbox
                     */
                    adapter = new ContactAdapter(this, contacts, false);
                    lv.setAdapter(adapter);
                    if (contacts.size() <= 0) {

                        Toast.makeText(ClearActivity.this, "未查到相关信息", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case R.id.tv_batchdelete:
                //打开多选
                tvMultiChoose.setVisibility(View.GONE);
                tvMultiChooseCancel.setVisibility(View.VISIBLE);
                llDeleteContainer.setVisibility(View.VISIBLE);

                contactSelectedList.clear();	//清空被选中的item项
                adapter = new ContactAdapter(this, contacts, true);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        boolean isSelect = adapter.getisSelectedAt(position);

                        if(!isSelect){
                            //当前为被选中，记录下来，用于删除
                            contactSelectedList.add(contacts.get(position));
                        }else{
                            contactSelectedList.remove(contacts.get(position));
                        }

                        //选中状态的切换
                        adapter.setItemisSelectedMap(position, !isSelect);
                    }
                });
                break;

            case R.id.tv_batchdelete_cancel: //关闭多选
                tvMultiChoose.setVisibility(View.VISIBLE);
                tvMultiChooseCancel.setVisibility(View.GONE);
                llDeleteContainer.setVisibility(View.GONE);

                contactSelectedList.clear();
                adapter = new ContactAdapter(this, contacts, false);
                lv.setAdapter(adapter);

                break;
            case R.id.tv_choose_delete: //删除所选项
                tvMultiChoose.setVisibility(View.VISIBLE);
                tvMultiChooseCancel.setVisibility(View.GONE);
                llDeleteContainer.setVisibility(View.GONE);

                for(Contact c : contactSelectedList){
                    //删除数据库
                    TaskPro repo1 = new TaskPro(ClearActivity.this);
                    repo1.delete(c.task_no);
                    //删除文件夹
                    Log.i(TAG,"/RZXT/" + userAcc + "/" + c.task_no);
                    String fileName = Environment.getExternalStorageDirectory()+"/RZXT/" + userAcc + "/" + c.task_no;
                    File file = new File(fileName);
                    FileUtils.delFile(file);

                    contacts.remove(c);
                }

                contactSelectedList.clear();
                adapter = new ContactAdapter(this, contacts, false);
                lv.setAdapter(adapter);

                break;
            case R.id.tv_choose_all: //全选

                for(int i=0; i<contacts.size(); i++){
                    adapter.setItemisSelectedMap(i, true);
                    contactSelectedList.add(contacts.get(i));
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {

        TaskPro repo = new TaskPro(ClearActivity.this);
        contacts = repo.getManageCache(userAcc,task_status);
        /**
         * 第三个参数表示是否显示checkbox
         */
        adapter = new ContactAdapter(this, contacts, false);
        lv.setAdapter(adapter);
        super.onResume();

    }


}
