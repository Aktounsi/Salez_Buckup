package com.prom.eazy;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class ProduitVenteAdapter extends ArrayAdapter<ProduitItemVente> {
    private ArrayList<ProduitItemVente> mProduitList;
    public Context context;
    public String[] current;
    public static HashMap<Integer,String> myList=new HashMap<Integer,String>();


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
        final ViewHolder holder;
        final int pos = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.produit_item_vente, parent, false);
            holder = new ViewHolder();
            holder.txtProduit = (TextView) convertView
                    .findViewById(R.id.textView);
            holder.txtPrix = (TextView) convertView
                    .findViewById(R.id.textView3Details);
            holder.txtQteRst = (TextView) convertView
                    .findViewById(R.id.textView4Details);
            holder.et_qte = (EditText)  convertView
                    .findViewById(R.id.etQte);

            convertView.setTag(holder);
            if(position==0) holder.et_qte.requestFocus();
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.et_qte.setInputType(InputType.TYPE_CLASS_NUMBER);

        holder.et_qte.setText(myList.get(position));


        holder.et_qte.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    EditText et =(EditText)v.findViewById(R.id.etQte);
                    myList.put(pos,et.getText().toString().trim());
                }
            }
        });

        ProduitItemVente produitItemVente = getItem(position);

        holder.ref = position;
        holder.txtProduit.setText(currentItem.getTxtProduit());
        holder.txtPrix.setText(String.valueOf(currentItem.getPrix()));
        holder.txtQteRst.setText(String.valueOf(currentItem.getTxtQteRst()));

        return convertView;
    }

    class ViewHolder {
        TextView txtProduit, txtPrix, txtQteRst;
        EditText et_qte;
        int ref;
    }
}
