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
        int movieLike = 0, dramaLike = 0, bookLike = 0, poemLike = 0, musicLike = 0, cartoonLike = 0;
        ContentsListData movie = new ContentsListData("movie.jpeg",getString(R.string.movie_contents_body),getString(R.string.movie_contents_reference), movieLike);
        contentsListDataArray.add(movie);
        ContentsListData drama = new ContentsListData("drama.jpeg",getString(R.string.drama_contents_body),getString(R.string.drama_contents_reference), dramaLike);
        contentsListDataArray.add(drama);
        ContentsListData book = new ContentsListData("book.jpeg",getString(R.string.book_contents_body),getString(R.string.book_contents_reference), bookLike);
        contentsListDataArray.add(book);
        ContentsListData poem = new ContentsListData("poem.jpeg",getString(R.string.poem_contents_body),getString(R.string.poem_contents_reference), poemLike);
        contentsListDataArray.add(poem);
        ContentsListData music = new ContentsListData("music.jpeg",getString(R.string.music_contents_body),getString(R.string.music_contents_reference), musicLike);
        contentsListDataArray.add(music);
        ContentsListData cartoon = new ContentsListData("cartoon.jpeg",getString(R.string.cartoon_contents_body),getString(R.string.cartoon_contents_reference), cartoonLike);
        contentsListDataArray.add(cartoon);
    }
}
