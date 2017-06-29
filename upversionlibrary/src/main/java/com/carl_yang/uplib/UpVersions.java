package com.carl_yang.uplib;

import android.app.Activity;

import com.lzy.okgo.OkGo;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * Created by carl_yang on 2017/5/11.
 */

public class UpVersions implements Serializable {

    private static UpVersions mInstance;
    //更新的APP地址
    String downloadUrl;
    //提示升级框的标题
    String title;
    //提示框的内容
    String content;

//    boolean dialogShow=true;//是否使用dialog显示升级进度条，默认使用
//    boolean notiShow=true;//是否使用通知栏进行升级进度条展示，默认使用

    public static UpVersions getInstance(){
        if(mInstance==null){
            synchronized (UpVersions.class){
                if (mInstance == null){
                    mInstance = new UpVersions();
                }
            }
        }
        return mInstance;
    }

    public String getContent() {
        return content;
    }

    public UpVersions setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UpVersions setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public UpVersions setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public UpVersions downAndUpApp(Activity ac) {
        //初始化OKGO
        OkGo.init(ac.getApplication());
        //打开OKGO的日志
        OkGo.getInstance()
                .debug("okGo", Level.INFO,true);
        new UpdateAppManager(ac,mInstance);
        return this;
    }
}
