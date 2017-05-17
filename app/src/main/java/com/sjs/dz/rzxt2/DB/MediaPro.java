package com.sjs.dz.rzxt2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SJS on 2017/2/28.
 */

public class MediaPro {
    private  String  TAG="TaskPro";
    private DBHelper dbHelper;

    public MediaPro(Context context){
        dbHelper =new DBHelper(context);
    }

    public int insert(MEDIA media){
        //打开连接，写入数据
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
        values.put(MEDIA.MT_NO,media.mt_no);
        //模板名称
        values.put(MEDIA.MT_NAME,media.mt_name);
        //模板项目信息
        values.put(MEDIA.MT_ITEM_INFO,media.mt_item_info);
        //模板上传照片数量
        values.put(MEDIA.MT_D_DESC,media.mt_d_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_7_DESC,"7@0.jpg");
        //模板上传照片描述
        values.put(MEDIA.MT_U_8_DESC,"8@0.jpg");
        //模板上传照片描述
        values.put(MEDIA.MT_U_9_DESC,"9@0.jpg");


        //模板备注
        values.put(MEDIA.MT_IS_NOTE,media.mt_is_note);
        //模板状态
        values.put(MEDIA.MT_STATUS,media.mt_status);
        long media_Id=db.insert(MEDIA.TABLE,null,values);
        db.close();
        Log.i(TAG,"media_Id="+media_Id);
        return (int)media_Id;
    }


    public void delete(int media_no){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(MEDIA.TABLE,MEDIA.MT_NO+"=?", new String[]{String.valueOf(media_no)});
        db.close();
    }
    public void update(MEDIA media){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
        values.put(MEDIA.MT_NO,media.mt_no);
        //模板名称
        values.put(MEDIA.MT_NAME,media.mt_name);
        //模板项目信息
        values.put(MEDIA.MT_ITEM_INFO,media.mt_item_info);
        //模板上传照片数量
        values.put(MEDIA.MT_D_DESC,media.mt_d_desc);
//        //模板上传照片描述
//        values.put(MEDIA.MT_U_7_DESC,media.mt_u_7_desc);
//        //模板上传照片描述
//        values.put(MEDIA.MT_U_8_DESC,media.mt_u_8_desc);
//        //模板上传照片描述
//        values.put(MEDIA.MT_U_9_DESC,media.mt_u_9_desc);

        //模板备注
        values.put(MEDIA.MT_IS_NOTE,media.mt_is_note);


        //模板状态
        values.put(MEDIA.MT_STATUS,media.mt_status);

        db.update(MEDIA.TABLE,values,MEDIA.MT_NO+"=?",new String[] { String.valueOf(media.mt_no) });
        db.close();

    }

    public void addMediaGetDesc(MEDIA media){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
        values.put(MEDIA.MT_NO,media.mt_no);
        //模板名称
        values.put(MEDIA.MT_NAME,media.mt_name);
        //模板项目信息
        values.put(MEDIA.MT_ITEM_INFO,media.mt_item_info);
        //模板上传照片数量
        values.put(MEDIA.MT_D_DESC,media.mt_d_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_7_DESC,media.mt_u_7_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_8_DESC,media.mt_u_8_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_9_DESC,media.mt_u_9_desc);

        //模板备注
        values.put(MEDIA.MT_IS_NOTE,media.mt_is_note);


        //模板状态
        values.put(MEDIA.MT_STATUS,media.mt_status);

        db.update(MEDIA.TABLE,values,MEDIA.MT_NO+"=?",new String[] { String.valueOf(media.mt_no) });
        db.close();

    }
//    public void addMediaGetDesc(String mt_no,String mPhotoname,String type){
//        Log.i(TAG,"addMediaGetDesc………………………………………………………………");
//        SQLiteDatabase db= dbHelper.getWritableDatabase();
//        ContentValues values=new ContentValues();
//        //记录主键
//        values.put(MEDIA.MT_NO,mt_no);
//        if(type.equals("7")){
//            values.put(MEDIA.MT_U_7_DESC,seledesc(mt_no,type)+"%"+type+"@"+mPhotoname);
////            mMedialist.mt_u_7_desc=mMedialist.mt_u_7_desc;
////            Log.i(TAG,"mt_u_7_desc="+desc);
//            }
//        else     if(type.equals("8")){
//            values.put(MEDIA.MT_U_8_DESC,seledesc(mt_no,type)+"%"+type+"@"+mPhotoname);
//            Log.i(TAG,"mt_u_8_desc="+seledesc(mt_no,type)+"%"+type+"@"+mPhotoname);
//        }
//        else     if(type.equals("9")){
//            values.put(MEDIA.MT_U_9_DESC,seledesc(mt_no,type)+"%"+type+"@"+mPhotoname);
//            Log.i(TAG,"mt_u_9_desc="+seledesc(mt_no,type)+"%"+type+"@"+mPhotoname);
//        }
////        模板备注
//        values.put(MEDIA.MT_IS_NOTE,media.mt_is_note);
//
//
//        //模板状态
//        values.put(MEDIA.MT_STATUS,media.mt_status);
//
//        db.update(MEDIA.TABLE,values,MEDIA.MT_NO+"=?",new String[] { mt_no});
//        db.close();
//    }

