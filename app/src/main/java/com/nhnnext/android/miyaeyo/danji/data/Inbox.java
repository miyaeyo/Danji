package com.nhnnext.android.miyaeyo.danji.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by miyaeyo on 2015. 8. 26..
 */
@ParseClassName("Inbox")
public class Inbox extends ParseObject {
    public void setUserName(String userName){
        put("UserName", userName);
    }
    public void setInboxId(String inboxId){
        put("InboxId", inboxId);
    }
    public String getUserName(){
       return getString("UserName");
    }
    public String getInboxId(){
        return getString("InboxId");
    }

}
