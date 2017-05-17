package com.sjs.dz.rzxt2.DB;

/**
 * Created by Administrator on 2016/6/30.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static final int DATABASE_VERSION=1;

    //数据库名称
    private static final String DATABASE_NAME="DB_SJS_RZ.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_EVENT="CREATE TABLE "+ TASK.TABLE+"("
                +TASK.TASK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +TASK.TASK_NO+" TEXT, "
                +TASK.TASK_TYPE+" TEXT, "
                +TASK.TASK_STATUS+" TEXT, "
                +TASK.TASK_IS_EARLY_FILE+" TEXT, "

                +TASK.TASK_CHECK_ORG_NO+" TEXT, "
                +TASK.TASK_CHECK_ORG_NAME+" TEXT, "
                +TASK.TASK_CHECK_TYPE+" TEXT, "
                +TASK.TASK_CHECK_OPTION+" TEXT, "
                +TASK.TASK_INER_ACC+" TEXT, "

                +TASK.TASK_INER_NAME+" TEXT, "
                +TASK.TASK_START_DATE+" TEXT, "
                +TASK.TASK_END_DATE+" TEXT, "
                +TASK.TASK_FINISH_DATE+" TEXT, "
                +TASK.TASK_AUDIT_ACC+" TEXT, "

                +TASK.TASK_AUDIT_NAME+" TEXT, "
                +TASK.TASK_COM_NO+" TEXT, "
                +TASK.TASK_COM_NAME+" TEXT, "
                +TASK.TASK_CON_NO+" TEXT, "
                +TASK.TASK_ITEN_NO+" TEXT, "

                +TASK.TASK_PRDT_NO+" TEXT, "
                +TASK.TASK_PRDT_NAME+" TEXT, "
                +TASK.TASK_PRDT_TYPE+" TEXT, "
                +TASK.TASK_RZ_SCOPE+" TEXT, "
                +TASK.TASK_RZ_TYPE+" TEXT, "

                +TASK.TASK_COM_ADDR+" TEXT, "
                +TASK.TASK_COM_TEL+" TEXT, "
                +TASK.TASK_COM_POST_CODE+" TEXT, "
                +TASK.TASK_COM_EMAIL+" TEXT, "
                +TASK.TASK_COM_FAX+" TEXT, "

                +TASK.TASK_WEB_URL+" TEXT, "
                +TASK.TASK_COM_CON_NAME+" TEXT, "
                +TASK.TASK_COM_CON_TEL+" TEXT, "
                +TASK.TASK_COM_CON_IDE+" TEXT, "
                +TASK.TASK_ITEM_INFO+" TEXT, "

                +TASK.TASK_MT_ID+" TEXT, "
                +TASK.TASK_NOTE+" TEXT, "
                +TASK.APPLY_ID+ " TEXT)";
        db.execSQL(CREATE_TABLE_EVENT);

        Log.i("DBHelper","EVENT表创建");



        String CREATE_TABLE_MEDIA="CREATE TABLE "+ MEDIA.TABLE+"("
                +MEDIA.MT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +MEDIA.MT_NO+" TEXT,"
                +MEDIA.MT_NAME+" TEXT, "
                +MEDIA.MT_ITEM_INFO+" TEXT, "
                +MEDIA.MT_U_7_DESC+" TEXT, "
                +MEDIA.MT_U_8_DESC+" TEXT, "
                +MEDIA.MT_U_9_DESC+" TEXT, "
                +MEDIA.MT_D_DESC+" TEXT, "
                +MEDIA.MT_IS_NOTE+" TEXT, "
                +MEDIA.MT_STATUS+" TEXT)";
        db.execSQL(CREATE_TABLE_MEDIA);
        Log.i("DBHelper","Media表创建");




    }
//数据更新时重新考虑
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
//        db.execSQL("DROP TABLE IF EXISTS "+ Event.TABLE);
//        db.execSQL("DROP TABLE IF EXISTS "+ User.TABLE);

        //再次创建表
        onCreate(db);
    }
}