    public String UpdateStatus(MEDIA media){
        Log.i(TAG,"UpdateStatus………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
        values.put(MEDIA.MT_NO,media.mt_no);
        //模板名称
        values.put(MEDIA.MT_NAME,media.mt_name);
        //模板项目信息
        values.put(MEDIA.MT_ITEM_INFO,media.mt_item_info);
        //模板上传照片数量
        values.put(MEDIA.MT_D_DESC,media.mt_d_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_7_DESC,media.mt_u_7_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_8_DESC,media.mt_u_8_desc);
        //模板上传照片描述
        values.put(MEDIA.MT_U_9_DESC,media.mt_u_9_desc);
//        //模板上传照片数量
//        values.put(MEDIA.MT_U_IM_NUM,media.mt_u_im_num);
//        //模板上传照片描述
//        values.put(MEDIA.MT_U_IM_DESC,media.mt_u_im_desc);
//
//
//        //模板上传word数量
//        values.put(MEDIA.MT_U_W_NUM,media.mt_u_w_num);
//        //模板上传word描述
//        values.put(MEDIA.MT_U_W_DESC,media.mt_u_w_desc);
//        //模板上传pdf数量
//        values.put(MEDIA.MT_U_P_NUM,media.mt_u_p_num);
//        //模板上传pdf描述
//        values.put(MEDIA.MT_U_P_DESC,media.mt_u_p_desc);
//        //模板上传excel数量
//        values.put(MEDIA.MT_U_E_NUM,media.mt_u_e_num);
//
//
//        //模板上传excel描述
//        values.put(MEDIA.MT_U_E_DESC,media.mt_u_e_desc);
//        //模板下载照片数量
//        values.put(MEDIA.MT_D_IM_NUM,media.mt_d_im_num);
//        //模板上下载传照片描述
//        values.put(MEDIA.MT_D_IM_DESC,media.mt_d_im_desc);
//        //模板下载word数量
//        values.put(MEDIA.MT_D_W_NUM,media.mt_d_w_num);
//        //模板下载word描述
//        values.put(MEDIA.MT_D_W_DESC,media.mt_d_w_desc);
//
//
//        //模板下载pdf数量
//        values.put(MEDIA.MT_D_P_NUM,media.mt_d_p_num);
//        //模板下载pdf描述
//        values.put(MEDIA.MT_D_P_DESC,media.mt_d_p_desc);
//        //模板下载excel数量
//        values.put(MEDIA.MT_D_E_NUM,media.mt_d_e_num);
//        //模板下载excel描述
//        values.put(MEDIA.MT_D_E_DESC,media.mt_d_e_desc);
        //模板备注
        values.put(MEDIA.MT_IS_NOTE,media.mt_is_note);


        //模板状态
        values.put(MEDIA.MT_STATUS,media.mt_status);

        db.update(MEDIA.TABLE,values,MEDIA.MT_NO+"=?",new String[] { String.valueOf(media.mt_no) });
        db.close();
         return media.mt_status;


    }
    //查询该no编号该type类型下的描述信息
    public String seledesc(String no,String type){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String desc = "";

        String selectQuery="SELECT "
                +MEDIA.MT_NO+","
                +MEDIA.MT_U_7_DESC+","
                +MEDIA.MT_U_7_DESC+","
                +MEDIA.MT_U_7_DESC+
                " FROM "+MEDIA.TABLE
                + " WHERE " +
                MEDIA.MT_NO + "=?";
        int iCount=0;
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(no)});
        if(cursor.moveToFirst()){
            do{
                if(type.equals("7")){
                    desc=cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_7_DESC));
                }
                else    if(type.equals("8")){
                    desc=cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_8_DESC));
                }
                else    if(type.equals("9")){
                    desc=cursor.getString(cursor.getColumnIndex(MEDIA.MT_U_9_DESC));
                }




            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return desc;
    }


    //查询MT表id
    public boolean getID(String NO){
        boolean rel = false;

        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                MEDIA.MT_NO+

                " FROM "+MEDIA.TABLE +
                "  WHERE " + MEDIA.MT_NO + " = '" + NO + "'"+" LIMIT 1";


        System.out.println(selectQuery);
        ArrayList<HashMap<String,String>> personalList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            rel=true;

        }
        Log.i(TAG,"查询结果：rel="+rel);
        cursor.close();
        db.close();
        return rel;
    }
