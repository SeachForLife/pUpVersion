package com.carl_yang.uv;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carl_yang.permission.PermissionsChecker;
import com.carl_yang.uplib.UpVersions;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0; // 请求码
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private PermissionsChecker mPermissionsChecker; // 动态权限检测器

    private UpVersions upVersions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPermissionsChecker=new PermissionsChecker(this);

        upVersions=new UpVersions()
                .getInstance()
                .setTitle("提示")
                .setContent("有新版本需要更新！")
                .setDownloadUrl("http://download.cntv.cn/app/cntv/cbox_androidguanwang_v6.1.70.apk")
                .downAndUpApp(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
}
