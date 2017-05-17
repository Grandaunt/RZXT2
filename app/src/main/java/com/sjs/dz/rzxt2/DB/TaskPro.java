package com.sjs.dz.rzxt2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.sjs.dz.rzxt2.Adapter.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SJS on 2017/2/28.
 */

public class TaskPro {
    private  String  TAG="TaskPro";
    private DBHelper dbHelper;

    public TaskPro(Context context){
        dbHelper =new DBHelper(context);
    }

    public int insert(TASK task){
        //打开连接，写入数据
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
//        values.put(Goods.GoodsId,goods.goodsId);
        //记录主键
        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task.task_no);
        //任务类型
        values.put(TASK.TASK_TYPE,task.task_type);
        //任务状态
        values.put(TASK.TASK_STATUS,task.task_status);
        //初期资料是否齐全
        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);


        //检查机构代码
        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
        //检查机构名称
        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
        //检查类型(首次检查、定期检查、不定期检查)
        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
        //检查选项(按合同检查)
        values.put(TASK.TASK_CHECK_OPTION,task.task_check_option);
        //任务检查人账号
        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);


        //任务检查人姓名
        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
        //任务开始日期
        values.put(TASK.TASK_START_DATE,task.task_start_date);
        //任务截止日期
        values.put(TASK.TASK_END_DATE,task.task_end_date);
        //任务完成日期
        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
        //审核人账号
        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);


        //审核人姓名
        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
        //申请公司编号
        values.put(TASK.TASK_COM_NO,task.task_com_no);
        //申请公司名称
        values.put(TASK.TASK_COM_NAME,task.task_com_name);
        //申请合同编号
        values.put(TASK.TASK_CON_NO,task.task_con_no);
        //申请项目编号
        values.put(TASK.TASK_ITEN_NO,task.task_item_no);


        //申请产品编号
        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
        //申请产品名称
        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
        //申请生产类型
        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
        //申请认证范围
        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
        //申请认证类型
        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);

        //申请公司地址
        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
        //申请公司电话
        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
        //申请公司邮编
        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
        //申请公司邮箱
        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
        //申请公司传真
        values.put(TASK.TASK_COM_FAX,task.task_com_fax);


        //申请公司网络url
        values.put(TASK.TASK_WEB_URL,task.task_web_url);
        //公司联系人姓名
        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
        //公司联系人电话
        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
        //公司联系人职位
        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
        //申请产品介绍
        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);


        //信息采集模板ID
        values.put(TASK.TASK_MT_ID,task.task_mt_id);
        //任务备注
        values.put(TASK.TASK_NOTE,task.task_note);
        //申请单号
        values.put(TASK.APPLY_ID,task.apply_id);

        long task_Id=db.insert(TASK.TABLE,null,values);
        db.close();
        Log.i(TAG,"task_Id="+task_Id);
        return (int)task_Id;
    }


    public void delete(String task_Id){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(TASK.TABLE,TASK.TASK_NO+"=?", new String[]{task_Id});
        db.close();
    }
    public void update(TASK task){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
//        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task.task_no);
        //任务类型
        values.put(TASK.TASK_TYPE,task.task_type);
        //任务状态
        values.put(TASK.TASK_STATUS,task.task_status);
        //初期资料是否齐全
        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);


        //检查机构代码
        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
        //检查机构名称
        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
        //检查类型(首次检查、定期检查、不定期检查)
        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
        //检查选项(按合同检查)
        values.put(TASK.TASK_CHECK_OPTION,task.task_check_option);
        //任务检查人账号
        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);


        //任务检查人姓名
        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
        //任务开始日期
        values.put(TASK.TASK_START_DATE,task.task_start_date);
        //任务截止日期
        values.put(TASK.TASK_END_DATE,task.task_end_date);
        //任务完成日期
        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
        //审核人账号
        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);


        //审核人姓名
        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
        //申请公司编号
        values.put(TASK.TASK_COM_NO,task.task_com_no);
        //申请公司名称
        values.put(TASK.TASK_COM_NAME,task.task_com_name);
        //申请合同编号
        values.put(TASK.TASK_CON_NO,task.task_con_no);
        //申请项目编号
        values.put(TASK.TASK_ITEN_NO,task.task_item_no);


        //申请产品编号
        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
        //申请产品名称
        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
        //申请生产类型
        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
        //申请认证范围
        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
        //申请认证类型
        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);

        //申请公司地址
        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
        //申请公司电话
        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
        //申请公司邮编
        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
        //申请公司邮箱
        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
        //申请公司传真
        values.put(TASK.TASK_COM_FAX,task.task_com_fax);


        //申请公司网络url
        values.put(TASK.TASK_WEB_URL,task.task_web_url);
        //公司联系人姓名
        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
        //公司联系人电话
        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
        //公司联系人职位
        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
        //申请产品介绍
        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);


        //信息采集模板ID
        values.put(TASK.TASK_MT_ID,task.task_mt_id);
        //任务备注
        values.put(TASK.TASK_NOTE,task.task_note);
        //申请单号
        values.put(TASK.APPLY_ID,task.apply_id);

        db.update(TASK.TABLE,values,TASK.TASK_NO+"=?",new String[] { String.valueOf(task.task_no) });
        db.close();



    }
    public void updateNote(String task_no,String task_note){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
//        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task_no);
//        //任务类型
//        values.put(TASK.TASK_TYPE,task.task_type);
//        //任务状态
//        values.put(TASK.TASK_STATUS,task.task_status);
//        //初期资料是否齐全
//        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);
//
//
//        //检查机构代码
//        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
//        //检查机构名称
//        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
//        //检查类型(首次检查、定期检查、不定期检查)
//        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
//        //检查选项(按合同检查)
//        values.put(TASK.TASK_CHECK_OPTION,task.task_check_option);
//        //任务检查人账号
//        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);
//
//
//        //任务检查人姓名
//        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
//        //任务开始日期
//        values.put(TASK.TASK_START_DATE,task.task_start_date);
//        //任务截止日期
//        values.put(TASK.TASK_END_DATE,task.task_end_date);
//        //任务完成日期
//        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
//        //审核人账号
//        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);
//
//
//        //审核人姓名
//        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
//        //申请公司编号
//        values.put(TASK.TASK_COM_NO,task.task_com_no);
//        //申请公司名称
//        values.put(TASK.TASK_COM_NAME,task.task_com_name);
//        //申请合同编号
//        values.put(TASK.TASK_CON_NO,task.task_con_no);
//        //申请项目编号
//        values.put(TASK.TASK_ITEN_NO,task.task_item_no);
//
//
//        //申请产品编号
//        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
//        //申请产品名称
//        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
//        //申请生产类型
//        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
//        //申请认证范围
//        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
//        //申请认证类型
//        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);
//
//        //申请公司地址
//        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
//        //申请公司电话
//        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
//        //申请公司邮编
//        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
//        //申请公司邮箱
//        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
//        //申请公司传真
//        values.put(TASK.TASK_COM_FAX,task.task_com_fax);
//
//
//        //申请公司网络url
//        values.put(TASK.TASK_WEB_URL,task.task_web_url);
//        //公司联系人姓名
//        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
//        //公司联系人电话
//        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
//        //公司联系人职位
//        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
//        //申请产品介绍
//        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);
//
//
//        //信息采集模板ID
//        values.put(TASK.TASK_MT_ID,task.task_mt_id);
//        //任务备注
        values.put(TASK.TASK_NOTE,task_note);
        //申请单号
//        values.put(TASK.APPLY_ID,task.apply_id);

        db.update(TASK.TABLE,values,TASK.TASK_NO+"=?",new String[] { String.valueOf(task_no) });
        db.close();



    }
    public String updateStatus(String task_no,String status){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
//        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task_no);
//        //任务类型
//        values.put(TASK.TASK_TYPE,task.task_type);
//        //任务状态
        values.put(TASK.TASK_STATUS,status);
//        //初期资料是否齐全
//        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);
//
//
//        //检查机构代码
//        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
//        //检查机构名称
//        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
//        //检查类型(首次检查、定期检查、不定期检查)
//        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
//        //检查选项(按合同检查)
//        values.put(TASK.TASK_CHECK_OPTION,task.task_check_option);
//        //任务检查人账号
//        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);
//
//
//        //任务检查人姓名
//        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
//        //任务开始日期
//        values.put(TASK.TASK_START_DATE,task.task_start_date);
//        //任务截止日期
//        values.put(TASK.TASK_END_DATE,task.task_end_date);
//        //任务完成日期
//        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
//        //审核人账号
//        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);
//
//
//        //审核人姓名
//        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
//        //申请公司编号
//        values.put(TASK.TASK_COM_NO,task.task_com_no);
//        //申请公司名称
//        values.put(TASK.TASK_COM_NAME,task.task_com_name);
//        //申请合同编号
//        values.put(TASK.TASK_CON_NO,task.task_con_no);
//        //申请项目编号
//        values.put(TASK.TASK_ITEN_NO,task.task_item_no);
//
//
//        //申请产品编号
//        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
//        //申请产品名称
//        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
//        //申请生产类型
//        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
//        //申请认证范围
//        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
//        //申请认证类型
//        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);
//
//        //申请公司地址
//        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
//        //申请公司电话
//        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
//        //申请公司邮编
//        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
//        //申请公司邮箱
//        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
//        //申请公司传真
//        values.put(TASK.TASK_COM_FAX,task.task_com_fax);
//
//
//        //申请公司网络url
//        values.put(TASK.TASK_WEB_URL,task.task_web_url);
//        //公司联系人姓名
//        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
//        //公司联系人电话
//        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
//        //公司联系人职位
//        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
//        //申请产品介绍
//        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);
//
//
//        //信息采集模板ID
//        values.put(TASK.TASK_MT_ID,task.task_mt_id);
//        //任务备注
//        values.put(TASK.TASK_NOTE,task_note);
        //申请单号
//        values.put(TASK.APPLY_ID,task.apply_id);

        db.update(TASK.TABLE,values,TASK.TASK_NO+"=?",new String[] { String.valueOf(task_no) });
        db.close();
        return status;



    }
    //查询该num编号的下载信息
    public MEDIA  getMedialistDown(String num){
        SQLiteDatabase db= dbHelper.getWritableDatabase();


        String selectQuery="SELECT "
                +TASK.TASK_NO+","
                +TASK.TASK_MT_ID+
                " FROM "+TASK.TABLE
                + " WHERE " +
                TASK.TASK_NO + "=?";
        int iCount=0;
        String MT_ID = null;


        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(num)});
        if(cursor.moveToFirst()){
            do{
                MT_ID=cursor.getString(cursor.getColumnIndex(TASK.TASK_MT_ID));
            }while(cursor.moveToNext());
        }
             selectQuery="SELECT "
                + MEDIA.MT_ID+","
                + MEDIA.MT_NO+","
                + MEDIA.MT_NAME+","
                + MEDIA.MT_ITEM_INFO+","
                + MEDIA.MT_U_7_DESC+","
                + MEDIA.MT_U_8_DESC+","
                + MEDIA.MT_U_9_DESC+","
                + MEDIA.MT_D_DESC+","
