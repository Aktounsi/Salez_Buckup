package com.prom.eazy;

import java.util.ArrayList;

public class ModelListAgents {

    private int isSuccess;
    private String message;
    ArrayList<VendeurItemModel> vendeursList = new ArrayList<>();

    public ModelListAgents(int isSuccess, String message, ArrayList<VendeurItemModel> vendersList) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.vendeursList = vendersList;
    }

    public int getIsSuccess() {return isSuccess;}

    public ArrayList<VendeurItemModel> getVendeursList() {return vendeursList;}

    public String getMessage() { return message; }

}
