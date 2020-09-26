package com.prom.eazy;

public class SubTitleModelObject extends ListParametresObject {

        private SubTitleModelItem subTitle;

        public SubTitleModelItem getSubTitle() {
            return this.subTitle;
        }

        public void setSubTitle(SubTitleModelItem subTitle) {
            this.subTitle = subTitle;
        }

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

    }

    @Override
        public int getType() {
            return TYPE_SUBTITLE;
        }
    }
