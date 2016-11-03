package com.liaozhenbin.mobliesafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.domain.Icon;

import java.util.ArrayList;
import java.util.List;

public class IconAadapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Icon> iconList;

    public IconAadapter(Context context, String[] names, int[] images) {
        super();
        inflater = LayoutInflater.from(context);
        iconList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Icon icon = new Icon(names[i], images[i]);
            iconList.add(icon);
        }
    }

    @Override
    public int getCount() {
        return iconList.size();
    }

    @Override
    public Object getItem(int position) {
        return iconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.icon, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.icon_name);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.icon_image);
            viewHolder.animation = (ImageView) convertView.findViewById(R.id.animation);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(iconList.get(position).getIconName());
        viewHolder.image.setImageResource(iconList.get(position).getIconImage());
        viewHolder.animation.setImageResource(iconList.get(position).getIconImage());
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
        public ImageView image;
        public ImageView animation;
    }
}