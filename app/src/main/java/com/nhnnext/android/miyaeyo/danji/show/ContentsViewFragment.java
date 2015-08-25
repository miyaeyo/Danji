package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

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

/** To do
 *  DB에서 모든 data다 받아와서 contents를 view에 맞게 뿌려줌
 */
public class ContentsViewFragment extends Fragment {
    private ListView mlistView;
    static ContentsViewFragment contentsViewFragment;
    private TextView category = null;


    public static ContentsViewFragment getInstance(String category){
        Bundle args = new Bundle();
        args.putString("selectedCategory", category);
        if(contentsViewFragment == null) {
            contentsViewFragment = new ContentsViewFragment();
            contentsViewFragment.setArguments(args);

        } else {
            contentsViewFragment.getArguments().putAll(args);
        }

        return contentsViewFragment;
    }

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

    public String getSelectedCategory(){
        if(getArguments() == null){
            return "total";
        } else {
            return getArguments().getString("selectedCategory");
        }
    }

    private void setContentsList(final ArrayList<ContentsListData> contentsListDataArray) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Danji");
        query.orderByDescending("LikeCount");
        String selectedCategory = getSelectedCategory();
        Log.d("EEE", "2. 받을 때: "+selectedCategory);
        FrameLayout categoryFrame = (FrameLayout)getActivity().findViewById(R.id.selected_category);
        if(category != null){
            categoryFrame.removeAllViews();
        }

        if(!selectedCategory.equalsIgnoreCase("total")) {
            query.whereEqualTo("Category", selectedCategory.toLowerCase());
            category = new TextView(getActivity());
            category.setText(selectedCategory);
            category.setBackgroundColor(getResources().getColor(R.color.tabColor));
            category.setTextColor(getResources().getColor(R.color.contentsBackroundColor));
            DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
            float padding  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, metrics);
            category.setPadding((int)padding, (int)padding, (int)padding, (int)padding);
            categoryFrame.addView(category);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            Dialog progressDialog = ProgressDialog.show(getActivity(), "","connecting in danji...",true);
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null){
                    for(ParseObject pObject: list){
                        Danji danji = (Danji)pObject;
                        String danjiID = danji.getObjectId();
                        ParseFile image = danji.getContentsImage();
                        String contentsBody = danji.getContentsBody();
                        String contentsRefer;
                        if(danji.getCreator().equals("")){
                            contentsRefer = danji.getContentsTitle();
                        } else {
                            contentsRefer = danji.getCreator() + ", " + danji.getContentsTitle();
                        }
                        int likeCount = danji.getLikeCount();
                        ContentsListData contentsListData = new ContentsListData(danjiID, image, contentsBody, contentsRefer, likeCount);
                        contentsListDataArray.add(contentsListData);
                        ContentsListAdapter contentsListAdapter = new ContentsListAdapter(getActivity(), R.layout.contents_list, contentsListDataArray);
                        mlistView.setAdapter(contentsListAdapter);
                        progressDialog.dismiss();

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
