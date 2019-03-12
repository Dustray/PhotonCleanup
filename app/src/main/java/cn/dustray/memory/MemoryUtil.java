package cn.dustray.memory;

import android.app.Activity;
import android.app.ActivityManager;

import java.util.List;

public class MemoryUtil {
    private Memory m;
    private Activity activity;

    public MemoryUtil(Activity activity) {
        this.m = new Memory();
        this.activity = activity;
    }

    public MemoryEntity getCurrentMeminfo() {
        return m.getCurrentMeminfo(activity);
    }


    public void clearMemory() {
        m.clearMemory(activity);
    }

    public List<AppInfo> getInstalledAppList() {
        return m.getAppList(activity);
    }

}
