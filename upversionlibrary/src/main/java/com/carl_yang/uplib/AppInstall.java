package com.carl_yang.uplib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
        Uri uri=Uri.fromFile(file);
        intent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
