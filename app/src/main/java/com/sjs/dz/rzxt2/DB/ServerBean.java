package com.sjs.dz.rzxt2.DB;

import java.util.List;

/**
 * Created by SJS on 2017/2/27.
 */

public class ServerBean {
    //0:成功，1：失败
    private int error;
    private String msg;
    private User user;
    private List<TaskBean> taskList;

    public List<MediaBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<MediaBean> fileList) {
        this.fileList = fileList;
    }

    private List<MediaBean> fileList;


    public List<TaskBean> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskBean> taskList) {
        this.taskList = taskList;
    }



    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }






    public static class  User{
        //请求结果
        private String AUTH_RESULT;
        //用户口令
        private  String AUTH_TOKEN;
        //用户登陆账号
        private String USER_ACCOUNT;
        //用户姓名
        private String USER_NAME;
        //用户身份
        private String USER_IDE;


        //用户电话
        private String USER_TEL;
        //用户所属部门
        private String USER_DEPT_NAME;
        //用户所属部门机构码
        private String USER_DEPT_ORG_CODE;


        public String getAUTH_RESULT() {
            return AUTH_RESULT;
        }

        public void setAUTH_RESULT(String AUTH_RESULT) {
            this.AUTH_RESULT = AUTH_RESULT;
        }

        public String getAUTH_TOKEN() {
            return AUTH_TOKEN;
        }

        public void setAUTH_TOKEN(String AUTH_TOKEN) {
            this.AUTH_TOKEN = AUTH_TOKEN;
        }

        public String getUSER_ACCOUNT() {
            return USER_ACCOUNT;
        }

        public void setUSER_ACCOUNT(String USER_ACCOUNT) {
            this.USER_ACCOUNT = USER_ACCOUNT;
        }

        public String getUSER_NAME() {
            return USER_NAME;
        }

        public void setUSER_NAME(String USER_NAME) {
            this.USER_NAME = USER_NAME;
        }

        public String getUSER_IDE() {
            return USER_IDE;
        }

        public void setUSER_IDE(String USER_IDE) {
            this.USER_IDE = USER_IDE;
        }

        public String getUSER_TEL() {
            return USER_TEL;
        }

        public void setUSER_TEL(String USER_TEL) {
            this.USER_TEL = USER_TEL;
        }

        public String getUSER_DEPT_NAME() {
            return USER_DEPT_NAME;
        }

        public void setUSER_DEPT_NAME(String USER_DEPT_NAME) {
            this.USER_DEPT_NAME = USER_DEPT_NAME;
        }

        public String getUSER_DEPT_ORG_CODE() {
            return USER_DEPT_ORG_CODE;
        }

        public void setUSER_DEPT_ORG_CODE(String USER_DEPT_ORG_CODE) {
            this.USER_DEPT_ORG_CODE = USER_DEPT_ORG_CODE;
        }

        @Override
        public String toString() {
            return "User{" +
                    "USER_ACCOUNT='" + USER_ACCOUNT + '\'' +
                    ", USER_NAME='" + USER_NAME + '\'' +
                    ", USER_IDE='" + USER_IDE + '\'' +
                    ", USER_TEL='" + USER_TEL + '\'' +
                    '}';
        }
    }

