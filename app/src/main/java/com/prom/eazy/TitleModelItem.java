package com.prom.eazy;

public class TitleModelItem {
    public void setmImageResourceRight(int mImageResourceRight) {
        this.mImageResourceRight = mImageResourceRight;
    }

    private int mImageResourceRight;
    private int mImageResourceLeft;

    public TitleModelItem(int mImageResourceRight, int mImageResourceLeft, String title) {
        this.mImageResourceRight = mImageResourceRight;
        this.mImageResourceLeft = mImageResourceLeft;
        this.title = title;
    }

    public int getmImageResourceRight() {
        return mImageResourceRight;
    }

    public int getmImageResourceLeft() {
        return mImageResourceLeft;
    }

    public String getTitle() {
        return title;
    }

    private String title;
}

