package com.liaozhenbin.mobliesafe.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by liaozhenbin on 2016/11/3.
 */

public class ActivityUtil {
    private static final String packageName = "com.liaozhenbin.mobliesafe";
    private static final String[][] classNames = {{".activity.SpeedActivity", ".activity.ClearStorageActivity", ".activity.AutoActivity",
            ".activity.FLowActivity", ".activity.HarassActivity", ".activity.SoftManagerActivity"}, {".activity.AppLockActivity",".activity.VirusActivity",
            ".activity.PermissionActivity",".activity.TheftActivity",".activity.PrivateActivity"}};

    public static void startMyActivity(Context context,int arg0, int arg1){
        context.startActivity(new Intent().setComponent(new ComponentName(packageName,packageName+classNames[arg0][arg1])));
    }
}
