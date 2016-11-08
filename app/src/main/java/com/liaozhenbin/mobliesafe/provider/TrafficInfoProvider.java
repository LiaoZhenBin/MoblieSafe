package com.liaozhenbin.mobliesafe.provider;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;

import com.liaozhenbin.mobliesafe.domain.TrafficInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaozhenbin on 2016/11/7.
 */

public class TrafficInfoProvider {
    private Context context;
    private PackageManager manager;

    public TrafficInfoProvider(Context context) {
        this.context = context;
        manager = context.getPackageManager();
    }


    /**
     * return information of all the application witch use intent
     *
     * @return
     */
    public List<TrafficInfo> getTrafficInfos() {
        //获取到配置权限信息的应用程序
        List<PackageInfo> packageInfos = manager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        List<TrafficInfo> trafficInfos = new ArrayList<>();

        for (PackageInfo info : packageInfos) {
            //获取当前应用所有需要的权限
            String[] permissions = info.requestedPermissions;
            if (permissions != null && permissions.length > 0) {
                for (String permission : permissions) {
                    //筛选出有网络权限的应用
                    if ("android.permission.INTERNET".equals(permission)) {
                        TrafficInfo trafficInfo = new TrafficInfo();

                        trafficInfo.setPackageName(info.packageName);
                        trafficInfo.setIcon(info.applicationInfo.loadIcon(manager));
                        trafficInfo.setAppName(info.applicationInfo.loadLabel(manager).toString());

                        //获取到应用的uid (user id)
                        int uid = info.applicationInfo.uid;
                        //TrafficStats对象通过应用的uid来获取应用的下载、上传流量信息
                        trafficInfo.setDownFlow(TrafficStats.getUidRxBytes(uid));
                        trafficInfo.setUpFlow(TrafficStats.getUidTxBytes(uid));
                        trafficInfos.add(trafficInfo);
                        break;
                    }
                }
            }
        }
        return trafficInfos;
    }
}
