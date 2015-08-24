package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhnnext.android.miyaeyo.danji.MyApplication;
import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.ContentsListAdapter;
import com.nhnnext.android.miyaeyo.danji.data.ContentsListData;
import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

/** To do
 *  DB에서 모든 data다 받아와서 contents를 view에 맞게 뿌려줌
 */
public class ContentsViewFragment extends Fragment {
    private ListView mlistView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contents_view_f, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<ContentsListData> contentsListDataArray = new ArrayList<ContentsListData>();
        setContentsList(contentsListDataArray);
        mlistView = (ListView)getActivity().findViewById(R.id.contents_view);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setContentsList(final ArrayList<ContentsListData> contentsListDataArray) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Danji");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null){
                    for(ParseObject pObject: list){
                        Danji danji = (Danji)pObject;
                        ParseFile image = danji.getContentsImage();
                        String contentsBody = danji.getContentsBody();
                        String contentsRefer = danji.getCreator() + ", " + danji.getContenstsTitle();
                        int likeCount = danji.getLikeCount();
                        ContentsListData contentsListData = new ContentsListData(image, contentsBody, contentsRefer, likeCount);
                        contentsListDataArray.add(contentsListData);
                        ContentsListAdapter contentsListAdapter = new ContentsListAdapter(getActivity(), R.layout.contents_list, contentsListDataArray);
                        mlistView.setAdapter(contentsListAdapter);

                    }

                } else {
                    Log.d(MyApplication.TAG, "ParseException: "+ e);
                }

            }
        });

//        String[] imageFileName = getResources().getStringArray(R.array.image_file_name);
//        String[] contentsBody = getResources().getStringArray(R.array.contents_body);
//        String[] contentsRefer = getResources().getStringArray(R.array.contents_reference);
//        int[] likeCount = new int[6];
//
//        for(int i = 0; i<imageFileName.length; i++){
//            ContentsListData contentsListData = new ContentsListData(imageFileName[i], contentsBody[i], contentsRefer[i], likeCount[i]);
//            contentsListDataArray.add(contentsListData);
//        }
    }


}
