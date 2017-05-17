package com.sjs.dz.rzxt2.DB;

/**
 * Created by SJS on 2017/3/14.
 */

public class test {
    //        TASK task = new TASK();
//        task.task_no=task_no;
//        task.task_type =  task_info_list.get(0).get("task_type");
//        task.task_status =  task_info_list.get(0).get("task_status");
//        task.task_is_early_file = task_info_list.get(0).get("task_is_early_file");
//
//
//        //检查机构代码
//        task.task_check_org_no = task_info_list.get(0).get("task_check_org_no");
//        //检查机构名称
//        task.task_check_org_name =  task_info_list.get(0).get("task_check_org_name");
//        //检查类型(首次检查、定期检查、不定期检查)
//        task.task_check_type =  task_info_list.get(0).get("task_check_type");
//        //检查选项(按合同检查)
//        task.task_check_option = task_info_list.get(0).get("ln_check_option");
//        //任务检查人账号
//        task.task_iner_acc =  task_info_list.get(0).get("task_iner_acc");
//
//
//        //任务检查人姓名
//        task.task_iner_name =  task_info_list.get(0).get("task_iner_name");
//        //任务开始日期
//        task.task_start_date = task_info_list.get(0).get("task_start_date");
//        //任务截止日期
//        task.task_end_date =  task_info_list.get(0).get("task_end_date");
//        //任务完成日期
//        task.task_finish_date =  task_info_list.get(0).get("task_finish_date");
//        //审核人账号
//        task.task_audit_acc =  task_info_list.get(0).get("task_audit_acc");
//
//
//        //审核人姓名
//        task.task_audit_name =  task_info_list.get(0).get("task_audit_name");
//        //申请公司编号
//        task.task_com_no =  task_info_list.get(0).get("task_com_no");
//        //申请公司名称
//        task.task_com_name =  task_info_list.get(0).get("task_com_name");
//        //申请合同编号
//        task.task_con_no =  task_info_list.get(0).get("task_con_no");
//        //申请项目编号
//        task.task_item_no =  task_info_list.get(0).get("task_item_no");
//
//
//        //申请产品编号
//        task.task_prdt_no =  task_info_list.get(0).get("task_prdt_no");
//        //申请产品名称
//        task.task_prdt_name =  task_info_list.get(0).get("task_prdt_name");
//        //申请生产类型
//        task.task_prdt_type =  task_info_list.get(0).get("task_prdt_type");
//        //申请认证范围
//        task.task_rz_scope =  task_info_list.get(0).get("task_rz_scope");
//        //申请认证类型
//        task.task_rz_type =  task_info_list.get(0).get("task_rz_type");
//
//        //申请公司地址
//        task.task_com_addr =  task_info_list.get(0).get("task_com_addr");
//        //申请公司电话
//        task.task_com_tel =  task_info_list.get(0).get("task_com_tel");
//        // 申请公司邮编
//        task.task_com_post_code =  task_info_list.get(0).get("task_com_post_code");
//        //申请公司邮箱
//        task.task_com_email =  task_info_list.get(0).get("task_com_email");
//        //申请公司传真
//        task.task_com_fax =  task_info_list.get(0).get("task_com_fax");
//
//
//        //申请公司网络url
//        task.task_web_url =  task_info_list.get(0).get("task_web_url");
//        //公司联系人姓名
//        task.task_com_con_name =  task_info_list.get(0).get("task_com_con_name");
//        //公司联系人电话
//        task.task_com_con_tel =  task_info_list.get(0).get("task_com_con_tel");
//        //公司联系人职位
//        task.task_com_con_ide =  task_info_list.get(0).get("task_com_con_ide");
//        //申请产品介绍
//        task.task_item_info =  task_info_list.get(0).get("task_item_info");
//
//        //信息采集模板ID
//        task.task_mt_id = task_info_list.get(0).get("task_mt_id");

//        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("EDIT_MEMO", com_info_memo.getText().toString());
//        editor.commit();

    //删除旧图片
//                String fileName = resultList.get(0).getPhotoPath();
//                File file = new File(fileName);
//                FileUtils.delFile(file);

//                String value= getmediaList.get(0).get("mt_image_desc");
//                String[] array = value.split("=");
//                for(int i=0;i< array.length;i++ ){
//                    File f = new File(path, i + ".JPG");
//                    //如果同名照片存在则添加到list
//                    if (f.exists()) {
//                        PhotoInfo ptinfo = new PhotoInfo();
//                        ptinfo.setPhotoPath(path+ i + ".JPG");
//                        resultList.add(i,ptinfo);
//                    }
//                    else{
//                        resultList.add(i,null);
//                    }
//                }


//                mPhotoList.addAll(resultList);
//                mChoosePhotoListAdapter.notifyDataSetChanged();
//                initView();
//                Toast.makeText(EventEventActivity.this, "resultList" + resultList, Toast.LENGTH_SHORT).show();
//}
//    //根据状态查询任务
//    public ArrayList<HashMap<String, String>> showStatusList(String status, String userAcc){
//
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//        String selectQuery="";
//        if(Integer.parseInt(status)==0){
//            selectQuery="SELECT "
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
//                    " FROM "+TASK.TABLE
//                    +" WHERE "+ TASK.TASK_INER_ACC
//                    +"= '"+userAcc+"'"
//                    +"and "+ TASK.TASK_STATUS +" = '"+status+"'";
//
//        }
//        else{
//            selectQuery="SELECT "
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
//                    " FROM "+TASK.TABLE
//                    +" WHERE "+ TASK.TASK_INER_ACC
//                    +"= '"+userAcc+"'"
//                    +"and "+ TASK.TASK_STATUS+" >= '"+status+"'" ;
//        }
//        Log.i(TAG,Integer.parseInt(status)+selectQuery);
//        ArrayList<HashMap<String,String>> showTaskList=new ArrayList<HashMap<String, String>>();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String,String> task=new HashMap<String,String>();
//
//                task.put("task_id",cursor.getString(cursor.getColumnIndex(TASK.TASK_ID)));
//                task.put("task_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_NO)));
//                /***
//                 * 0：待处理
//                 1：待上传
//                 2：已上传，正在审核
//                 3：审核通过
//                 4：审核失败
//                 5：任务失效
//                 */
//                task.put("task_status", cursor.getString(cursor.getColumnIndex(TASK.TASK_STATUS)));
//                task.put("task_com_name", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NAME)));
//                task.put("task_com_no", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_NO)));
//
//                task.put("task_com_addr", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_ADDR)));
//                task.put("task_com_tel", cursor.getString(cursor.getColumnIndex(TASK.TASK_COM_TEL)));
//                task.put("task_end_date", cursor.getString(cursor.getColumnIndex(TASK.TASK_END_DATE)));
//
//
//                showTaskList.add(task);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return showTaskList;
//
    }