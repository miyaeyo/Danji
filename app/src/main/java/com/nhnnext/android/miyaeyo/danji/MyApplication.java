package com.nhnnext.android.miyaeyo.danji;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by miyaeyo on 2015. 8. 17..
 */
public class MyApplication extends Application{

    public static final String TAG = "DANJI";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "9bsJRraxnOoVjbhqsCjA35Gb4OMc29jzwcuZCKRq", "A3ydVgCH2c8QKwLUEmUS36fMprzVingTwDuMVyGb");
        ParseUser.enableAutomaticUser();
        ParseACL defaultAcl = new ParseACL();
        defaultAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultAcl, true);

    }
}
