package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.RankListAdapter;
import com.nhnnext.android.miyaeyo.danji.data.ContentsRankData;
import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/** To do
 * category별 contents갯수를 표시하는 창
 * 각 버튼 클릭하면 ContentsViewFragment에 해당 category의 contents만 보여줌
 */
public class ContentsSearchFragment extends Fragment{
    private ListView contentsRankView;

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
        return inflater.inflate(R.layout.contents_search_f, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contentsRankView = (ListView)getActivity().findViewById(R.id.contents_rank);
        final ArrayList<ContentsRankData> contentsRankDataArray = new ArrayList<ContentsRankData>();
        setRankList(contentsRankDataArray);

        contentsRankView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String searchQuery = contentsRankDataArray.get(position).getContentsTitle();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
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

    private void setRankList(final ArrayList<ContentsRankData> contentsRankDataArray){
        ParseQuery<Danji> query = ParseQuery.getQuery(Danji.class);
        query.orderByDescending("LikeCount");
        query.setLimit(5);
        query.findInBackground(new FindCallback<Danji>() {
            @Override
            public void done(List<Danji> list, ParseException e) {
                int contentsRank = 0;
                for(Danji danji: list){
                    contentsRank++;
                    String contentsTitle = danji.getContentsTitle();
                    String category = danji.getCategory();
                    String[] itemTitle = getResources().getStringArray(R.array.contents_category_title);
                    TypedArray itemIcon = getResources().obtainTypedArray(R.array.contents_category_icon);
                    int contentsCategory = itemIcon.getResourceId(0,-1);
                    for(int i = 0; i < itemTitle.length; i++){
                        if(category.equalsIgnoreCase(itemTitle[i])){
                            contentsCategory = itemIcon.getResourceId(i, -1);
                        }
                    }
                    ContentsRankData contentsRankData = new ContentsRankData(contentsRank, contentsTitle, contentsCategory);
                    contentsRankDataArray.add(contentsRankData);
                }
                RankListAdapter rankListAdapter = new RankListAdapter(getActivity(), R.layout.contents_rank_list, contentsRankDataArray);
                contentsRankView.setAdapter(rankListAdapter);
            }
        });

    }
}
