package com.prom.eazy;

public class ProduitItemVente {
    private int id;
    private String txtProduit;
    private float prix;
    private int QteRst;

    public ProduitItemVente(int id, String txtProduit, float prix, int QteRst) {
        this.id=id;
        this.txtProduit = txtProduit;
        this.prix = prix;
        this.QteRst = QteRst;
    }
    public String getTxtProduit() {
        return txtProduit;
    }
    public float getPrix() {
        return prix;
    }
    public int getTxtQteRst() {
        return QteRst;
    }
    public int getId() {
        return this.id;
    }
}
