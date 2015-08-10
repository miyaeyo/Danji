package com.nhnnext.android.miyaeyo.danji.data;

import android.content.res.Resources;

/**
 * Created by miyaeyo on 2015. 8. 4..
 */
public class DrawerListData {
    private String listTitle;
    private int icon;

    public DrawerListData(String listTitle, int icon){
        this.listTitle = listTitle;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getListTitle() {
        return listTitle;
    }
}
