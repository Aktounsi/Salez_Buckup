package com.prom.eazy;

public class ProduitItem {
    private int id;
    private String txtProduit;
    private int txtQteChg;
    private int txtQteVdu;
    private int txtQteRst;

    public ProduitItem(int id, String txtProduit, int txtQteVdu, int txtQteRst) {
        this.id=id;
        this.txtProduit = txtProduit;
        this.txtQteVdu = txtQteVdu;
        this.txtQteRst = txtQteRst;
    }
    public String getTxtProduit() {
        return txtProduit;
    }
    public int getText1() {
        return txtQteVdu;
    }
    public int getText2() {
        return txtQteRst;
    }
    public int getId() {
        return this.id;
    }
}