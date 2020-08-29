package com.prom.eazy;

public class ProduitItem {
    private int id;
    private String txtProduit;
    private float prix;
    private int txtQteChg;
    private int txtQteVdu;
    private int txtQteRst;

    public ProduitItem(int id, String txtProduit, int txtQteChg, int txtQteVdu, int txtQteRst, float prix) {
        this.id=id;
        this.txtProduit = txtProduit;
        this.txtQteChg = txtQteChg;
        this.txtQteVdu = txtQteVdu;
        this.txtQteRst = txtQteRst;
        this.prix = prix;
    }
    public String getTxtProduit() {
        return this.txtProduit;
    }
    public int getQteChg() {
        return this.txtQteChg;
    }
    public float getPrix() {
        return this.prix;
    }
    public int getQteVdu() {
        return this.txtQteVdu;
    }
    public int getQteRst() {
        return this.txtQteRst;
    }
    public int getId() {
        return this.id;
    }
}