//                     + MEDIA.MT_U_IM_NUM+","
//                     + MEDIA.MT_U_IM_DESC+","
//
//
//                + MEDIA.MT_U_W_NUM+","
//                + MEDIA.MT_U_W_DESC+","
//                + MEDIA.MT_U_P_NUM+","
//                + MEDIA.MT_U_P_DESC+","
//                + MEDIA.MT_U_E_NUM+","
//
//
//                + MEDIA.MT_U_E_DESC+","
//                + MEDIA.MT_D_IM_NUM+","
//                + MEDIA.MT_D_IM_DESC+","
//                + MEDIA.MT_D_W_NUM+","
//                + MEDIA.MT_D_W_DESC+","
//
//
//                + MEDIA.MT_D_P_NUM+","
//                + MEDIA. MT_D_P_DESC+","
//                + MEDIA.MT_D_E_NUM+","
//                + MEDIA.MT_D_E_DESC+","
                + MEDIA.MT_IS_NOTE+","


                + MEDIA.MT_STATUS+
                " FROM "+MEDIA.TABLE
                + " WHERE " +
                MEDIA.MT_NO + "=?";

        Cursor  cursor1=db.rawQuery(selectQuery,new String[]{String.valueOf(MT_ID)});
                MEDIA media=new MEDIA();
        if(cursor1.moveToFirst()){
            do{

                media.mt_id = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_ID));
                media.mt_no = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_NO));
                media.mt_name = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_NAME));
                media.mt_item_info = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_ITEM_INFO));
                media.mt_u_7_desc = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_U_7_DESC));
                media.mt_u_8_desc = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_U_8_DESC));
                media.mt_u_9_desc = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_U_9_DESC));
                media.mt_d_desc = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_D_DESC));
