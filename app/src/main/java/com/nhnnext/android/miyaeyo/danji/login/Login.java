package com.nhnnext.android.miyaeyo.danji.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.show.DanjiMainActivity;

/**
 */
public class Login extends FragmentActivity {
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginFragment = new LoginFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.login_button_frame, loginFragment);
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout buttonLayout = (LinearLayout)findViewById(R.id.start_danji_button_place);
        LayoutInflater buttonInflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View addForm = buttonInflater.inflate(R.layout.start_danji_button, buttonLayout, false);
        buttonLayout.removeAllViews();

        if(AccessToken.getCurrentAccessToken() != null){
            buttonLayout.addView(addForm);
        }
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

    public void startDanji(View view){
        if(view.getId() == R.id.start_danji){
            Intent intent = new Intent(this, DanjiMainActivity.class);
            startActivity(intent);
        }
    }



}
