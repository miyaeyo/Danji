package com.nhnnext.android.miyaeyo.danji.show;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.write.WriteDialogQuotation;
import com.nhnnext.android.miyaeyo.danji.write.WriteParagraphQuotation;
import com.parse.ParseObject;


/**To do
 * category별 contents 갯수가 들어간 이미지 버튼이 위치
 */
public class WriteCategoryFragment extends Fragment {
    private Button movieButton;
    private Button dramaButton;
    private Button bookButton;
    private Button poemButton;
    private Button musicButton;
    private Button cartoonButton;

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
        return inflater.inflate(R.layout.write_category_f, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieButton = (Button)getActivity().findViewById(R.id.write_movie);
        dramaButton = (Button)getActivity().findViewById(R.id.write_drama);
        bookButton = (Button)getActivity().findViewById(R.id.write_book);
        poemButton = (Button)getActivity().findViewById(R.id.write_poem);
        musicButton = (Button)getActivity().findViewById(R.id.write_music);
        cartoonButton = (Button)getActivity().findViewById(R.id.write_cartoon);

        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialogIntent = new Intent(getActivity(), WriteDialogQuotation.class);
                dialogIntent.putExtra("category", "movie");
                startActivity(dialogIntent);
            }
        });

        dramaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialogIntent = new Intent(getActivity(), WriteDialogQuotation.class);
                dialogIntent.putExtra("category", "drama");
                startActivity(dialogIntent);
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paraIntent = new Intent(getActivity(), WriteParagraphQuotation.class);
                paraIntent.putExtra("category", "book");
                startActivity(paraIntent);
            }
        });

        poemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paraIntent = new Intent(getActivity(), WriteParagraphQuotation.class);
                paraIntent.putExtra("category", "poem");
                startActivity(paraIntent);
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paraIntent = new Intent(getActivity(), WriteParagraphQuotation.class);
                paraIntent.putExtra("category", "music");
                startActivity(paraIntent);
            }
        });

        cartoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialogIntent = new Intent(getActivity(), WriteDialogQuotation.class);
                dialogIntent.putExtra("category", "cartoon");
                startActivity(dialogIntent);
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

}
