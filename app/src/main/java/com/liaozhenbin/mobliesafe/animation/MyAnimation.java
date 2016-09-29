package com.liaozhenbin.mobliesafe.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;


/**
 * Created by liaozhenbin on 2016/9/29.
 */

public class MyAnimation extends Animation {
    private int centerX;
    private int centerY;

    Camera camera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 中心点坐标
        centerX = width / 2;
        centerY = height / 2;
        setDuration(500);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        camera.save();

        // 绕y轴旋转
        camera.rotateX(360 * interpolatedTime);
        camera.getMatrix(matrix);
        // 设置翻转中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }
}
