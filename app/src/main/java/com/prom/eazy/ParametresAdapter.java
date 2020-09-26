package com.prom.eazy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ParametresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ListParametresObject> mParametresList;

    private com.prom.eazy.ParametresAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(com.prom.eazy.ParametresAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    ////////////////////////////////////////
    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImageViewRight,mImageViewLeft;
        public View line;
        public TitleViewHolder(View itemView, final com.prom.eazy.ParametresAdapter.OnItemClickListener listener) {
            super(itemView);
            //TODO initialize your xml views
            mTitle = itemView.findViewById(R.id.textView);
            mImageViewLeft = itemView.findViewById(R.id.imageView);
            mImageViewRight = itemView.findViewById(R.id.imageViewRight);
            line = itemView.findViewById(R.id.line);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(final TitleModelItem title) {
            //TODO set data to xml view via textivew.setText();
            mTitle.setText(title.getTitle());
            mImageViewRight.setImageResource(title.getmImageResourceRight());
            mImageViewLeft.setImageResource(title.getmImageResourceLeft());
        }

        public void bindTopLine(){
            line.setVisibility(View.GONE);
        }
    }
    ///////////////////////////////////////////
    public static class SubTitleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageViewRight,mImageViewLeft;
        public TextView mSubTitle;

        public SubTitleViewHolder(View itemView, final com.prom.eazy.ParametresAdapter.OnItemClickListener listener) {
            super(itemView);
            //TODO initialize your xml views
            mSubTitle = itemView.findViewById(R.id.textView);
            mImageViewLeft = itemView.findViewById(R.id.imageView);
            mImageViewRight = itemView.findViewById(R.id.imageViewRight);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

        public void bind(final SubTitleModelItem subTitle) {
            //TODO set data to xml view via textivew.setText();
            //mImageView.setImageResource(currentItem.getImageResource());
            mSubTitle.setText(subTitle.getSubTitle());
            mImageViewRight.setImageResource(subTitle.getmImageResourceRight());
            mImageViewLeft.setImageResource(subTitle.getmImageResourceLeft());

        }
    }


    public ParametresAdapter(ArrayList<ListParametresObject> exampleList) {
        mParametresList = exampleList;
    }
    ///////////////////////////////////////////
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ListParametresObject.TYPE_TITLE:
                View v0 = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item, parent, false);
                viewHolder = new TitleViewHolder(v0,mListener); // view holder for normal items
                break;
            case ListParametresObject.TYPE_SUBTITLE:
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.subtitle_item, parent, false);
                viewHolder = new SubTitleViewHolder(v1,mListener); // view holder for normal items
                break;
        }
        return viewHolder;

    }
    ////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ListParametresObject.TYPE_TITLE:
                TitleModelObject generalItem = (TitleModelObject) mParametresList.get(position);
                TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
                titleViewHolder.bind(generalItem.getTitle());
                if(position==0) titleViewHolder.bindTopLine();
                break;
            case ListParametresObject.TYPE_SUBTITLE:
                SubTitleModelObject generalItemLeft = (SubTitleModelObject) mParametresList.get(position);
                SubTitleViewHolder subTitleViewHolder = (SubTitleViewHolder) viewHolder;
                subTitleViewHolder.bind(generalItemLeft.getSubTitle());
                break;
        }
    }

    /////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        if(mParametresList!=null)
            return mParametresList.size();

        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mParametresList.get(position).getType();
    }

    public ListParametresObject getItem(int position) {
        return mParametresList.get(position);
    }

}








