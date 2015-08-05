package com.nhnnext.android.miyaeyo.danji.data;

/**
 * Created by miyaeyo on 2015. 8. 5..
 */
public class ContentsListData {
    private String contentsImage;
    private String contentsBody;
    private String contentsRefer;
    private int likeCount;

    public ContentsListData(String contentsImage, String contentsBody, String contentsRefer, int likeCount){
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

    public String getContentsImage() {
        return contentsImage;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
