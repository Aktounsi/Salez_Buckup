package com.prom.eazy;

class SubTitleModelItem {
    private int mImageResourceRight;
    private int mImageResourceLeft;

    public SubTitleModelItem(int mImageResourceRight, int mImageResourceLeft, String subTitle) {
        this.mImageResourceRight = mImageResourceRight;
        this.mImageResourceLeft = mImageResourceLeft;
        this.subTitle = subTitle;
    }

    public int getmImageResourceRight() {
        return mImageResourceRight;
    }

    public int getmImageResourceLeft() {
        return mImageResourceLeft;
    }

    public String getSubTitle() {
        return subTitle;
    }

    private String subTitle;
}
