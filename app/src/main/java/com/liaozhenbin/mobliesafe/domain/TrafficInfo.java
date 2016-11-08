package com.liaozhenbin.mobliesafe.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by liaozhenbin on 2016/11/7.
 */

public class TrafficInfo {
    private String packageName;
    private String appName;
    private long upFlow;
    private long downFlow;
    private Drawable icon;

    public String getPackageName() {
        return packageName;
    }

    public String getAppName() {
        return appName;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
