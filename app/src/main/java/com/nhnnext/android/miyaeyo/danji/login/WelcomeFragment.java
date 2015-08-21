package com.nhnnext.android.miyaeyo.danji.login;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.show.DanjiMainActivity;
import com.parse.ParseUser;

/**
 * Created by miyaeyo on 2015. 8. 20..
 */
public class WelcomeFragment extends Fragment {

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
        return inflater.inflate(R.layout.welcome_f, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        String userName = currentUser.getUsername().toString();

        TextView userNameView = (TextView)getActivity().findViewById(R.id.user_name);
        userNameView.setText("WELCOME "+userName+" :)");

        Button logoutButton = (Button)getActivity().findViewById(R.id.logout_button);
        Button startDanjiButton = (Button)getActivity().findViewById(R.id.start_danji);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                BeforeLoginFragment beforeLoginFragment = new BeforeLoginFragment();
                fragmentTransaction.replace(R.id.login_container, beforeLoginFragment);
                fragmentTransaction.commit();
            }
        });

        startDanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanjiMainActivity.class);
                startActivity(intent);
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
