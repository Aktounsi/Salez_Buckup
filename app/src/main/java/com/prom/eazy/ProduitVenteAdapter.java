package com.prom.eazy;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class ProduitVenteAdapter extends ArrayAdapter<ProduitItemVente> {
    private ArrayList<ProduitItemVente> mProduitList;
    public Context context;

    public ProduitVenteAdapter(ArrayList<ProduitItemVente> exampleList, Context context) {
        super(context, R.layout.produit_item_vente, exampleList);
        mProduitList = exampleList;
        this.context = context;
    }

    int getItemCount(){
        return mProduitList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ProduitItemVente currentItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.produit_item_vente, parent, false);
        }
        // Lookup view for data population
        TextView txtProduit = (TextView) convertView.findViewById(R.id.textView);
        TextView txtPrix = (TextView) convertView.findViewById(R.id.textView3Details);
        TextView txtQteRst = (TextView) convertView.findViewById(R.id.textView4Details);
        // Populate the data into the template view using the data object
        txtProduit.setText(currentItem.getTxtProduit());
        txtPrix.setText(String.valueOf(currentItem.getPrix()));
        txtQteRst.setText(String.valueOf(currentItem.getTxtQteRst()));
        // Return the completed view to render on screen
        return convertView;
    }
}
