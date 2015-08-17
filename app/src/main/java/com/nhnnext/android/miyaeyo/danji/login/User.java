package com.nhnnext.android.miyaeyo.danji.login;

/**
 * Created by miyaeyo on 2015. 8. 17..
 */
public class User {
    private String userEmail;
    private int numOfWallContents;
    private int numOfInboxContents;

    public User(){
        this(null, 0, 0);
    }
    public User(String userEmail, int numOfWallContents, int numOfInboxContents){
        this.userEmail = userEmail;
        this.numOfWallContents = numOfWallContents;
        this.numOfInboxContents = numOfInboxContents;

    }

    public int getNumOfInboxContents() {
        return numOfInboxContents;
    }

    public int getNumOfWallContents() {
        return numOfWallContents;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setNumOfInboxContents(int numOfInboxContents) {
        this.numOfInboxContents = numOfInboxContents;
    }

    public void setNumOfWallContents(int numOfWallContents) {
        this.numOfWallContents = numOfWallContents;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
