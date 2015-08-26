package com.nhnnext.android.miyaeyo.danji.data;

import com.parse.ParseFile;

/**
 * Created by miyaeyo on 2015. 8. 5..
 */
public class ContentsListData {
    private final String danjiID;
    private final ParseFile contentsImage;
    private final String contentsBody;
    private final String contentsRefer;
    private final String userName;
    private int likeCount;

    public ContentsListData(Danji danji) {
        this.danjiID = danji.getObjectId();
        this.contentsImage = danji.getContentsImage();
        this.contentsBody = danji.getContentsBody();
        this.contentsRefer = danji.getReference();
        this.userName = danji.getUserName();
        this.likeCount = danji.getLikeCount();
    }

    public String getDanjiID() {
        return danjiID;
    }

    public String getContentsBody() {
        return contentsBody;
    }

    public String getContentsRefer() {
        return contentsRefer;
    }

    public ParseFile getContentsImage() {
        return contentsImage;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getUserName() {
        return userName;
    }
}
