package com.nhnnext.android.miyaeyo.danji.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhnnext.android.miyaeyo.danji.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by miyaeyo on 2015. 8. 20..
 */
public class BeforeLoginFragment extends Fragment {
    private Dialog progressDialog;
    private Button loginButton;
    private Button signupButton;
    private String userNameTxt;
    private String passwordTxt;
    private EditText userName;
    private EditText password;

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
        return inflater.inflate(R.layout.before_login_f, null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginButton = (Button) getActivity().findViewById(R.id.login_button);
        signupButton = (Button) getActivity().findViewById(R.id.signup_button);
        userName = (EditText) getActivity().findViewById(R.id.user_name_input);
        password = (EditText) getActivity().findViewById(R.id.password_input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.logging_in), true);
                userNameTxt = userName.getText().toString();
                passwordTxt = password.getText().toString();

                ParseUser.logInInBackground(userNameTxt, passwordTxt, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        progressDialog.dismiss();
                        if (parseUser != null) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.login_container, new WelcomeFragment());
                            fragmentTransaction.commit();

                        } else {
                            Toast.makeText(getActivity(), "Please signup first", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameTxt = userName.getText().toString();
                passwordTxt = password.getText().toString();

                if (userNameTxt.equals("") && passwordTxt.equals("")) {
                    Toast.makeText(getActivity(), "Please complete the signup", Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(userNameTxt);
                    parseUser.setPassword(passwordTxt);
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "Successfully signed up", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(), "Signup error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
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
