package com.carl_yang.uplib;

import android.app.Activity;

import java.io.Serializable;

/**
 * Created by carl_yang on 2017/5/11.
 */

public class UpVersions implements Serializable {

    String downloadUrl;
    String title;
    String content;

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

    public UpVersions downAndUpApp(Activity ac){
        new UpdateAppManager(ac,title,content,downloadUrl);
        return this;
    }
}
