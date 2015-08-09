package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.ContentsListAdapter;
import com.nhnnext.android.miyaeyo.danji.data.ContentsListData;

import java.util.ArrayList;

/** To do
 *  DB에서 모든 data다 받아와서 contents를 view에 맞게 뿌려줌
 */
public class ContentsViewFragment extends Fragment {

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
        ArrayList<ContentsListData> contentsListDataArray = new ArrayList<ContentsListData>();
        setContentsList(contentsListDataArray);
        ListView listView = (ListView)getActivity().findViewById(R.id.contents_view);
        ContentsListAdapter contentsListAdapter = new ContentsListAdapter(getActivity(), R.layout.contents_list, contentsListDataArray);
        listView.setAdapter(contentsListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();



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

    private void setContentsList(ArrayList<ContentsListData> contentsListDataArray) {
        String[] imageFileName = getResources().getStringArray(R.array.image_file_name);
        String[] contentsBody = getResources().getStringArray(R.array.contents_body);
        String[] contentsRefer = getResources().getStringArray(R.array.contents_reference);
        int[] likeCount = new int[6];

        for(int i = 0; i<imageFileName.length; i++){
            ContentsListData contentsListData = new ContentsListData(imageFileName[i], contentsBody[i], contentsRefer[i], likeCount[i]);
            contentsListDataArray.add(contentsListData);
        }
    }


}
