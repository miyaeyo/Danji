package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhnnext.android.miyaeyo.danji.MyApplication;
import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.ContentsListData;
import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.nhnnext.android.miyaeyo.danji.data.Inbox;
import com.nhnnext.android.miyaeyo.danji.show.SearchWebview;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miyaeyo on 2015. 8. 5..
 */
public class ContentsListAdapter extends ArrayAdapter<ContentsListData>{
    private Context context;
    private int layoutResourceId;
    private ArrayList<ContentsListData> contentsListData;
    private ParseUser currentUser = ParseUser.getCurrentUser();


    public ContentsListAdapter(Context context, ArrayList<ContentsListData> contentsListData){
        super(context, R.layout.contents_list, contentsListData);
        this.context = context;
        this.layoutResourceId = R.layout.contents_list;
        this.contentsListData = contentsListData;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageButton likeButton = (ImageButton)convertView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = contentsListData.get(position).getLikeCount();
                count++;
                contentsListData.get(position).setLikeCount(count);
                notifyDataSetChanged();
                Toast.makeText(getContext(), R.string.like, Toast.LENGTH_SHORT).show();

                String danjiID = contentsListData.get(position).getDanjiID();
                Log.d("EEE", "id "+danjiID);
                ParseQuery<Danji> query = ParseQuery.getQuery(Danji.class);
                query.getInBackground(danjiID, new GetCallback<Danji>() {
                    @Override
                    public void done(Danji danji, ParseException e) {
                        danji.increment("LikeCount", 1);
                        danji.saveInBackground();
                    }
                });
            }
        });

        ImageButton inboxButton = (ImageButton)convertView.findViewById(R.id.inbox_button);
        inboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String currentUserName = currentUser.getUsername();
                String createdUserName = contentsListData.get(position).getUserName();
                if(currentUserName.equals(createdUserName)){
                   Toast.makeText(getContext(), "This content is created by you", Toast.LENGTH_LONG).show();
                } else {
                    final String inboxId = contentsListData.get(position).getDanjiID();
                    ParseQuery.getQuery(Inbox.class)
                            .whereEqualTo("InboxId", inboxId)
                            .findInBackground(new FindCallback<Inbox>() {
                                @Override
                                public void done(List<Inbox> inboxList, ParseException e) {
                                    if (e != null) {
                                        Log.e(MyApplication.TAG, "ParseException: " + e);
                                    } else if(inboxList.size() == 0){
                                        Inbox newInbox = new Inbox();
                                        newInbox.setUserName(currentUserName);
                                        newInbox.setInboxId(inboxId);
                                        newInbox.saveInBackground();
                                        Toast.makeText(getContext(), R.string.pick_inbox, Toast.LENGTH_SHORT).show();
                                    } else {
                                        for(Inbox inbox: inboxList){
                                            if (inbox.getUserName().equals(currentUserName)) {
                                                Toast.makeText(getContext(), "Already exist in your inbox", Toast.LENGTH_LONG).show();
                                            } else {
                                                Inbox newInbox = new Inbox();
                                                newInbox.setUserName(currentUserName);
                                                newInbox.setInboxId(inboxId);
                                                newInbox.saveInBackground();
                                                Toast.makeText(getContext(), R.string.pick_inbox, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                }
            }
        });

        TextView contentsBody = (TextView)convertView.findViewById(R.id.contents_body);
        TextView contentsRefer = (TextView)convertView.findViewById(R.id.contents_reference);
        contentsRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchWebview.class);
                String searchQuery = Uri.encode(contentsListData.get(position).getContentsRefer());
                intent.putExtra("Query", searchQuery);
                context.startActivity(intent);
            }
        });
        TextView likeCount = (TextView)convertView.findViewById(R.id.count_like);

        contentsBody.setText(contentsListData.get(position).getContentsBody());
        contentsRefer.setText(contentsListData.get(position).getContentsRefer());
        likeCount.setText(""+contentsListData.get(position).getLikeCount());

        final ImageView contentsImage = (ImageView)convertView.findViewById(R.id.contents_image);
        ParseFile image = contentsListData.get(position).getContentsImage();
        image.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if(e == null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    contentsImage.setImageBitmap(bitmap);
                    contentsImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    Log.e(MyApplication.TAG, "ParseException"+ e);
                }
            }
        });

//        try{
//
//            InputStream inputStream = context.getAssets().open(contentsListData.get(position).getContentsImage());
//            Bitmap image = BitmapFactory.decodeStream(inputStream);
//            contentsImage.setImageBitmap(image);
//            contentsImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        } catch(IOException e){
//            Log.e(MyApplication.TAG, "ERROR: "+e);
//        }

        return convertView;
    }
}
