package com.liaozhenbin.mobliesafe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.domain.TrafficInfo;

import java.util.List;

/**
 * Created by liaozhenbin on 2016/11/7.
 */

public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.ViewHolder> {
    private List<TrafficInfo> infos;

    public FlowAdapter(List<TrafficInfo> infos) {
        this.infos = infos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flow, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.appName = (TextView) view.findViewById(R.id.tv_name);
        holder.icon = (ImageView) view.findViewById(R.id.app_label);
        holder.upFlow = (TextView) view.findViewById(R.id.tv_up);
        holder.downFlow = (TextView) view.findViewById(R.id.tv_down);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrafficInfo info = infos.get(position);
        holder.appName.setText(info.getAppName());
        holder.icon.setImageDrawable(info.getIcon());

        long up = info.getUpFlow();
        long down = info.getDownFlow();
        holder.upFlow.setText(up + "");
        holder.downFlow.setText(down + "");
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView packageName;
        private TextView appName;
        private TextView upFlow;
        private TextView downFlow;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

