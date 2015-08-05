package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
        final int mPosition = position;
        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageButton likeButton = (ImageButton)convertView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int count = contentsListData.get(mPosition).getLikeCount();
                count++;
                contentsListData.get(mPosition).setLikeCount(count);
            }
        });

        TextView contentsBody = (TextView)convertView.findViewById(R.id.contents_body);
        TextView contentsRefer = (TextView)convertView.findViewById(R.id.contents_reference);
        TextView likeCount = (TextView)convertView.findViewById(R.id.count_like);

        contentsBody.setText(contentsListData.get(mPosition).getContentsBody());
        contentsRefer.setText(contentsListData.get(mPosition).getContentsRefer());
        likeCount.setText(""+contentsListData.get(mPosition).getLikeCount());

        ImageView contentsImage = (ImageView)convertView.findViewById(R.id.contents_image);
        try{
            InputStream inputStream = context.getAssets().open(contentsListData.get(mPosition).getContentsImage());
            Bitmap image = BitmapFactory.decodeStream(inputStream);
            contentsImage.setImageBitmap(image);
            contentsImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } catch(IOException e){
            Log.e("ERROR", "ERROR: "+e);
        }



        return convertView;
    }
}
