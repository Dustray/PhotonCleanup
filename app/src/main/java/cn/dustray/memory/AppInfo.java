package cn.dustray.memory;

import android.graphics.drawable.Drawable;

public class AppInfo {
    //应用名称
    private String appName;
    //应用包名
    private String packageName;
    //版本名称
    private String versionName;
    //版本号
    private int versionCode;
    //应用图标
    private Drawable appIcon;

    private double memorySize;

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {

        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public double getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(double memorySize) {
        this.memorySize = memorySize;
    }
}
