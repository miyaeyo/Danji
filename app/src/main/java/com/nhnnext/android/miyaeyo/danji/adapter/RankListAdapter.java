package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.ContentsRankData;
import java.util.ArrayList;

/**
 * Created by miyaeyo on 2015. 8. 26..
 */
public class RankListAdapter extends ArrayAdapter<ContentsRankData> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<ContentsRankData> contentsRankData;

    public RankListAdapter(Context context, int layoutResourceId, ArrayList<ContentsRankData> contentsRankData){
        super(context, layoutResourceId, contentsRankData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.contentsRankData = contentsRankData;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contents_rank_list, null);
        }
        TextView contentsRank = (TextView)convertView.findViewById(R.id.contents_rank_num);
        ImageView contentsCategory = (ImageView)convertView.findViewById(R.id.contents_rank_image);
        TextView contentsTitle = (TextView)convertView.findViewById(R.id.contents_rank_title);

        contentsRank.setText(""+contentsRankData.get(position).getContentsRank());
        contentsCategory.setImageResource(contentsRankData.get(position).getContentsCategory());
        contentsTitle.setText(contentsRankData.get(position).getContentsTitle());

        return convertView;
    }
}
