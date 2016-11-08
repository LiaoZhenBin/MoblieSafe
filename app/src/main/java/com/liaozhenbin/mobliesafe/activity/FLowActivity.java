package com.liaozhenbin.mobliesafe.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.adapter.FlowAdapter;
import com.liaozhenbin.mobliesafe.domain.TrafficInfo;
import com.liaozhenbin.mobliesafe.provider.TrafficInfoProvider;

import java.util.List;


public class FLowActivity extends BaseActivity {
    private RecyclerView recyclerView;
    //封装单个具有Intenet权限的应用的流量信息
    private List<TrafficInfo> trafficInfos;
    private TrafficInfoProvider infoProvider;
    //处理子线程发送过来的消息，更新UI
    private ProgressBar progressBar;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (trafficInfos != null) {
                recyclerView.setAdapter(new FlowAdapter(trafficInfos));
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initUI();
        infoProvider = new TrafficInfoProvider(this);

        new Thread(){
            public void run() {
                trafficInfos = infoProvider.getTrafficInfos();
                //想主线程中发送一个空消息，用于通知主线程更新数据
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_flow);
        progressBar = (ProgressBar) findViewById(R.id.pg);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
