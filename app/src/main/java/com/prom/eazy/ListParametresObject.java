package com.prom.eazy;

public abstract class ListParametresObject {
     public static final int TYPE_TITLE = 0;
        public static final int TYPE_SUBTITLE = 1;
        boolean checked;

        abstract public boolean isChecked();
        abstract public void setChecked(boolean bool);
        abstract public void setRightImage(int img);

        abstract public int getType();
    }
