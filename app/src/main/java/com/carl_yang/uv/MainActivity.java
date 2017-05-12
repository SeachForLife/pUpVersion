package com.carl_yang.uv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carl_yang.uplib.UpVersions;

public class MainActivity extends AppCompatActivity {

    private UpVersions upVersions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upVersions=new UpVersions()
                .setTitle("提示")
                .setContent("有审计")
                .setDownloadUrl("http://download.cntv.cn/app/cntv/cbox_androidguanwang_v6.1.70.apk")
                .downAndUpApp(MainActivity.this);
    }
}
