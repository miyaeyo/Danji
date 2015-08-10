package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.DrawerListData;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by miyaeyo on 2015. 8. 4..
 */
public class DrawerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DrawerListData> drawerListData;

    public DrawerListAdapter(Context context, ArrayList<DrawerListData> drawerListData) {
        this.context = context;
        this.drawerListData = drawerListData;
    }

    @Override
    public int getCount() {
        return drawerListData.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       if(convertView == null){
           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.drawer_list, null);
       }

        ImageView itemIcon = (ImageView) convertView.findViewById(R.id.drawer_menu_image);
        TextView itemTitle = (TextView) convertView.findViewById(R.id.drawer_menu_title);
        itemIcon.setImageResource(drawerListData.get(position).getIcon());
        itemTitle.setText(drawerListData.get(position).getListTitle());

        return convertView;
    }
}
