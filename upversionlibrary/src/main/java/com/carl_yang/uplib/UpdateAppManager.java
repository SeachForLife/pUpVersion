package com.carl_yang.uplib;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by carl_yang on 2017/5/12.
 */

public class UpdateAppManager {

    private Context context;

    private AlertDialog dialog;
    public AlertDialog loadingDialog;
    AlertDialog failDialog;
    private String title;
    private String content;
    private String downloadUrl;
    View loadingView;

    public UpdateAppManager(Context context,String titile,String content,String downloadUrl) {
        System.out.println("初始化UPdateManager");
        this.context=context;
        this.title=titile;
        this.content=content;
        this.downloadUrl=downloadUrl;
        showNoticeDialog();
    }


    /**
     * 创建提示框，提示是否需要升级
     */
    private void showNoticeDialog() {
        dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(content).setPositiveButton(
                "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadFile(downloadUrl);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void downloadFile(String url) {
            OkGo.get(url).execute(new FileCallback(FileHelper.getDownloadApkCachePath(),  "upversionLib.apk") {
                @Override
                public void onBefore(BaseRequest request) {
                    super.onBefore(request);
                }

                @Override
                public void onSuccess(File file, Call call, Response response) {
                    if (loadingDialog != null)
                        loadingDialog.dismiss();
                    AppInstall.installApk(context, file);
                    dialog.cancel();
                }

                @Override
                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                    int currentProgress = (int) (progress * 100);
                    showLoadingDialog(currentProgress);
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    showFailDialog();
                }
            });
    }


    /**
     * 创建一个加载进度条提示
     * @param currentProgress
     */
    public void showLoadingDialog(int currentProgress) {
        if (loadingDialog == null) {
            loadingView = LayoutInflater.from(context).inflate(R.layout.downloading_layout, null);
            loadingDialog = new AlertDialog.Builder(context).setTitle("正在下载中...").setView(loadingView).create();
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    loadingDialog.cancel();
                }
            });
        }
        ProgressBar pb = (ProgressBar) loadingView.findViewById(R.id.pb);
        TextView tvProgress = (TextView) loadingView.findViewById(R.id.tv_progress);
        tvProgress.setText(String.format("%d%%/100%%", currentProgress));
        pb.setProgress(currentProgress);
        loadingDialog.show();
    }

    /**
     * 展示下载失败提示框
     */
    public void showFailDialog() {
        if (failDialog == null) {
            failDialog = new AlertDialog.Builder(context)
                    .setMessage("下载失败是否重试?")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    downloadFile(downloadUrl);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    failDialog.cancel();
                }
            }).create();
            failDialog.setCanceledOnTouchOutside(false);
            failDialog.setCancelable(false);
        }
        failDialog.show();
    }
}