//                media.mt_d_im_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_IM_NUM));
//                media.mt_d_im_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_IM_DESC));

//                media.mt_d_w_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_W_NUM));
//                media.mt_d_w_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_W_DESC));
//                media.mt_d_p_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_P_NUM));
//                media.mt_d_p_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_P_DESC));
//                media.mt_d_e_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_E_NUM));
//
//                media.mt_d_e_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_D_E_DESC));
//                media.mt_u_im_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_IM_NUM));
//                media.mt_u_im_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_IM_DESC));
//                media.mt_u_p_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_P_NUM));
//                media.mt_u_p_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_P_DESC));
//
//                media.mt_u_e_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_E_NUM));
//                media.mt_u_e_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_E_DESC));
//                media.mt_u_w_num = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_W_NUM));
//                media.mt_u_w_desc = cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_W_DESC));
                media.mt_is_note = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_IS_NOTE));


                media.mt_status = cursor1.getString(cursor1.getColumnIndex(MEDIA.MT_STATUS));

            }while(cursor1.moveToNext());
        }
        cursor.close();
        cursor1.close();
        db.close();
        return media;
    }


    //查询goods表id
    public boolean getNo(String No){
        boolean rel = false;

        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                TASK.TASK_NO+

                " FROM "+TASK.TABLE +
                "  WHERE " + TASK.TASK_NO + " = '" + No + "'"+" LIMIT 1";


        System.out.println(selectQuery);
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            rel=true;

        }
        Log.i(TAG,"查询结果：rel="+rel);
        cursor.close();
        db.close();
        return rel;
    }


    //根据任务号查询任务信息
    public ArrayList<HashMap<String, String>> showTaskInfo(String task_no){

        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="";
            selectQuery="SELECT "
                    +TASK.TASK_ID+","
                    +TASK.TASK_NO+","
                    +TASK.TASK_TYPE+","
                    +TASK.TASK_STATUS+","
                    +TASK.TASK_IS_EARLY_FILE+","

                    +TASK.TASK_CHECK_ORG_NO+","
                    +TASK.TASK_CHECK_ORG_NAME+","
                    +TASK.TASK_CHECK_TYPE+","
                    +TASK.TASK_CHECK_OPTION+","
                    +TASK.TASK_INER_ACC+","

                    +TASK.TASK_INER_NAME+","
                    +TASK.TASK_START_DATE+","
                    +TASK.TASK_END_DATE+","
                    +TASK.TASK_FINISH_DATE+","
                    +TASK.TASK_AUDIT_ACC+","

                    +TASK.TASK_AUDIT_NAME+","
                    +TASK.TASK_COM_NO+","
                    +TASK.TASK_COM_NAME+","
                    +TASK.TASK_CON_NO+","
                    +TASK.TASK_ITEN_NO+","

                    +TASK.TASK_PRDT_NO+","
                    +TASK.TASK_PRDT_NAME+","
                    +TASK.TASK_PRDT_TYPE+","
                    +TASK.TASK_RZ_SCOPE+","
                    +TASK.TASK_RZ_TYPE+","

                    +TASK.TASK_COM_ADDR+","
                    +TASK.TASK_COM_TEL+","
                    +TASK.TASK_COM_POST_CODE+","
                    +TASK.TASK_COM_EMAIL+","
                    +TASK.TASK_COM_FAX+","

                    +TASK.TASK_WEB_URL+","
                    +TASK.TASK_COM_CON_NAME+","
                    +TASK.TASK_COM_CON_TEL+","
                    +TASK.TASK_COM_CON_IDE+","
                    +TASK.TASK_ITEM_INFO+","

                    +TASK.TASK_MT_ID+","
                    +TASK.TASK_NOTE+","
                    +TASK.APPLY_ID+
                    " FROM "+TASK.TABLE
                    +" WHERE "+  TASK.TASK_NO +" = '"+task_no+"'";


        Log.i(TAG,selectQuery);
        ArrayList<HashMap<String,String>> showTaskList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> task=new HashMap<String,String>();

                task.put("task_id",cursor.getString(cursor.getColumnIndex(TASK.TASK_ID)));
                task.put("task_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_NO)));
                task.put("task_status", cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)));
                task.put("task_com_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME)));
                task.put("task_com_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO)));

                task.put("task_com_addr", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_ADDR)));
                task.put("task_com_tel", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_TEL)));
                task.put("task_end_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE)));
                task.put("task_com_con_name",  cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_NAME)));
                task.put("task_com_con_ide", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_IDE)));

                task.put("task_com_con_tel",cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_TEL)));
                task.put("task_com_email", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_EMAIL)));
                task.put("task_com_fax", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_FAX)));
                task.put("task_com_poat_code", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_POST_CODE)));
                task.put("task_note", cursor.getString(cursor.getColumnIndex(TASK.TASK_NOTE)));


                task.put("task_type",cursor.getString(cursor.getColumnIndex(TASK.TASK_TYPE)));
                task.put("task_is_early_file", cursor.getString(cursor.getColumnIndex(TASK.TASK_IS_EARLY_FILE)));
                task.put("task_check_org_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NO)));
                task.put("task_check_org_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NAME)));
                task.put("task_check_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_TYPE)));

                task.put("task_check_option",cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_OPTION)));
                task.put("task_start_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_INER_NAME)));
                task.put("task_finish_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_FINISH_DATE)));
                task.put("task_audit_acc", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_ACC)));
                task.put("task_audit_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_NAME)));


                task.put("task_item_no",cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEN_NO)));
                task.put("task_prdt_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_NAME)));
                task.put("task_prdt_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_TYPE)));
                task.put("task_rz_scope", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_SCOPE)));
                task.put("task_rz_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_TYPE)));

                task.put("task_web_url",cursor.getString(cursor.getColumnIndex(TASK.TASK_WEB_URL)));
                task.put("task_item_info", cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEM_INFO)));
                task.put("apply_id", cursor.getString(cursor.getColumnIndex(TASK.APPLY_ID)));

                showTaskList.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return showTaskList;

    }
    public List<Contact> getSreachManageCache(String info, String userAccount){
        //获取全部eventlist数据
        Log.i(TAG,"ArrayList<HashMap<String, String>> getEventList()");
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                TASK.TASK_NO+","+
                TASK.TASK_COM_NAME+","+
                TASK.TASK_END_DATE+","+
                TASK.TASK_STATUS+

                "  FROM "+TASK.TABLE

                +" WHERE "+ TASK.TASK_INER_ACC
                +"= '"+userAccount+"'"
                +"and "+ TASK.TASK_STATUS +" = ' "+userAccount+"'"
                ;
        Log.i(TAG,userAccount+"查询语句为："+selectQuery);

        List<Contact> eventList=new ArrayList<Contact>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.i(TAG,selectQuery);
        if(cursor.moveToFirst()){
            do{

                Contact contact = new Contact();
                contact.task_no = cursor.getString(cursor.getColumnIndex(TASK.TASK_NO));
                contact.task_com_name = cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME));
                contact.task_end_date = cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE));

                contact.task_status = "审批通过";




                eventList.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventList;
    }

    //根据搜索内容查询该信贷员任务状态0的数据
    public ArrayList<HashMap<String, String>> showSreach(String info ,int position,String userAccount) {
        //获取全部eventlist数据
        String selectQuery = "";
        Log.i(TAG, "ArrayList<HashMap<String, String>> getEventList()");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        selectQuery = "SELECT "
                +TASK.TASK_ID+","
                +TASK.TASK_NO+","
                +TASK.TASK_TYPE+","
                +TASK.TASK_STATUS+","
                +TASK.TASK_IS_EARLY_FILE+","

                +TASK.TASK_CHECK_ORG_NO+","
                +TASK.TASK_CHECK_ORG_NAME+","
                +TASK.TASK_CHECK_TYPE+","
                +TASK.TASK_CHECK_OPTION+","
                +TASK.TASK_INER_ACC+","

                +TASK.TASK_INER_NAME+","
                +TASK.TASK_START_DATE+","
                +TASK.TASK_END_DATE+","
                +TASK.TASK_FINISH_DATE+","
                +TASK.TASK_AUDIT_ACC+","

                +TASK.TASK_AUDIT_NAME+","
                +TASK.TASK_COM_NO+","
                +TASK.TASK_COM_NAME+","
                +TASK.TASK_CON_NO+","
                +TASK.TASK_ITEN_NO+","

                +TASK.TASK_PRDT_NO+","
                +TASK.TASK_PRDT_NAME+","
                +TASK.TASK_PRDT_TYPE+","
                +TASK.TASK_RZ_SCOPE+","
                +TASK.TASK_RZ_TYPE+","

                +TASK.TASK_COM_ADDR+","
                +TASK.TASK_COM_TEL+","
                +TASK.TASK_COM_POST_CODE+","
                +TASK.TASK_COM_EMAIL+","
                +TASK.TASK_COM_FAX+","

                +TASK.TASK_WEB_URL+","
                +TASK.TASK_COM_CON_NAME+","
                +TASK.TASK_COM_CON_TEL+","
                +TASK.TASK_COM_CON_IDE+","
                +TASK.TASK_ITEM_INFO+","

                +TASK.TASK_MT_ID+","
                +TASK.TASK_NOTE+","
                +TASK.APPLY_ID+
                "  FROM " + TASK.TABLE
                + " WHERE " + TASK.TASK_INER_ACC
                + "= '" + userAccount + "'";
        if (position == 0){
            selectQuery=selectQuery+


//                    "SELECT "
//                    +TASK.TASK_ID+","
//                    +TASK.TASK_NO+","
//                    +TASK.TASK_TYPE+","
//                    +TASK.TASK_STATUS+","
//                    +TASK.TASK_IS_EARLY_FILE+","
//
//                    +TASK.TASK_CHECK_ORG_NO+","
//                    +TASK.TASK_CHECK_ORG_NAME+","
//                    +TASK.TASK_CHECK_TYPE+","
//                    +TASK.TASK_CHECK_OPTION+","
//                    +TASK.TASK_INER_ACC+","
//
//                    +TASK.TASK_INER_NAME+","
//                    +TASK.TASK_START_DATE+","
//                    +TASK.TASK_END_DATE+","
//                    +TASK.TASK_FINISH_DATE+","
//                    +TASK.TASK_AUDIT_ACC+","
//
//                    +TASK.TASK_AUDIT_NAME+","
//                    +TASK.TASK_COM_NO+","
//                    +TASK.TASK_COM_NAME+","
//                    +TASK.TASK_CON_NO+","
//                    +TASK.TASK_ITEN_NO+","
//
//                    +TASK.TASK_PRDT_NO+","
//                    +TASK.TASK_PRDT_NAME+","
//                    +TASK.TASK_PRDT_TYPE+","
//                    +TASK.TASK_RZ_SCOPE+","
//                    +TASK.TASK_RZ_TYPE+","
//
//                    +TASK.TASK_COM_ADDR+","
//                    +TASK.TASK_COM_TEL+","
//                    +TASK.TASK_COM_POST_CODE+","
//                    +TASK.TASK_COM_EMAIL+","
//                    +TASK.TASK_COM_FAX+","
//
//                    +TASK.TASK_WEB_URL+","
//                    +TASK.TASK_COM_CON_NAME+","
//                    +TASK.TASK_COM_CON_TEL+","
//                    +TASK.TASK_COM_CON_IDE+","
//                    +TASK.TASK_ITEM_INFO+","
//
//                    +TASK.TASK_MT_ID+","
//                    +TASK.TASK_NOTE+","
//                    +TASK.APPLY_ID+
//
//                    "  FROM " + TASK.TABLE
//                    + " WHERE " + TASK.TASK_INER_ACC
//                    + "= '" + userAccount + "'"
//                     "and " + TASK.TASK_STATUS + " = '" + position + "'"
//                    + "  and " + TASK.TASK_NO + " LIKE '%" + info + "%'" +
//                    " and " + TASK.TASK_COM_NO + " LIKE '%" + info + "%'" +
//                    " and " + TASK.TASK_END_DATE + " LIKE '%" + info + "%'"+
                    " and " + TASK.TASK_COM_NAME + " LIKE '%" + info + "%'"
//                    " and " + TASK.TASK_COM_ADDR + " LIKE '%" + info + "%'"
                    ;
    } else
      {
          selectQuery= selectQuery + "and " + TASK.TASK_STATUS + ">'" + position + "'"
//                    + "  and " + TASK.TASK_NO + " LIKE '%" + info + "%'" +
//                    " and " + TASK.TASK_COM_NO + " LIKE '%" + info + "%'" +
//                    " and " + TASK.TASK_END_DATE + " LIKE '%" + info + "%'"+
                   + " and " + TASK.TASK_COM_NAME + " LIKE '%" + info + "%'"
//                   + " and " + TASK.TASK_COM_ADDR + " LIKE '%" + info + "%'"
            ;
        }
        Log.i(TAG,selectQuery);
        ArrayList<HashMap<String,String>> showTaskList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> task=new HashMap<String,String>();

                task.put("task_id",cursor.getString(cursor.getColumnIndex(TASK.TASK_ID)));
                task.put("task_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_NO)));
                /***
                 * 0：待处理
                 1：待上传
                 2：已上传，正在审核
                 3：审核通过
                 4：审核失败
                 5：任务失效
                 */

                if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("0")) {
                    task.put("task_status","待处理");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("2")) {
                    task.put("task_status","正在审核");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("3")) {
                    task.put("task_status","审核通过");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("4")) {
                    task.put("task_status","审核失败");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("5")) {
                    task.put("task_status","任务失效");
                }
//                task.put("task_status", cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)));
                task.put("task_com_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME)));
                task.put("task_com_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO)));

                task.put("task_com_addr", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_ADDR)));
                task.put("task_com_tel", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_TEL)));
                task.put("task_end_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE)));
                task.put("task_com_con_name",  cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_NAME)));
                task.put("task_com_con_ide", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_IDE)));

                task.put("task_com_con_tel",cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_TEL)));
                task.put("task_com_email", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_EMAIL)));
                task.put("task_com_fax", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_FAX)));
                task.put("task_com_poat_code", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_POST_CODE)));
                task.put("task_note", cursor.getString(cursor.getColumnIndex(TASK.TASK_NOTE)));


                task.put("task_type",cursor.getString(cursor.getColumnIndex(TASK.TASK_TYPE)));
                task.put("task_is_early_file", cursor.getString(cursor.getColumnIndex(TASK.TASK_IS_EARLY_FILE)));
                task.put("task_check_org_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NO)));
                task.put("task_check_org_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NAME)));
                task.put("task_check_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_TYPE)));

                task.put("task_check_option",cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_OPTION)));
                task.put("task_start_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_INER_NAME)));
                task.put("task_finish_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_FINISH_DATE)));
                task.put("task_audit_acc", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_ACC)));
                task.put("task_audit_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_NAME)));


                task.put("task_item_no",cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEN_NO)));
                task.put("task_prdt_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_NAME)));
                task.put("task_prdt_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_TYPE)));
                task.put("task_rz_scope", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_SCOPE)));
                task.put("task_rz_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_TYPE)));

                task.put("task_web_url",cursor.getString(cursor.getColumnIndex(TASK.TASK_WEB_URL)));
                task.put("task_item_info", cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEM_INFO)));
                task.put("apply_id", cursor.getString(cursor.getColumnIndex(TASK.APPLY_ID)));

                showTaskList.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return showTaskList;

    }

    //根据搜索内容查询该信贷员任务状态0的数据
