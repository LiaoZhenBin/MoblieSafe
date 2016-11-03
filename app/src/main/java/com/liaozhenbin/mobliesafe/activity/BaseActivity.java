package com.liaozhenbin.mobliesafe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liaozhenbin.mobliesafe.R;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    public abstract void initUI();


}
