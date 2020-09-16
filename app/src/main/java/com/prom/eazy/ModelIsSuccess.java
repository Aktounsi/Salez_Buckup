package com.prom.eazy;

/**
 * Created by twonsi_ on 1/9/2020. (french date)
 */

public class ModelIsSuccess {

    private int isSuccess;
    private String message;

    public int getCode_bp() {return code_bp;}

    private int code_bp;

    public ModelIsSuccess(int isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

}


