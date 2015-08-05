package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.DrawerListData;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by miyaeyo on 2015. 8. 4..
 */
public class DrawerListAdapter extends ArrayAdapter<DrawerListData> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<DrawerListData> drawerListData;

    public DrawerListAdapter(Context context, int layoutResourceId, ArrayList<DrawerListData> drawerListData) {
        super(context, layoutResourceId, drawerListData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.drawerListData = drawerListData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
        }
        TextView itemTitle = (TextView) view.findViewById(R.id.drawer_menu_title);
        itemTitle.setText(drawerListData.get(position).getListTitle());

        ImageView itemImage = (ImageView) view.findViewById(R.id.drawer_menu_image);
        int imageId = drawerListData.get(position).getImageId();
        itemImage.setImageResource(imageId);
        return view;
    }
}
