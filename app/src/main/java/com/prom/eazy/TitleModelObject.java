package com.prom.eazy;

public class TitleModelObject extends ListParametresObject {

        private TitleModelItem title;

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean bool) {
        checked = bool;
    }

    @Override
    public void setRightImage(int img) {
        title.setmImageResourceRight(img);
    }

    public TitleModelItem getTitle() {
            return this.title;
        }

        public void setTitle(TitleModelItem title) {
            this.title = title;
        }

        @Override
        public int getType() {
            return TYPE_TITLE;
        }
    }

