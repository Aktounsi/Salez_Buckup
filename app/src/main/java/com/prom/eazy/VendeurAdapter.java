package com.prom.eazy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VendeurAdapter extends RecyclerView.Adapter<VendeurAdapter.VendeurViewHolder> {
    private ArrayList<VendeurItem> mVendeurList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public static class VendeurViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTxtVendeur;
        public TextView mTxtVehicule;
        public TextView mTxtSecteur;
        public VendeurViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTxtVendeur = itemView.findViewById(R.id.textView);
            mTxtVehicule = itemView.findViewById(R.id.textView2Details);
            mTxtSecteur = itemView.findViewById(R.id.textView3Details);
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
    }
    public VendeurAdapter(ArrayList<VendeurItem> vendeurList) {
        mVendeurList = vendeurList;
    }
    @Override
    public VendeurViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendeur_item, parent, false);
        VendeurViewHolder evh = new VendeurViewHolder(v, mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(VendeurViewHolder holder, int position) {
        VendeurItem currentItem = mVendeurList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTxtVendeur.setText(currentItem.getTxtVendeur());
        holder.mTxtVehicule.setText(currentItem.getTxtVehicule());
        holder.mTxtSecteur.setText(currentItem.getTxtSecteur());
    }
    @Override
    public int getItemCount() {
        return mVendeurList.size();
    }
}
