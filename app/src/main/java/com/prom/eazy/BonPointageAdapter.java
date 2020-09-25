package com.prom.eazy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BonPointageAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private ArrayList<ListObject> mBonPointageList;

    private com.prom.eazy.BonPointageAdapter.OnItemClickListener mListener;
    private com.prom.eazy.BonPointageAdapter.OnItemLongClickListener mLongListener;

    public interface OnItemClickListener {
            void onItemClick(int position);
        }
    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }
        public void setOnItemClickListener(com.prom.eazy.BonPointageAdapter.OnItemClickListener listener) {
            mListener = listener;
        }
    public void setOnItemLongClickListener(com.prom.eazy.BonPointageAdapter.OnItemLongClickListener longListener) {
        mLongListener = longListener;
    }
////////////////////////////////////////
        public static class DateViewHolder extends RecyclerView.ViewHolder {
            public TextView mTxtDate;
            public DateViewHolder(View itemView, final com.prom.eazy.BonPointageAdapter.OnItemClickListener listener) {
                super(itemView);
                //TODO initialize your xml views
                mTxtDate = itemView.findViewById(R.id.TxtDate);
            }

            public void bind(final String date) {
                //TODO set data to xml view via textivew.setText();
                mTxtDate.setText(date);
            }
        }
///////////////////////////////////////////
    public static class BonPointageRetourViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTxtVendeur;
        public TextView mTxtVehicule;
        public TextView mTxtSecteur;
        public TextView mTxtType;

        public BonPointageRetourViewHolder(View itemView, final com.prom.eazy.BonPointageAdapter.OnItemClickListener listener,
                                                        final com.prom.eazy.BonPointageAdapter.OnItemLongClickListener longListener) {
            super(itemView);
            //TODO initialize your xml views
            mImageView = itemView.findViewById(R.id.imageView);
            mTxtVendeur = itemView.findViewById(R.id.textView);
            mTxtVehicule = itemView.findViewById(R.id.textView2Details);
            mTxtSecteur = itemView.findViewById(R.id.textView3Details);
            mTxtType = itemView.findViewById(R.id.textView4Details);
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

            //here
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longListener.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }

        public void bind(final BonPointageItemModel currentItem) {
            //TODO set data to xml view via textivew.setText();
            //mImageView.setImageResource(currentItem.getImageResource());
            mTxtVendeur.setText(currentItem.getNom()+" "+currentItem.getPrenom()+" et "+currentItem.getChauffeur());
            mTxtVehicule.setText(currentItem.getMatricule());
            mTxtSecteur.setText(currentItem.getCode_sec());
            mTxtType.setText(currentItem.getType_bp());
        }
    }

///////////////////////////////////////////
    public static class BonPointageSortieViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTxtVendeur;
        public TextView mTxtVehicule;
        public TextView mTxtSecteur;
        public TextView mTxtType;

        public BonPointageSortieViewHolder(View itemView, final com.prom.eazy.BonPointageAdapter.OnItemClickListener listener,
                                           final com.prom.eazy.BonPointageAdapter.OnItemLongClickListener longListener) {
            super(itemView);
            //TODO initialize your xml views
            mImageView = itemView.findViewById(R.id.imageView);
            mTxtVendeur = itemView.findViewById(R.id.textView);
            mTxtVehicule = itemView.findViewById(R.id.textView2Details);
            mTxtSecteur = itemView.findViewById(R.id.textView3Details);
            mTxtType = itemView.findViewById(R.id.textView4Details);
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
//here
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longListener.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }

        public void bind(final BonPointageItemModel currentItem) {
            //TODO set data to xml view via textivew.setText();
            //mImageView.setImageResource(currentItem.getImageResource());
            mTxtVendeur.setText(currentItem.getNom()+" "+currentItem.getPrenom()+" et "+currentItem.getChauffeur());
            mTxtVehicule.setText(currentItem.getMatricule());
            mTxtSecteur.setText(currentItem.getCode_sec());
            mTxtType.setText(currentItem.getType_bp());
        }
    }

///////////////////////////////////////////
    public BonPointageAdapter(ArrayList<ListObject> exampleList) {
            mBonPointageList = exampleList;
        Log.d("exxx","rani f adapter constructor "+getItemCount());
        }
///////////////////////////////////////////
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("exxx","rani f adapter onCreateViewHolder");
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ListObject.TYPE_GENERAL_SORTIE:
                Log.d("exxx","rani f adapter onCreateViewHolder sortie");
                View v0 = LayoutInflater.from(parent.getContext()).inflate(R.layout.bon_pointage_item, parent, false);
                viewHolder = new BonPointageSortieViewHolder(v0,mListener,mLongListener); // view holder for normal items
                break;
            case ListObject.TYPE_GENERAL_RETOUR:
                Log.d("exxx","rani f adapter onCreateViewHolder retour");
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.bon_retour_item, parent, false);
                viewHolder = new BonPointageRetourViewHolder(v1,mListener,mLongListener); // view holder for normal items
                break;
            case ListObject.TYPE_DATE:
                Log.d("exxx","rani f adapter onCreateViewHolder date");
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
                viewHolder = new DateViewHolder(v2,mListener);
                break;
        }

        return viewHolder;

    }
    ////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Log.d("exxx","rani f adapter onBindViewHolder");
        switch (viewHolder.getItemViewType()) {
            case ListObject.TYPE_GENERAL_SORTIE:
                Log.d("exxx","rani f adapter onBindViewHolder sortie");
                BonPointageItemModelObject generalItem = (BonPointageItemModelObject) mBonPointageList.get(position);
                Log.d("exxx","rani f adapter onBindViewHolder sortie 2");
                BonPointageSortieViewHolder bonSortieViewHolder = (BonPointageSortieViewHolder) viewHolder;
                Log.d("exxx","rani f adapter onBindViewHolder sortie 3");
                bonSortieViewHolder.bind(generalItem.getBonPointageItemModel());
                break;
            case ListObject.TYPE_GENERAL_RETOUR:
                Log.d("exxx","rani f adapter onBindViewHolder retour");
                BonPointageItemModelObject generalItemLeft = (BonPointageItemModelObject) mBonPointageList.get(position);
                BonPointageRetourViewHolder bonRetourViewHolder = (BonPointageRetourViewHolder) viewHolder;
                bonRetourViewHolder.bind(generalItemLeft.getBonPointageItemModel());
                break;
            case ListObject.TYPE_DATE:
                Log.d("exxx","rani f adapter onBindViewHolder date");
                DateObject dateItem = (DateObject) mBonPointageList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;
                dateViewHolder.bind(dateItem.getDate());
                break;
        }
    }

    /////////////////////////////////////////////////
        @Override
        public int getItemCount() {
            if(mBonPointageList!=null)
                    return mBonPointageList.size();

            return 0;
        }

    @Override
    public int getItemViewType(int position) {
        return mBonPointageList.get(position).getType();
    }

    public ListObject getItem(int position) {
        return mBonPointageList.get(position);
    }

    }








