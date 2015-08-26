package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhnnext.android.miyaeyo.danji.MyApplication;
import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.adapter.ContentsListAdapter;
import com.nhnnext.android.miyaeyo.danji.data.ContentsListData;
import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.nhnnext.android.miyaeyo.danji.data.Inbox;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * To do
 * 내가 쓴글만 모아서 보여줌. 이걸 위해서는 로그인이 필요해서 소셜 로그인을 붙일 예정..
 */
public class MyPageFragment extends Fragment {
    private TextView danjiUserName;
    private ListView myContentsListView;
    private ParseUser currentUser;
    private TextView myWallCount;
    private TextView myInboxCount;
    private ImageButton movie;
    private ImageButton drama;
    private ImageButton book;
    private ImageButton poem;
    private ImageButton music;
    private ImageButton cartoon;
    private ImageButton inbox;

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
        return inflater.inflate(R.layout.my_page_f, null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentUser = ParseUser.getCurrentUser();
        danjiUserName = (TextView) getActivity().findViewById(R.id.danji_user_name);
        danjiUserName.setText(currentUser.getUsername());
        myWallCount = (TextView) getActivity().findViewById(R.id.my_wall);
        myInboxCount = (TextView) getActivity().findViewById(R.id.my_inbox);
        myContentsListView = (ListView) getActivity().findViewById(R.id.mypage_contents);
        movie = (ImageButton) getActivity().findViewById(R.id.mypage_movie);
        drama = (ImageButton) getActivity().findViewById(R.id.mypage_drama);
        book = (ImageButton) getActivity().findViewById(R.id.mypage_book);
        poem = (ImageButton) getActivity().findViewById(R.id.mypage_poem);
        music = (ImageButton) getActivity().findViewById(R.id.mypage_music);
        cartoon = (ImageButton) getActivity().findViewById(R.id.mypage_cartoon);
        inbox = (ImageButton) getActivity().findViewById(R.id.mypage_inbox);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        ParseQuery
                .getQuery(Danji.class)
                .whereEqualTo("UserName", currentUser.getUsername())
                .orderByDescending("createdAt")
                .findInBackground(new FindCallback<Danji>() {
                    @Override
                    public void done(List<Danji> danjiList, ParseException e) {
                        if (e == null) {
                            Log.e(MyApplication.TAG, "ParseException: " + e);
                        }
                        int count = danjiList.size();
                        myWallCount.setText("" + count);
                        ArrayList<ContentsListData> contentsListDataArray = new ArrayList<ContentsListData>();
                        for (Danji danji : danjiList) {
                            contentsListDataArray.add(new ContentsListData(danji));
                            myContentsListView.setAdapter(
                                    new ContentsListAdapter(getActivity(), contentsListDataArray));
                        }

                    }
                });

        ParseQuery
                .getQuery(Inbox.class)
                .whereEqualTo("UserName", currentUser.getUsername())
                .findInBackground(new FindCallback<Inbox>() {
                    @Override
                    public void done(List<Inbox> inboxList, ParseException e) {
                        if(e == null){
                            Log.e(MyApplication.TAG, "ParseException: "+ e);
                        }
                        int count = inboxList.size();
                        myInboxCount.setText("" + count);
                    }
                });

        movie.setOnClickListener(new CategoryClickListener("movie", movie));
        drama.setOnClickListener(new CategoryClickListener("drama", drama));
        book.setOnClickListener(new CategoryClickListener("book", book));
        poem.setOnClickListener(new CategoryClickListener("poem", poem));
        music.setOnClickListener(new CategoryClickListener("music", music));
        cartoon.setOnClickListener(new CategoryClickListener("cartoon", cartoon));
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "inbox selected", Toast.LENGTH_SHORT).show();
                ParseQuery
                        .getQuery(Inbox.class)
                        .whereEqualTo("UserName", currentUser.getUsername())
                        .findInBackground(new FindCallback<Inbox>() {
                            @Override
                            public void done(List<Inbox> inboxList, ParseException e) {
                                setInboxList(new ArrayList<ContentsListData>(), inboxList);
                            }
                        });
            }
        });


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

    private class CategoryClickListener implements View.OnClickListener {
        private String category;
        private ImageButton button;

        private CategoryClickListener(String category, ImageButton button) {
            this.category = category;
            this.button = button;
        }

        @Override
        public void onClick(View v) {
            setContentsList(new ArrayList<ContentsListData>(), category);
            Toast.makeText(getActivity(), category+" selected", Toast.LENGTH_SHORT).show();
//            button.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
        }
    }

    private void setContentsList(final ArrayList<ContentsListData> contentsListDataArray, String selectedCategory) {
        ParseQuery
                .getQuery(Danji.class)
                .whereEqualTo("UserName", currentUser.getUsername())
                .whereEqualTo("Category", selectedCategory)
                .orderByDescending("createdAt")
                .findInBackground(new FindCallback<Danji>() {
                      @Override
                      public void done(List<Danji> danjiList, ParseException e) {
                          if (e != null) {
                              Log.d(MyApplication.TAG, "ParseException: " + e);
                              return;
                          }

                          for (Danji danji : danjiList) {
                              contentsListDataArray.add(new ContentsListData(danji));
                              myContentsListView.setAdapter(
                                      new ContentsListAdapter(getActivity(), contentsListDataArray));
                          }
                      }
                });
    }

    private void setInboxList(final ArrayList<ContentsListData> contentsListDataArray, List<Inbox> inboxList){
       for(Inbox inbox: inboxList){
           ParseQuery
                   .getQuery(Danji.class)
                   .getInBackground(inbox.getInboxId(), new GetCallback<Danji>() {
                       @Override
                       public void done(Danji danji, ParseException e) {
                           if (e != null) {
                               Log.d(MyApplication.TAG, "ParseException: " + e);
                               return;
                           }
                           contentsListDataArray.add(new ContentsListData(danji));
                           myContentsListView.setAdapter(
                                   new ContentsListAdapter(getActivity(), contentsListDataArray));

                       }
                   });
       }
    }
}
