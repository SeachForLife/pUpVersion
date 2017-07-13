# pUpVersion
自动升级的封装，可以直接调用

# 运行效果
![image](https://github.com/SeachForLife/pUpVersion/raw/master/ScreenGif/pUpVersion.gif)</br>

# Android Studio 引用库示例
### gradle中添加:
    第一步：
	    allprojects {
		    repositories {
			    ...
			    maven { url 'https://jitpack.io' }
		    }
	    }
    第二步:
 	    dependencies {
		    compile 'com.github.SeachForLife:pUpVersion:1.2'
	    }


### 示例
	加入动态权限
	注册provider
	        upVersions=new UpVersions()
                .getInstance()
                .setTitle("提示")
                .setContent("有新版本需要更新！")
                .setDownloadUrl("http://download.cntv.cn/app/cntv/cbox_androidguanwang_v6.1.70.apk")
                .downAndUpApp(MainActivity.this);
