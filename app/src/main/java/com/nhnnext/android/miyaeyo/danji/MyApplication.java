package com.nhnnext.android.miyaeyo.danji;

import android.app.Application;

import com.nhnnext.android.miyaeyo.danji.data.Danji;
import com.nhnnext.android.miyaeyo.danji.data.DialogPart;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
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
        ParseObject.registerSubclass(Danji.class);
        ParseObject.registerSubclass(DialogPart.class);
        Parse.initialize(this, "9bsJRraxnOoVjbhqsCjA35Gb4OMc29jzwcuZCKRq", "A3ydVgCH2c8QKwLUEmUS36fMprzVingTwDuMVyGb");
        ParseUser.enableAutomaticUser();
        ParseACL defaultAcl = new ParseACL();
        defaultAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultAcl, true);

    }
}
