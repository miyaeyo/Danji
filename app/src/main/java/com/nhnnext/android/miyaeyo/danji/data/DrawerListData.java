package com.nhnnext.android.miyaeyo.danji.data;

/**
 * Created by miyaeyo on 2015. 8. 4..
 */
public class DrawerListData {
    private String listTitle;
    private int imageId;

    public DrawerListData(String listTitle, int imageId){
        this.listTitle = listTitle;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getListTitle() {
        return listTitle;
    }
}
