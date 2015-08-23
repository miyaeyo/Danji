package com.nhnnext.android.miyaeyo.danji.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by miyaeyo on 2015. 8. 22..
 */
@ParseClassName("DialogPart")
public class DialogPart extends ParseObject {
    public void setCharacter(String character){
        put("Character", character);
    }

    public void setContentsDialog(String dialog){
        put("Dialog", dialog);
    }

    public String getCharacter(){
        return getString("Character");
    }

    public String getContentsDialog(){
        return getString("Dialog");
    }
}
