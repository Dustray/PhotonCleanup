package cn.dustray.memory;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Memory {
    /**
     * 获取内存信息
     *
     * @param activity
     * @return
     */
    MemoryEntity getCurrentMeminfo(Activity activity) {
        //StringBuffer sb = new StringBuffer();
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        MemoryEntity entity = new MemoryEntity();
        entity.setTotalMemory((mi.totalMem / 1024 / 1024) + " MB");
        entity.setAvailableMemory((mi.availMem / 1024 / 1024) + " MB");
        entity.setMinimumMemoryScale(mi.availMem / mi.totalMem);
        entity.setLowMemory(mi.lowMemory);
        entity.setCanBeCleanMemory(getAllAppMemory(activity) + " MB");
        //sb.append("剩余内存：" + (mi.availMem / 1024 / 1024) + "MB\n");
        //sb.append("总内存： " + (mi.totalMem / 1024 / 1024) + "MB\n");
        //sb.append("内存是否过低：" + mi.lowMemory);
        return entity;
    }

    /**
     * 获取本机所安装app信息
     *
     * @return List<AppInfo> app信息列表
     */
    public List<AppInfo> getAppList(Activity activity) {
        List<AppInfo> appInfoList = new ArrayList<AppInfo>();
        PackageManager packageManager = activity.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);

        AppInfo appInfo;
        for (PackageInfo packageInfo:installedPackages) {
            // 过滤自己当前的应用
            if (packageInfo == null || activity.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }
            // 过滤系统的应用
            if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) > 0) {
                continue;
            }
            appInfo = new AppInfo();
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
            appInfo.setPackageName(packageInfo.packageName);
            appInfo.setVersionCode(packageInfo.versionCode);
            appInfo.setVersionName(packageInfo.versionName);
            Log.i("AppMemory", "应用名:"+ appInfo.getAppName());
            //去除系统应用
//            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                 appInfoList.add(appInfo);
//            }
        }
        Log.i("AppMemory", appInfoList.size()+"个应用");
        return appInfoList;
    }

    /**
     * 获取正在运行应用进程
     *
     * @param activity
     * @return
     */
    @Deprecated
    List<ActivityManager.RunningAppProcessInfo> getRunningAppProcess(Activity activity) {
        ActivityManager activityManger = (ActivityManager) activity.getSystemService(activity.ACTIVITY_SERVICE);
        return activityManger.getRunningAppProcesses();
    }

    int getAllAppMemory(Activity activity) {
        int memo = 0;
        List<AppInfo> resule = getAllAppInfo(activity);
        for (AppInfo info : resule) {
            memo += info.getMemorySize();
        }
        return memo;
    }

    List<AppInfo> getAllAppInfo(Activity activity) {
        List<AppInfo> resule = new ArrayList<AppInfo>();
        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = activity.getPackageManager();
        AppUtil proutils = new AppUtil(activity);
        List<ActivityManager.RunningAppProcessInfo> listInfo = am.getRunningAppProcesses();

       // Log.i("AppMemory", listInfo.size()+"个进程");
        if (listInfo.isEmpty() || listInfo.size() == 0) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo info : listInfo) {
            ApplicationInfo app = proutils.getApplicationInfo(info.processName);
            // 过滤自己当前的应用
            if (app == null || activity.getPackageName().equals(app.packageName)) {
                continue;
            }
            // 过滤系统的应用
            if ((app.flags & app.FLAG_SYSTEM) > 0) {
                continue;
            }
            AppInfo ent = new AppInfo();
            ent.setAppIcon(app.loadIcon(pm));//应用的图标
            ent.setAppName(app.loadLabel(pm).toString());//应用的名称
            ent.setPackageName(app.packageName);//应用的包名
            // 计算应用所占内存大小
            int[] myMempid = new int[]{info.pid};
            Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
            double memSize = memoryInfo[0].dalvikPrivateDirty / 1024.0;
            int temp = (int) (memSize * 100);
            memSize = temp / 100.0;
            ent.setMemorySize(memSize);//应用所占内存的大小
            Log.i("AppMemory", app.packageName + ":" + memSize);
            resule.add(ent);
        }

        return resule;
    }
//    private String getAllAppInfoNew() {
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTime(new Date());
//        long endt = calendar.getTimeInMillis();//结束时间
//        calendar.add(Calendar.DAY_OF_MONTH, -1);//时间间隔为一个月
//        long statt = calendar.getTimeInMillis();//开始时间
//        UsageStatsManager usageStatsManager=(UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
//        //获取一个月内的信息
//        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_MONTHLY,statt,endt);
//
//        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
//            return null;
//        }
//
//        UsageStats recentStats = null;
//        for (UsageStats usageStats : queryUsageStats) {
//
//            if(recentStats == null || recentStats.getLastTimeUsed() < usageStats.getLastTimeUsed()){
//                recentStats = usageStats;
//            }
//        }
//
//        return recentStats.getPackageName();
//    }
@Deprecated
    void clearMemory(Activity activity) {
        ActivityManager activityManger = (ActivityManager) activity.getSystemService(activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appList = activityManger.getRunningAppProcesses();
        if (appList != null) {
            for (int i = 0; i < appList.size(); i++) {
                ActivityManager.RunningAppProcessInfo appInfo = appList.get(i);

                String[] pkgList = appInfo.pkgList;
                if (appInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    for (int j = 0; j < pkgList.length; j++) {
                        activityManger.killBackgroundProcesses(pkgList[j]);
                    }
                }


            }
        }
    }
}
