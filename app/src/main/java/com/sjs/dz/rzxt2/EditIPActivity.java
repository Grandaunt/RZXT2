package com.sjs.dz.rzxt2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditIPActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private EditText et_connect_name,et_connect_ip,et_connect_port;
    private Button okbtn;
    private SharedPreferences sharedPrefs;
    private String connect_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ip);
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_ip_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_left);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        et_connect_name  =  (EditText)findViewById(R.id.et_connect_name);
        et_connect_ip  =  (EditText)findViewById(R.id.et_connect_ip);
        et_connect_port =  (EditText)findViewById(R.id.et_connect_port);
        connect_name=  et_connect_name.getText().toString();
        okbtn =  (Button) findViewById(R.id.btn_ok_edit_ip);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //判断文本框是否为空
                 Log.i(TAG,et_connect_name.getText().toString());
                connect_name=  et_connect_name.getText().toString();
//                if(TextUtils.isEmpty(connect_name)) {
//                    Toast.makeText(EditIPActivity.this,"连接名不允许为空",Toast.LENGTH_SHORT).show();
//
//                }
//                else{
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("CONNECT_NAME", et_connect_name.getText().toString());
                    editor.putString("CONNECT_IP", et_connect_ip.getText().toString());
                    editor.putString("CONNECT_PORT", et_connect_port.getText().toString());
                    editor.commit();
                Log.i(TAG,"连接名："+sharedPrefs.getString("CONNECT_NAME", " "));
                Log.i(TAG,"连接IP："+sharedPrefs.getString("CONNECT_IP", " "));
                Log.i(TAG,"连接端口："+sharedPrefs.getString("CONNECT_PORT", " "));

                    System.exit(0);
//                Intent i = new Intent(EditIPActivity.this,MainActivity.class);
//                i.putExtra("usernameinfo", useraccount);
//                startActivity(i);
//                }
            }
        });



    }
    @Override
    protected void onResume() { //在另一个Activity中
        super.onResume();
        Log.i(TAG,"onResume获取IP");
//        if (!sharedPrefs.getString("CONNECT_IP", "null").equals("null")){

        et_connect_name.setText(sharedPrefs.getString("CONNECT_NAME", ""));
        et_connect_ip.setText(sharedPrefs.getString("CONNECT_IP",""));
        et_connect_port.setText(sharedPrefs.getString("CONNECT_PORT", ""));
//        }


        Log.i(TAG,"连接名："+sharedPrefs.getString("CONNECT_NAME", " "));
        Log.i(TAG,"连接IP："+sharedPrefs.getString("CONNECT_IP", " "));
        Log.i(TAG,"连接端口："+sharedPrefs.getString("CONNECT_PORT", " "));


    }
}
