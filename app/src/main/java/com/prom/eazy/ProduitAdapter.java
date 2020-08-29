package com.prom.eazy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProduitAdapter  extends RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder> {
    private ArrayList<ProduitItem> mProduitList;
    public static class ProduitViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProduit;
        public TextView txtPrix;
        public TextView txtQteChg;
        public TextView txtQteVdu;
        public TextView txtQteRst;
        public ProduitViewHolder(View itemView) {
            super(itemView);
            txtProduit = itemView.findViewById(R.id.textView);
            txtPrix = itemView.findViewById(R.id.textView5Details);
            txtQteVdu = itemView.findViewById(R.id.textView3Details);
            txtQteChg = itemView.findViewById(R.id.textView2Details);
            txtQteRst = itemView.findViewById(R.id.textView4Details);

        }
    }
    public ProduitAdapter(ArrayList<ProduitItem> exampleList) {
        mProduitList = exampleList;
    }
    @Override
    public ProduitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_item, parent, false);
        ProduitViewHolder evh = new ProduitViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ProduitViewHolder holder, int position) {
        ProduitItem currentItem = mProduitList.get(position);
        holder.txtProduit.setText(currentItem.getTxtProduit());
        holder.txtPrix.setText(String.valueOf(currentItem.getPrix()) );
        holder.txtQteRst.setText(String.valueOf(currentItem.getQteRst()));
        holder.txtQteVdu.setText(String.valueOf(currentItem.getQteVdu()));
        holder.txtQteChg.setText(String.valueOf(currentItem.getQteChg()));

    }
    @Override
    public int getItemCount() {
        return mProduitList.size();
    }
}