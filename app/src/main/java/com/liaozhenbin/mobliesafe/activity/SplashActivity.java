package com.liaozhenbin.mobliesafe.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.animation.MyAnimation;


public class SplashActivity extends BaseActivity {

    private ImageView icon;
    private ImageView wallet;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startAnim();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
        startAnim();
        splashing();

    }


    /**
     * play two times animation
     */
    private void splashing() {
        new Thread() {
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage();
                long startTime = System.currentTimeMillis();
                try {
                    mHandler.sendMessageDelayed(msg, 1500);
                } finally {
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 3500) {
                        try {
                            Thread.sleep(3500 - (endTime - startTime));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    startActivity(new Intent(SplashActivity.this,IndexActivity.class));
                    finish();
                }

            }
        }.start();
    }

//    /**
//     * connect intent to check the version
//     */
//    private void getData() {
//        new Thread() {
//
//            Message msg = new Message();
//
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://www.weather.com.cn/data/cityinfo/101190404.html");
//
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    //3,设置常见请求参数(请求头)
//
//                    //请求超时
//                    connection.setConnectTimeout(4000);
//                    //读取超时
//                    connection.setReadTimeout(4000);
//
//                    //默认就是get请求方式,
//                    //connection.setRequestMethod("POST");
//
//                    //4,获取请求成功响应码
//                    if(connection.getResponseCode() == 200){
//                        //5,以流的形式,将数据获取下来
//                        InputStream is = connection.getInputStream();
//                        //6,将流转换成字符串(工具类封装)
//                        String json = StreamUtil.streamToString(is);
//
//                        Log.d("Liao",json+"____");
//
//                        msg.what = 1;
//                        msg.obj = json;
//                        mHandler.sendMessage(msg);
//                    }
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }

    public void initUI() {
        icon = (ImageView) findViewById(R.id.iv_icon);
        wallet = (ImageView) findViewById(R.id.iv_wallet);
    }


    /**
     * start splash animation
     */
    private void startAnim() {
        startIcon();
        initWallet();
    }


    private void initWallet() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() >= 0.55) {
                    valueAnimator.cancel();
                    startWallet();
                }
            }
        });
        valueAnimator.start();
    }

    /**
     * start wallet animation
     */
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


    /**
     * icon start animation
     */
    private void startIcon() {
        icon.setVisibility(View.VISIBLE);
        //掉落动画
        TranslateAnimation translate = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        translate.setDuration(1400);
        //旋转动画
        Animation rotate = new MyAnimation();
        rotate.setRepeatCount(10);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(translate);
        icon.startAnimation(animationSet);
    }
}
