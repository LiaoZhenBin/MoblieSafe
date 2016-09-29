package com.liaozhenbin.mobliesafe.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.animation.MyAnimation;


public class SplashActivity extends AppCompatActivity {

    private ImageView icon;
    private ImageView wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon = (ImageView) findViewById(R.id.iv_icon);
        wallet = (ImageView) findViewById(R.id.iv_wallet);

        startIcon();
        initWallet();

    }

    private void initWallet() {
        final ValueAnimator  valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(animation.getAnimatedFraction() >= 0.7){
                    valueAnimator.cancel();
                    startWallet();
                }
            }
        });
        valueAnimator.start();
    }

    private void startWallet() {
        icon.setVisibility(View.INVISIBLE);

        // x轴缩放
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(wallet, "scaleX", 1, 1.1f, 0.9f, 1);
        objectAnimator1.setDuration(600);
        // y轴缩放
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(wallet, "scaleY", 1, 0.75f, 1.25f, 1);
        objectAnimator2.setDuration(600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        // 同时执行x，y轴缩放动画
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.start();
    }



    private void startIcon() {
        //掉落动画
        TranslateAnimation translate = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        translate.setDuration(2000);
        //旋转动画
        Animation rotate = new MyAnimation();
        rotate.setDuration(1000);
        rotate.setRepeatCount(4);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1500);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(translate);
        icon.startAnimation(animationSet);
    }
}
