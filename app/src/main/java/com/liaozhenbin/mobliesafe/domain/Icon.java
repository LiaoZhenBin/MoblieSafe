package com.liaozhenbin.mobliesafe.domain;

/**
 * Created by liaozhenbin on 2016/11/1.
 */

public class Icon {
    private String iconName;
    private int iconImage;

    public Icon() {

    }

    public Icon(String iconName, int iconImage) {
        this.iconName = iconName;
        this.iconImage = iconImage;
    }

    public String getIconName() {
        return iconName;
    }

    public int getIconImage() {
        return iconImage;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }
}
