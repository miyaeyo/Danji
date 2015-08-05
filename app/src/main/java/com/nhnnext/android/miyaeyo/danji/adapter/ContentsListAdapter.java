package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.ContentsListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by miyaeyo on 2015. 8. 5..
 */
public class ContentsListAdapter extends ArrayAdapter<ContentsListData>{
    private Context context;
    private int layoutResourceId;
    private ArrayList<ContentsListData> contentsListData;

    public ContentsListAdapter(Context context, int layoutResourceId, ArrayList<ContentsListData> contestsListData){
        super(context, layoutResourceId, contestsListData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.contentsListData = contestsListData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView contentsBody = (TextView)view.findViewById(R.id.contents_body);
        TextView contentsRefer = (TextView)view.findViewById(R.id.contents_reference);
        TextView likeCount = (TextView)view.findViewById(R.id.count_like);

        contentsBody.setText(contentsListData.get(position).getContentsBody());
        contentsRefer.setText(contentsListData.get(position).getContentsRefer());
        likeCount.setText(""+contentsListData.get(position).getLikeCount());

        ImageView contentsImage = (ImageView)view.findViewById(R.id.contents_image);
        try{
            InputStream inputStream = context.getAssets().open(contentsListData.get(position).getContentsImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            contentsImage.setImageDrawable(drawable);
        } catch(IOException e){
            Log.e("ERROR", "ERROR: "+e);
        }
        return view;
    }
}