//    public static class TaskList {
//
//
//        private List<TaskBean> tasklist;
//
//        public List<TaskBean> getTasklist() {
//            return tasklist;
//        }
//
//        public void setTasklist(List<TaskBean> tasklist) {
//            this.tasklist = tasklist;
//        }
//    }


    public  class TaskBean{
        //任务编号
        private String TASK_NO;
        //任务类型
        private String TASK_TYPE;

        //任务状态
        private String TASK_STATUS;
        //初期资料是否齐全
        private String TASK_IS_EARLY_FILE;
        //检查机构代码
        private String TASK_CHECK_ORG_NO;
        //检查机构名称
        private String TASK_CHECK_ORG_NAME;
        //检查类型(首次检查、定期检查、不定期检查)
        private String TASK_CHECK_TYPE;


        //检查选项(按合同检查)
        private String TASK_CHECK_OPTION;
        //任务检查人账号
        private String TASK_INER_ACC;
        //任务检查人姓名
        private String TASK_INER_NAME;
        //任务开始日期
        private String TASK_START_DATE;
        //任务截止日期
        private String TASK_END_DATE;



        //任务完成日期
        private String TASK_FINISH_DATE;
        //审核人账号
        private String TASK_AUDIT_ACC;
        //审核人姓名
        private String TASK_AUDIT_NAME;
        //申请公司编号
        private String TASK_COM_NO;
        //申请公司名称
        private String TASK_COM_NAME;


        //申请合同编号
        private String TASK_CON_NO;
//        //申请项目编号
        private String TASK_ITEN_NO;
        //申请产品编号
        private String TASK_PRDT_NO;
        //申请产品名称
        private String TASK_PRDT_NAME;
        //申请生产类型
        private String TASK_PRDT_TYPE;
//
//
        //申请认证范围
        private String TASK_RZ_SCOPE;
        //申请认证类型
        private String TASK_RZ_TYPE;
        //申请公司地址
        private String TASK_COM_ADDR;
        //申请公司电话
        private String TASK_COM_TEL;



        //        //申请公司邮编
        private String TASK_COM_POST_CODE;


        //申请公司邮箱
        private String TASK_COM_EMAIL;
        //申请公司传真
        private String TASK_COM_FAX;
        //申请公司网络url
        private String TASK_COM_WEB_URL;
        //公司联系人姓名
        private String TASK_COM_CON_NAME;
        //公司联系人电话
        private String TASK_COM_CON_TEL;


        //公司联系人职位
        private String TASK_COM_CON_IDE;
        //申请产品介绍
        private String TASK_ITEM_INFO;
        //信息采集模板ID
        private String TASK_MT_ID;
        //任务备注
        private String TASK_NOTE;
        //申请单号
        private String APPLY_ID;

        public String getAPPLY_ID() {
            return APPLY_ID;
        }

        public void setAPPLY_ID(String APPLY_ID) {
            this.APPLY_ID = APPLY_ID;
        }
        public String getTASK_NO() {
            return TASK_NO;
        }

        public void setTASK_NO(String TASK_NO) {
            this.TASK_NO = TASK_NO;
        }
        public String getTASK_STATUS() {
            return TASK_STATUS;
        }
        public String getTASK_START_DATE() {
            return TASK_START_DATE;
        }

        public void setTASK_START_DATE(String TASK_START_DATE) {
            this.TASK_START_DATE = TASK_START_DATE;
        }
        public void setTASK_STATUS(String TASK_STATUS) {
            this.TASK_STATUS = TASK_STATUS;
        }
        public String getTASK_COM_NO() {
            return TASK_COM_NO;
        }

        public void setTASK_COM_NO(String TASK_COM_NO) {
            this.TASK_COM_NO = TASK_COM_NO;
        }

        public String getTASK_COM_NAME() {
            return TASK_COM_NAME;
        }

        public void setTASK_COM_NAME(String TASK_COM_NAME) {
            this.TASK_COM_NAME = TASK_COM_NAME;
        }

        public String getTASK_CON_NO() {
            return TASK_CON_NO;
        }

        public void setTASK_CON_NO(String TASK_CON_NO) {
            this.TASK_CON_NO = TASK_CON_NO;
        }
        public String getTASK_PRDT_TYPE() {
            return TASK_PRDT_TYPE;
        }

        public void setTASK_PRDT_TYPE(String TASK_PRDT_TYPE) {
            this.TASK_PRDT_TYPE = TASK_PRDT_TYPE;
        }

        public String getTASK_RZ_SCOPE() {
            return TASK_RZ_SCOPE;
        }

        public void setTASK_RZ_SCOPE(String TASK_RZ_SCOPE) {
            this.TASK_RZ_SCOPE = TASK_RZ_SCOPE;
        }

        public String getTASK_RZ_TYPE() {
            return TASK_RZ_TYPE;
        }

        public void setTASK_RZ_TYPE(String TASK_RZ_TYPE) {
            this.TASK_RZ_TYPE = TASK_RZ_TYPE;
        }

        public String getTASK_COM_ADDR() {
            return TASK_COM_ADDR;
        }

        public void setTASK_COM_ADDR(String TASK_COM_ADDR) {
            this.TASK_COM_ADDR = TASK_COM_ADDR;
        }

        public String getTASK_COM_TEL() {
            return TASK_COM_TEL;
        }

        public void setTASK_COM_TEL(String TASK_COM_TEL) {
            this.TASK_COM_TEL = TASK_COM_TEL;
        }

        public String getTASK_INER_ACC() {
            return TASK_INER_ACC;
        }

        public void setTASK_INER_ACC(String TASK_INER_ACC) {
            this.TASK_INER_ACC = TASK_INER_ACC;
        }

        public String getTASK_TYPE() {
            return TASK_TYPE;
        }

        public void setTASK_TYPE(String TASK_TYPE) {
            this.TASK_TYPE = TASK_TYPE;
        }


        public String getTASK_IS_EARLY_FILE() {
            return TASK_IS_EARLY_FILE;
        }

        public void setTASK_IS_EARLY_FILE(String TASK_IS_EARLY_FILE) {
            this.TASK_IS_EARLY_FILE = TASK_IS_EARLY_FILE;
        }

        public String getTASK_CHECK_ORG_NO() {
            return TASK_CHECK_ORG_NO;
        }

        public void setTASK_CHECK_ORG_NO(String TASK_CHECK_ORG_NO) {
            this.TASK_CHECK_ORG_NO = TASK_CHECK_ORG_NO;
        }

        public String getTASK_CHECK_ORG_NAME() {
            return TASK_CHECK_ORG_NAME;
        }

        public void setTASK_CHECK_ORG_NAME(String TASK_CHECK_ORG_NAME) {
            this.TASK_CHECK_ORG_NAME = TASK_CHECK_ORG_NAME;
        }

        public String getTASK_CHECK_TYPE() {
            return TASK_CHECK_TYPE;
        }

        public void setTASK_CHECK_TYPE(String TASK_CHECK_TYPE) {
            this.TASK_CHECK_TYPE = TASK_CHECK_TYPE;
        }

        public String getTASK_CHECK_OPTION() {
            return TASK_CHECK_OPTION;
        }

        public void setTASK_CHECK_OPTION(String TASK_CHECK_OPTION) {
            this.TASK_CHECK_OPTION = TASK_CHECK_OPTION;
        }

        public String getTASK_INER_NAME() {
            return TASK_INER_NAME;
        }

        public void setTASK_INER_NAME(String TASK_INER_NAME) {
            this.TASK_INER_NAME = TASK_INER_NAME;
        }



        public String getTASK_END_DATE() {
            return TASK_END_DATE;
        }

        public void setTASK_END_DATE(String TASK_END_DATE) {
            this.TASK_END_DATE = TASK_END_DATE;
        }

        public String getTASK_FINISH_DATE() {
            return TASK_FINISH_DATE;
        }

        public void setTASK_FINISH_DATE(String TASK_FINISH_DATE) {
            this.TASK_FINISH_DATE = TASK_FINISH_DATE;
        }

        public String getTASK_AUDIT_ACC() {
            return TASK_AUDIT_ACC;
        }

        public void setTASK_AUDIT_ACC(String TASK_AUDIT_ACC) {
            this.TASK_AUDIT_ACC = TASK_AUDIT_ACC;
        }

        public String getTASK_AUDIT_NAME() {
            return TASK_AUDIT_NAME;
        }

        public void setTASK_AUDIT_NAME(String TASK_AUDIT_NAME) {
            this.TASK_AUDIT_NAME = TASK_AUDIT_NAME;
        }


        public String getTASK_ITEN_NO() {
            return TASK_ITEN_NO;
        }

        public void setTASK_ITEN_NO(String TASK_ITEN_NO) {
            this.TASK_ITEN_NO = TASK_ITEN_NO;
        }

        public String getTASK_PRDT_NO() {
            return TASK_PRDT_NO;
        }

        public void setTASK_PRDT_NO(String TASK_PRDT_NO) {
            this.TASK_PRDT_NO = TASK_PRDT_NO;
        }

        public String getTASK_PRDT_NAME() {
            return TASK_PRDT_NAME;
        }

        public void setTASK_PRDT_NAME(String TASK_PRDT_NAME) {
            this.TASK_PRDT_NAME = TASK_PRDT_NAME;
        }


        public String getTASK_COM_POST_CODE() {
            return TASK_COM_POST_CODE;
        }

        public void setTASK_COM_POST_CODE(String TASK_COM_POST_CODE) {
            this.TASK_COM_POST_CODE = TASK_COM_POST_CODE;
        }

        public String getTASK_COM_EMAIL() {
            return TASK_COM_EMAIL;
        }

        public void setTASK_COM_EMAIL(String TASK_COM_EMAIL) {
            this.TASK_COM_EMAIL = TASK_COM_EMAIL;
        }

        public String getTASK_COM_FAX() {
            return TASK_COM_FAX;
        }

        public void setTASK_COM_FAX(String TASK_COM_FAX) {
            this.TASK_COM_FAX = TASK_COM_FAX;
        }

        public String getTASK_COM_WEB_URL() {
            return TASK_COM_WEB_URL;
        }

        public void setTASK_COM_WEB_URL(String TASK_COM_WEB_URL) {
            this.TASK_COM_WEB_URL = TASK_COM_WEB_URL;
        }

        public String getTASK_COM_CON_NAME() {
            return TASK_COM_CON_NAME;
        }

        public void setTASK_COM_CON_NAME(String TASK_COM_CON_NAME) {
            this.TASK_COM_CON_NAME = TASK_COM_CON_NAME;
        }

        public String getTASK_COM_CON_TEL() {
            return TASK_COM_CON_TEL;
        }

        public void setTASK_COM_CON_TEL(String TASK_COM_CON_TEL) {
            this.TASK_COM_CON_TEL = TASK_COM_CON_TEL;
        }

        public String getTASK_COM_CON_IDE() {
            return TASK_COM_CON_IDE;
        }

        public void setTASK_COM_CON_IDE(String TASK_COM_CON_IDE) {
            this.TASK_COM_CON_IDE = TASK_COM_CON_IDE;
        }

        public String getTASK_ITEM_INFO() {
            return TASK_ITEM_INFO;
        }

        public void setTASK_ITEM_INFO(String TASK_ITEM_INFO) {
            this.TASK_ITEM_INFO = TASK_ITEM_INFO;
        }

        public String getTASK_MT_ID() {
            return TASK_MT_ID;
        }

        public void setTASK_MT_ID(String TASK_MT_ID) {
            this.TASK_MT_ID = TASK_MT_ID;
        }

        public String getTASK_NOTE() {
            return TASK_NOTE;
        }

        public void setTASK_NOTE(String TASK_NOTE) {
            this.TASK_NOTE = TASK_NOTE;
        }

        @Override
        public String toString() {
            return "TaskBean{" +
                    "TASK_NO='" + TASK_NO + '\'' +
                    ", TASK_STATUS='" + TASK_STATUS + '\'' +
                    ", TASK_START_DATE='" + TASK_START_DATE + '\'' +
                    ", TASK_COM_NO='" + TASK_COM_NO + '\'' +
                    ", TASK_COM_NAME='" + TASK_COM_NAME + '\'' +
                    ", TASK_CON_NO='" + TASK_CON_NO + '\'' +
                    ", TASK_PRDT_TYPE='" + TASK_PRDT_TYPE + '\'' +
                    ", TASK_RZ_SCOPE='" + TASK_RZ_SCOPE + '\'' +
                    ", TASK_RZ_TYPE='" + TASK_RZ_TYPE + '\'' +
                    ", TASK_COM_ADDR='" + TASK_COM_ADDR + '\'' +
                    ", TASK_COM_TEL='" + TASK_COM_TEL + '\'' +
                    ", APPLY_ID='" + APPLY_ID + '\'' +
                    '}';
        }
    }




    public class MediaBean{
        //记录主键
        private String MT_ID;


        //模板名称
        private String MT_NAME;
        //模板项目信息
        private String MT_ITEM_INFO;



        public String getMT_D_DESC() {
            return MT_D_DESC;
        }

        public void setMT_D_DESC(String MT_D_DESC) {
            this.MT_D_DESC = MT_D_DESC;
        }

//        public String getMT_U_7_DESC() {
//            return MT_U_7_DESC;
//        }
//
//        public void setMT_U_7_DESC(String MT_U_7_DESC) {
//            this.MT_U_7_DESC = MT_U_7_DESC;
//        }
//
//        public String getMT_U_9_DESC() {
//            return MT_U_9_DESC;
//        }
//
//        public void setMT_U_9_DESC(String MT_U_9_DESC) {
//            this.MT_U_9_DESC = MT_U_9_DESC;
//        }
//
//        public String getMT_U_8_DESC() {
//            return MT_U_8_DESC;
//        }
//
//        public void setMT_U_8_DESC(String MT_U_8_DESC) {
//            this.MT_U_8_DESC = MT_U_8_DESC;
//        }

        //模板上传照片数量
//        private String MT_U_7_DESC;
//        //模板上传照片数量
//        private String MT_U_8_DESC;
//        //模板上传照片数量
//        private String MT_U_9_DESC;
        //模板上传照片描述
        private String MT_D_DESC;
        //模板上传word数量

        //模板备注
        private String MT_IS_NOTE;


        //模板状态
        private String MT_STATUS;



        public String getMT_ID() {
            return MT_ID;
        }

        public void setMT_ID(String MT_ID) {
            this.MT_ID = MT_ID;
        }

        public String getMT_NAME() {
            return MT_NAME;
        }

        public void setMT_NAME(String MT_NAME) {
            this.MT_NAME = MT_NAME;
        }

        public String getMT_ITEM_INFO() {
            return MT_ITEM_INFO;
        }

        public void setMT_ITEM_INFO(String MT_ITEM_INFO) {
            this.MT_ITEM_INFO = MT_ITEM_INFO;
        }

        public String getMT_IS_NOTE() {
            return MT_IS_NOTE;
        }

        public void setMT_IS_NOTE(String MT_IS_NOTE) {
            this.MT_IS_NOTE = MT_IS_NOTE;
        }

        public String getMT_STATUS() {
            return MT_STATUS;
        }

        public void setMT_STATUS(String MT_STATUS) {
            this.MT_STATUS = MT_STATUS;
        }

        @Override
        public String toString() {
            return "MediaBean{" +
                    "MT_ID='" + MT_ID + '\'' +
                    ", MT_NAME='" + MT_NAME + '\'' +
                    ", MT_ITEM_INFO='" + MT_ITEM_INFO + '\'' +
//                    ", MT_U_7_DESC='" + MT_U_7_DESC + '\'' +
//                    ", MT_U_8_DESC='" + MT_U_8_DESC + '\'' +
//                    ", MT_U_9_DESC='" + MT_U_9_DESC + '\'' +
                    ", MT_D_DESC='" + MT_D_DESC + '\'' +
                    ", MT_IS_NOTE='" + MT_IS_NOTE + '\'' +
                    ", MT_STATUS='" + MT_STATUS + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ServerBean{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", user=" + user +
                ", taskList=" + taskList +
                ", fileList=" + fileList +
                '}';
    }
}
