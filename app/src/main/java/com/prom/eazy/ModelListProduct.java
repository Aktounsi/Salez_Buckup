package com.prom.eazy;

import java.util.ArrayList;

public class ModelListProduct {

    private int isSuccess;
    private String message;
    ArrayList<ProductItemModel> productsList = new ArrayList<>();

    public ModelListProduct(int isSuccess, String message/*, ArrayList<ProductItemModel> productsList*/) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.productsList = productsList;
    }

    public int getIsSuccess() {return isSuccess;}

    public ArrayList<ProductItemModel> getProductsList() {return productsList;}

    public String getMessage() { return message; }

}