//    public ArrayList<HashMap<String, String>> showStatusList(int position,String userAccount) {
        public ArrayList<HashMap<String, String>> showStatusList(String userAccount) {
        //获取全部eventlist数据
        String selectQuery = "";
        Log.i(TAG, "ArrayList<HashMap<String, String>> getEventList()");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        if (position == 0){
            selectQuery = "SELECT "
                    +TASK.TASK_ID+","
                    +TASK.TASK_NO+","
                    +TASK.TASK_TYPE+","
                    +TASK.TASK_STATUS+","
                    +TASK.TASK_IS_EARLY_FILE+","

                    +TASK.TASK_CHECK_ORG_NO+","
                    +TASK.TASK_CHECK_ORG_NAME+","
                    +TASK.TASK_CHECK_TYPE+","
                    +TASK.TASK_CHECK_OPTION+","
                    +TASK.TASK_INER_ACC+","

                    +TASK.TASK_INER_NAME+","
                    +TASK.TASK_START_DATE+","
                    +TASK.TASK_END_DATE+","
                    +TASK.TASK_FINISH_DATE+","
                    +TASK.TASK_AUDIT_ACC+","

                    +TASK.TASK_AUDIT_NAME+","
                    +TASK.TASK_COM_NO+","
                    +TASK.TASK_COM_NAME+","
                    +TASK.TASK_CON_NO+","
                    +TASK.TASK_ITEN_NO+","

                    +TASK.TASK_PRDT_NO+","
                    +TASK.TASK_PRDT_NAME+","
                    +TASK.TASK_PRDT_TYPE+","
                    +TASK.TASK_RZ_SCOPE+","
                    +TASK.TASK_RZ_TYPE+","

                    +TASK.TASK_COM_ADDR+","
                    +TASK.TASK_COM_TEL+","
                    +TASK.TASK_COM_POST_CODE+","
                    +TASK.TASK_COM_EMAIL+","
                    +TASK.TASK_COM_FAX+","

                    +TASK.TASK_WEB_URL+","
                    +TASK.TASK_COM_CON_NAME+","
                    +TASK.TASK_COM_CON_TEL+","
                    +TASK.TASK_COM_CON_IDE+","
                    +TASK.TASK_ITEM_INFO+","

                    +TASK.TASK_MT_ID+","
                    +TASK.TASK_NOTE+","
                    +TASK.APPLY_ID+

                    "  FROM " + TASK.TABLE
                    + " WHERE " + TASK.TASK_INER_ACC
                    + "= '" + userAccount + "'"
//                    + "and " + TASK.TASK_STATUS + " = '" + position + "'"
            ;
//        } else {
//            selectQuery = "SELECT "
//                    +TASK.TASK_ID+","
//                    +TASK.TASK_NO+","
//                    +TASK.TASK_TYPE+","
//                    +TASK.TASK_STATUS+","
//                    +TASK.TASK_IS_EARLY_FILE+","
//
//                    +TASK.TASK_CHECK_ORG_NO+","
//                    +TASK.TASK_CHECK_ORG_NAME+","
//                    +TASK.TASK_CHECK_TYPE+","
//                    +TASK.TASK_CHECK_OPTION+","
//                    +TASK.TASK_INER_ACC+","
//
//                    +TASK.TASK_INER_NAME+","
//                    +TASK.TASK_START_DATE+","
//                    +TASK.TASK_END_DATE+","
//                    +TASK.TASK_FINISH_DATE+","
//                    +TASK.TASK_AUDIT_ACC+","
//
//                    +TASK.TASK_AUDIT_NAME+","
//                    +TASK.TASK_COM_NO+","
//                    +TASK.TASK_COM_NAME+","
//                    +TASK.TASK_CON_NO+","
//                    +TASK.TASK_ITEN_NO+","
//
//
//                    +TASK.TASK_PRDT_NO+","
//                    +TASK.TASK_PRDT_NAME+","
//                    +TASK.TASK_PRDT_TYPE+","
//                    +TASK.TASK_RZ_SCOPE+","
//                    +TASK.TASK_RZ_TYPE+","
//
//                    +TASK.TASK_COM_ADDR+","
//                    +TASK.TASK_COM_TEL+","
//                    +TASK.TASK_COM_POST_CODE+","
//                    +TASK.TASK_COM_EMAIL+","
//                    +TASK.TASK_COM_FAX+","
//
//                    +TASK.TASK_WEB_URL+","
//                    +TASK.TASK_COM_CON_NAME+","
//                    +TASK.TASK_COM_CON_TEL+","
//                    +TASK.TASK_COM_CON_IDE+","
//                    +TASK.TASK_ITEM_INFO+","
//
//                    +TASK.TASK_MT_ID+","
//                    +TASK.TASK_NOTE+","
//                    +TASK.APPLY_ID+
//                    "  FROM " + TASK.TABLE
//                    + " WHERE " + TASK.TASK_INER_ACC
//                    + "= '" + userAccount + "'"
//                    + "and " + TASK.TASK_STATUS + ">'" + position + "'";
//        }
        Log.i(TAG,selectQuery);
        ArrayList<HashMap<String,String>> showTaskList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> task=new HashMap<String,String>();

                task.put("task_id",cursor.getString(cursor.getColumnIndex(TASK.TASK_ID)));
                task.put("task_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_NO)));
                /***
                 * 0：待处理
                 1：待上传
                 2：已上传，正在审核
                 3：审核通过
                 4：审核失败
                 5：任务失效
                 */

                if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("0")) {
                    task.put("task_status","待处理");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("2")) {
                    task.put("task_status","正在审核");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("3")) {
                    task.put("task_status","审核通过");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("4")) {
                    task.put("task_status","审核失败");
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("5")) {
                    task.put("task_status","任务失效");
                }
//                task.put("task_status", cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)));
//                task.put("task_com_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME)));
                task.put("task_com_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME)));
                task.put("task_com_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO)));


                task.put("task_com_addr", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_ADDR)));
                task.put("task_com_tel", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_TEL)));
                task.put("task_end_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE)));
                task.put("task_com_con_name",  cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_NAME)));
                task.put("task_com_con_ide", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_IDE)));

                task.put("task_com_con_tel",cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_CON_TEL)));
                task.put("task_com_email", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_EMAIL)));
                task.put("task_com_fax", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_FAX)));
                task.put("task_com_poat_code", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_POST_CODE)));
                task.put("task_note", cursor.getString(cursor.getColumnIndex(TASK.TASK_NOTE)));


                task.put("task_type",cursor.getString(cursor.getColumnIndex(TASK.TASK_TYPE)));
                task.put("task_is_early_file", cursor.getString(cursor.getColumnIndex(TASK.TASK_IS_EARLY_FILE)));
                task.put("task_check_org_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NO)));
                task.put("task_check_org_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_ORG_NAME)));
                task.put("task_check_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_TYPE)));

                task.put("task_check_option",cursor.getString(cursor.getColumnIndex(TASK.TASK_CHECK_OPTION)));
                task.put("task_start_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_INER_NAME)));
                task.put("task_finish_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_FINISH_DATE)));
                task.put("task_audit_acc", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_ACC)));
                task.put("task_audit_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_AUDIT_NAME)));


                task.put("task_item_no",cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEN_NO)));
                task.put("task_prdt_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_NAME)));
                task.put("task_prdt_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_PRDT_TYPE)));
                task.put("task_rz_scope", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_SCOPE)));
                task.put("task_rz_type", cursor.getString(cursor.getColumnIndex(TASK.TASK_RZ_TYPE)));

                task.put("task_web_url",cursor.getString(cursor.getColumnIndex(TASK.TASK_WEB_URL)));
                task.put("task_item_info", cursor.getString(cursor.getColumnIndex(TASK.TASK_ITEM_INFO)));
                task.put("apply_id", cursor.getString(cursor.getColumnIndex(TASK.APPLY_ID)));

                showTaskList.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return showTaskList;

    }

    //查询该信贷员任务状态为3的数据
    public List<Contact> getManageCache(String userAccount, int ln_task_status){
        //获取全部eventlist数据
        Log.i(TAG,"ArrayList<HashMap<String, String>> getEventList()");
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                TASK.TASK_COM_NAME+","+
                TASK.TASK_NO+","+
                TASK.TASK_END_DATE+","+
                TASK.TASK_STATUS+","+
                TASK.TASK_COM_NO+","+
                TASK.TASK_INER_ACC+

                "  FROM "+TASK.TABLE
                +" WHERE "+ TASK.TASK_INER_ACC
                +"= '"+userAccount+"'"
                +"and "+ TASK.TASK_STATUS +" = '"+ln_task_status+"'"
                ;
        Log.i(TAG,ln_task_status+"查询语句为："+selectQuery);
        List<Contact> eventList=new ArrayList<Contact>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.i(TAG,selectQuery);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.task_com_name = cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME));
                contact.task_no = cursor.getString(cursor.getColumnIndex(TASK.TASK_NO));
                contact.task_end_date = cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE));
                if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("0")) {
                    contact.task_status ="待处理";
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("2")) {
                    contact.task_status ="正在审核";
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("3")) {
                    contact.task_status ="审核通过";
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("4")) {
                    contact.task_status ="审核失败";
                }
                else  if(cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)).equals("5")) {
                    contact.task_status ="任务失效";
                }

                contact.task_com_no = cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO));
                contact.task_iner_acc = cursor.getString(cursor.getColumnIndex(TASK.TASK_INER_ACC));
                eventList.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventList;
    }

    //根据搜索内容查询该信贷员任务状态为3的数据
    public List<Contact> getSreachManageCache(String info ,String userAccount, int ln_task_status){
        //获取全部eventlist数据
        Log.i(TAG,"ArrayList<HashMap<String, String>> getEventList()");
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                TASK.TASK_COM_NAME+","+
                TASK.TASK_NO+","+
                TASK.TASK_END_DATE+","+
                TASK.TASK_STATUS+","+
                TASK.TASK_COM_NO+","+
                TASK.TASK_INER_ACC+

                "  FROM "+TASK.TABLE
                +" WHERE "+ TASK.TASK_INER_ACC
                +"= '"+userAccount+"'"
                +"and "+ TASK.TASK_STATUS +" = '"+ln_task_status+"'"+
                " and "+TASK.TASK_COM_NAME+ " LIKE '%" + info + "%'";
        Log.i(TAG,ln_task_status+"查询语句为："+selectQuery);

        List<Contact> eventList=new ArrayList<Contact>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.i(TAG,selectQuery);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.task_com_name = cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME));
                contact.task_no = cursor.getString(cursor.getColumnIndex(TASK.TASK_NO));
                contact.task_end_date = cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE));
                contact.task_status = cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS));
                contact.task_com_no = cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO));
                contact.task_iner_acc = cursor.getString(cursor.getColumnIndex(TASK.TASK_INER_ACC));
                eventList.add(contact);

                eventList.add(contact);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventList;
    }
}