//    //查询可显示goods表
//    public ArrayList<HashMap<String, String>> showGoodsList(){
//        boolean rel = false;
//
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//        String selectQuery="SELECT "
//                +Goods.GoodsId+","
//                +Goods.GoodsNum+","
//                +Goods.GoodsName+","
//                +Goods.GoodsPrice+","
//                +Goods.GooodsInfo+","
//
//                +Goods.GoodsImage+","
//                +Goods.GoodsType+","
//                +Goods.GoodsStatus+","
//                +Goods.GoodsNumber+","
//                +Goods.GoodsCost+","
//
//                +Goods.GoodsTemperature+","
//                +Goods.GoodsLight+","
//                +Goods.GoodsLife+
//                " FROM "+Goods.TABLE +
//                "  WHERE " + Goods.GoodsStatus + " = '" + 1 + "'";
//
//
//        Log.i(TAG,selectQuery);
//        ArrayList<HashMap<String,String>> showGoodsList=new ArrayList<HashMap<String, String>>();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String,String> goods=new HashMap<String,String>();
//                goods.put("goodsId",cursor.getString(cursor.getColumnIndex(Goods.GoodsId)));
//                goods.put("goodsNum", cursor.getString(cursor.getColumnIndex(Goods.GoodsNum)));
//                goods.put("goodsName", cursor.getString(cursor.getColumnIndex(Goods.GoodsName)));
//                goods.put("goodsPrice", cursor.getString(cursor.getColumnIndex(Goods.GoodsPrice)));
//                goods.put("gooodsInfo", cursor.getString(cursor.getColumnIndex(Goods.GooodsInfo)));
//
//                goods.put("goodsImage", cursor.getString(cursor.getColumnIndex(Goods.GoodsImage)));
//                goods.put("goodsType", cursor.getString(cursor.getColumnIndex(Goods.GoodsType)));
//                goods.put("goodsStatus", cursor.getString(cursor.getColumnIndex(Goods.GoodsStatus)));
//                goods.put("goodsNumber",  cursor.getString(cursor.getColumnIndex(Goods.GoodsNumber)));
//                goods.put("goodsCost", cursor.getString(cursor.getColumnIndex(Goods.GoodsCost)));
//
//                goods.put("goodsTemperature", cursor.getString(cursor.getColumnIndex(Goods.GoodsTemperature)));
//                goods.put("goodsLight",  cursor.getString(cursor.getColumnIndex(Goods.GoodsLight)));
//                goods.put("goodsLife", cursor.getString(cursor.getColumnIndex(Goods.GoodsLife)));
//
//                showGoodsList.add(goods);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return showGoodsList;
//
//    }
//    //查询可管理的goods表
//    public ArrayList<HashMap<String, String>> showGoodsManagerList(){
//        boolean rel = false;
//
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//        String selectQuery="SELECT "
//                +Goods.GoodsId+","
//                +Goods.GoodsNum+","
//                +Goods.GoodsName+","
//                +Goods.GoodsPrice+","
//                +Goods.GooodsInfo+","
//
//                +Goods.GoodsImage+","
//                +Goods.GoodsType+","
//                +Goods.GoodsStatus+","
//                +Goods.GoodsNumber+","
//                +Goods.GoodsCost+","
//
//                +Goods.GoodsTemperature+","
//                +Goods.GoodsLight+","
//                +Goods.GoodsLife+
//                " FROM "+Goods.TABLE ;
//
//
//        Log.i(TAG,selectQuery);
//        ArrayList<HashMap<String,String>> showGoodsList=new ArrayList<HashMap<String, String>>();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String,String> goods=new HashMap<String,String>();
//                goods.put("goodsId",cursor.getString(cursor.getColumnIndex(Goods.GoodsId)));
//                goods.put("goodsNum", cursor.getString(cursor.getColumnIndex(Goods.GoodsNum)));
//                goods.put("goodsName", cursor.getString(cursor.getColumnIndex(Goods.GoodsName)));
//                goods.put("goodsPrice", cursor.getString(cursor.getColumnIndex(Goods.GoodsPrice)));
//                goods.put("gooodsInfo", cursor.getString(cursor.getColumnIndex(Goods.GooodsInfo)));
//
//                goods.put("goodsImage", cursor.getString(cursor.getColumnIndex(Goods.GoodsImage)));
//                goods.put("goodsType", cursor.getString(cursor.getColumnIndex(Goods.GoodsType)));
//                goods.put("goodsStatus", cursor.getString(cursor.getColumnIndex(Goods.GoodsStatus)));
//                goods.put("goodsNumber",  cursor.getString(cursor.getColumnIndex(Goods.GoodsNumber)));
//                goods.put("goodsCost", cursor.getString(cursor.getColumnIndex(Goods.GoodsCost)));
//
//                goods.put("goodsTemperature", cursor.getString(cursor.getColumnIndex(Goods.GoodsTemperature)));
//                goods.put("goodsLight",  cursor.getString(cursor.getColumnIndex(Goods.GoodsLight)));
//                goods.put("goodsLife", cursor.getString(cursor.getColumnIndex(Goods.GoodsLife)));
//
//                showGoodsList.add(goods);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return showGoodsList;
//
//    }


}
