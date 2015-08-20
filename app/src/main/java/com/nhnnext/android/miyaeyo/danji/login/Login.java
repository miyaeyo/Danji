package com.nhnnext.android.miyaeyo.danji.login;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.show.DanjiMainActivity;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

/**
 */
public class Login extends Activity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        fragmentTransaction = getFragmentManager().beginTransaction();
        BeforeLoginFragment beforeLoginFragment = new BeforeLoginFragment();
        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            fragmentTransaction.add(R.id.login_container, beforeLoginFragment);
            fragmentTransaction.commit();
        } else {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if(currentUser != null){
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                fragmentTransaction.replace(R.id.login_container, welcomeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                fragmentTransaction.replace(R.id.login_container, beforeLoginFragment);
                fragmentTransaction.commit();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    private void startDanji(){
        Intent intent = new Intent(this, DanjiMainActivity.class);
        startActivity(intent);

    }

}
