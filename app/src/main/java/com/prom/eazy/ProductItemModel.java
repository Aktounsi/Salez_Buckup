package com.prom.eazy;

import java.io.Serializable;

public class ProductItemModel {

    public ProductItemModel(int code_pr, String designation, double prix_u, int stock, int u_crt) {
        this.code_pr = code_pr;
        this.stock = stock;
        this.u_crt = u_crt;
        this.designation = designation;
        this.prix_u = prix_u;
    }

    public int getCode_pr() {
        return code_pr;
    }

    public int getStock() {
        return stock;
    }

    public String getDesignation() {
        return designation;
    }

    public double getPrix_u() {
        return prix_u;
    }



    public int getU_crt() {
        return u_crt;
    }
    private int code_pr;
    private String designation;
    private double prix_u;
    private int stock;
    private int u_crt;



}
