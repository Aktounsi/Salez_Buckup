package com.prom.eazy;

import java.util.ArrayList;

public class ModelListBonsPointage {

    private int isSuccess;
    private String message;

    public ArrayList<BonPointageItemModel> getBonsPointageList() {
        return bonsPointageList;
    }

    ArrayList<BonPointageItemModel> bonsPointageList = new ArrayList<>();

    public ModelListBonsPointage(int isSuccess, String message/*, ArrayList<ProductItemModel> productsList*/) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.bonsPointageList = bonsPointageList;
    }

    public int getIsSuccess() {return isSuccess;}


    public String getMessage() { return message; }

}
