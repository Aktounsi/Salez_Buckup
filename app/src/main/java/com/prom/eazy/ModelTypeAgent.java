package com.prom.eazy;

public class ModelTypeAgent {
    private String username;
    private char type;
    private int autorisation;

    private int isSuccess;
    private String message;

    public ModelTypeAgent(String username, char type, int autorisation, int isSuccess, String message) {
        this.username = username;
        this.type =type;
        this.autorisation = autorisation;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getType(){return this.type;}

    public void setType(char c){this.type = c;}

    public int getAutorisation(){return this.autorisation;}

    public void setAutorisation(int autorisation){this.autorisation = autorisation;}

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