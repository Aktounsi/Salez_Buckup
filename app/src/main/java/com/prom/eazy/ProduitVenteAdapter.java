package com.prom.eazy;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
public class ProduitVenteAdapter extends RecyclerView.Adapter<ProduitVenteAdapter.ProduitVenteViewHolder> {
    private ArrayList<ProduitItemVente> mProduitList;
    public static class ProduitVenteViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProduit;
        public TextView txtPrix;
        public TextView txtQteRst;
        public ProduitVenteViewHolder(View itemView) {
            super(itemView);
            txtProduit = itemView.findViewById(R.id.textView);
            txtPrix = itemView.findViewById(R.id.textView3Details);
            txtQteRst = itemView.findViewById(R.id.textView4Details);
        }
    }
    public ProduitVenteAdapter(ArrayList<ProduitItemVente> exampleList) {
        mProduitList = exampleList;
    }
    @Override
    public ProduitVenteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_item_vente, parent, false);
        ProduitVenteViewHolder evh = new ProduitVenteViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ProduitVenteViewHolder holder, int position) {
        ProduitItemVente currentItem = mProduitList.get(position);
        holder.txtProduit.setText(currentItem.getTxtProduit());
        holder.txtPrix.setText(String.valueOf(currentItem.getPrix()) );
        holder.txtQteRst.setText(String.valueOf(currentItem.getTxtQteRst()));
    }
    @Override
    public int getItemCount() {
        return mProduitList.size();
    }
}
