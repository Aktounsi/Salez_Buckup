package com.prom.eazy;

import java.util.ArrayList;

/**
 * Created by twonsi_ on 1/9/2020. (french date)
 */

public class ModelListeVendeursPointes {

    private int isSuccess;
    private String message;
    private ArrayList<Integer> listeVendeursPointes = new ArrayList<>();


    public ModelListeVendeursPointes(int isSuccess, String message, ArrayList<Integer> listeVendeursPointes) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listeVendeursPointes = listeVendeursPointes;
    }

    public ArrayList<Integer> getListeVendeursPointes() { return listeVendeursPointes; }

    public int getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }



}
