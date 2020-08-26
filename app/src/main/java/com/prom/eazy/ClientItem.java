package com.prom.eazy;

public class ClientItem {
    private int id;
    private int mImageResource;
    private String mText1;
    private String mText2;
    public ClientItem(int id, int imageResource, String text1, String text2) {
        this.id=id;
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
    public int getId() {
        return this.id;
    }
}