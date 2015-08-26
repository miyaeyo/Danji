package com.nhnnext.android.miyaeyo.danji.data;

import android.graphics.drawable.Drawable;

/**
 * Created by miyaeyo on 2015. 8. 25..
 */
public class ContentsRankData {
    private int contentsRank;
    private String contentsTitle;
    private int contentsCategory;

    public ContentsRankData(int contentsRank, String contentsTitle, int contentsCategory){
        this.contentsRank = contentsRank;
        this.contentsTitle = contentsTitle;
        this.contentsCategory = contentsCategory;

    }

    public int getContentsRank() {
        return contentsRank;
    }

    public String getContentsTitle() {
        return contentsTitle;
    }

    public int getContentsCategory() {
        return contentsCategory;
    }


}
