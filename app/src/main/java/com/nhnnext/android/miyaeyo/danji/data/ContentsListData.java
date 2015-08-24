package com.nhnnext.android.miyaeyo.danji.data;

import android.graphics.Bitmap;

import com.parse.ParseFile;

/**
 * Created by miyaeyo on 2015. 8. 5..
 */
public class ContentsListData {
    private ParseFile contentsImage;
    private String contentsBody;
    private String contentsRefer;
    private int likeCount;

    public ContentsListData(ParseFile contentsImage, String contentsBody, String contentsRefer, int likeCount){
        this.contentsImage = contentsImage;
        this.contentsBody = contentsBody;
        this.contentsRefer = contentsRefer;
        this.likeCount = likeCount;
    }

    public String getContentsBody() {
        return contentsBody;
    }

    public String getContentsRefer() {
        return contentsRefer;
    }

    public ParseFile getContentsImage() {return contentsImage;}

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
