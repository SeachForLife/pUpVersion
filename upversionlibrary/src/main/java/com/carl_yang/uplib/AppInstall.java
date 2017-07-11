package com.carl_yang.uplib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

/**
 * Created by carl_yang on 2017/5/11.
 */

public class AppInstall {

    private AppInstall() {
        throw new Error("Do not need instantiate!");
    }

    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            uri= FileProvider.getUriForFile(context,context.getPackageName()+".versionProvider",file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else{
            uri=Uri.fromFile(file);
        }
        intent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
