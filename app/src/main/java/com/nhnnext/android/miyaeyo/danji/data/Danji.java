package com.nhnnext.android.miyaeyo.danji.data;

import android.net.Uri;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by miyaeyo on 2015. 8. 21..
 */
@ParseClassName("Danji")
public class Danji extends ParseObject{

    public void setUserName(String userName){
        put("UserName", userName);
    }

    public void setCategory(String category){
        put("Category", category);
    }

    public void setContentsImage(ParseFile image){
        put("ContentsImage", image);
    }

    public void setContentsBody(String body){
        put("ContentsBody", body);
    }

    public void setCreator(String creator){
        put("Creator", creator);
    }

    public void setContentsTitle(String title){
        put("Title", title);
    }

    public void setLikeCount(int likeCount){
        put("LikeCount", likeCount);
    }

    public String getUserName(){
        return getString("UserName");
    }

    public String getCategory(){
        return getString("Category");
    }

    public ParseFile getContentsImage(){
        return (ParseFile)get("ContentsImage");
    }

    public String getContentsBody(){
        return getString("ContentsBody");
    }

    public String getCreator(){
        return getString("Creator");
    }

    public String getContentsTitle(){
        return getString("Title");
    }

    public int getLikeCount(){
        return getInt("LikeCount");
    }

    public String getReference() {
        if (getCreator().equals("")) {
            return getContentsTitle();
        } else {
            return getCreator() + ", " + getContentsTitle();
        }
    }
}
