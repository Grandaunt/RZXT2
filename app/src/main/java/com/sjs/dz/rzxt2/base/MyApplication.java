package com.sjs.dz.rzxt2.base;

import android.app.Application;
import android.graphics.Color;

import com.sjs.dz.rzxt2.loader.XUtilsImageLoader;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;


/**
 * 拍照MyApplication
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private static FunctionConfig mFunctionConfig;
    private static FunctionConfig.Builder mFunctionConfigBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        instance = this;
//      initImageLoader(getApplicationContext());
        initGalleryFinal();
    }

    private void initGalleryFinal() {
        mFunctionConfigBuilder = new FunctionConfig.Builder();
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                //标题栏背景颜色

                .setTitleBarBgColor(Color.rgb(0x35, 0x98, 0xdb))
                //标题栏文本字体颜色
                .setTitleBarTextColor(Color.rgb(0xff,0xff,0xff))
                //设置Floating按钮Nornal状态颜色
                .setFabNornalColor(Color.rgb(0xDD, 0xDD, 0xDD))
                //设置Floating按钮Pressed状态颜色
                .setFabPressedColor(Color.rgb(0xBB, 0xBB, 0xBB))
                //选择框未选颜色
                .setCheckSelectedColor(Color.rgb(0xEE, 0xEE, 0xEE))
                //设置裁剪控制点和裁剪框颜色
                .setCropControlColor(Color.rgb(0xEE, 0xEE, 0xEE))
                .build();
        // mFunctionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        //配置功能
        mFunctionConfig = mFunctionConfigBuilder
                .setEnableEdit(false)//开启编辑功能
                .setEnableCamera(false)//开启相机功能
                .setEnableCrop(false)//开启裁剪功能
                .setEnableRotate(false)//开启旋转功能
                .setCropSquare(true)//裁剪正方形
                .setEnablePreview(true)//是否开启预览功能
                .setRotateReplaceSource(true)//配置选择图片时是否替换原始图片，默认不替换
                .build();

        //配置imageloader
        ImageLoader imageloader = new XUtilsImageLoader();

        CoreConfig coreConfig = new CoreConfig.Builder(getInstance(), imageloader, theme)
               // .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(mFunctionConfig)
               .build();
        GalleryFinal.init(coreConfig);
    }


    public static MyApplication getInstance(){
        return instance;
    }
    public static FunctionConfig getFunctionConfig() {
        return mFunctionConfig;
    }
    public static FunctionConfig.Builder getFunctionConfigBuilder() {
        return mFunctionConfigBuilder;
    }


}
