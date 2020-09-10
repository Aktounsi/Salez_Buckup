package com.prom.eazy;

public class ModelUsername {
    private String username;

    private int isSuccess;
    private String message;

    public ModelUsername(String username, int isSuccess, String message) {
        this.username = username;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
